package com.projectsem4.BookingService.service.impl;

import com.projectsem4.BookingService.client.PaymentServiceClient;
import com.projectsem4.BookingService.client.StadiumServiceClient;
import com.projectsem4.BookingService.entity.*;
import com.projectsem4.BookingService.entity.Booking;
import com.projectsem4.BookingService.entity.BookingDetail;
import com.projectsem4.BookingService.model.request.CreateBookingRequest;
import com.projectsem4.BookingService.repository.*;
import com.projectsem4.BookingService.service.BookingService;
import com.projectsem4.common_service.dto.UserInfor;
import com.projectsem4.common_service.dto.constant.Constant;
import com.projectsem4.common_service.dto.entity.*;
import com.projectsem4.common_service.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final BookingAccessoryRepository bookingAccessoryRepository;
    private final BookingRefereeRepository bookingRefereeRepository;
    private final BookingPeriodRepository bookingPeriodRepository;
    private final StadiumServiceClient stadiumServiceClient;
    private final BookingDetailRepository bookingDetailRepository;
    private final PaymentServiceClient paymentServiceClient;
    private final ModelMapper modelMapper;

    @Override
    public Object createBooking(CreateBookingRequest request, HttpServletRequest httpServletRequest) {
        Booking booking = new Booking();
        booking.setUserId(request.getUserId());
        booking.setTotalPrice(request.getTotalPrice());
        booking.setStatus(Constant.OrderStatus.pending);
        booking.setUserId(JwtUtil.decodeToken(JwtUtil.genStringToken(httpServletRequest)).getUserId());
        bookingRepository.save(booking);
        List<BookingDetail> detail = request.getBookingDetails().stream().peek(item -> item.setBookingId(booking.getId())).toList();
        if(request.getBookingDetails() != null && !request.getBookingDetails().isEmpty()) {
            bookingDetailRepository.saveAll(detail);
        }
        if(request.getBookingPeriods() != null && !request.getBookingPeriods().isEmpty()) {
            bookingPeriodRepository.saveAll(request.getBookingPeriods());
        }
        if(request.getBookingAccessory() != null && !request.getBookingAccessory().isEmpty()) {
            bookingAccessoryRepository.saveAll(request.getBookingAccessory());
        }
        if(request.getBookingReferees() != null && !request.getBookingReferees().isEmpty()) {
            bookingRefereeRepository.saveAll(request.getBookingReferees());
        }
        request.setUrl(paymentServiceClient.linkThanhToan(booking.getId()));
         return request;
    }

    @Override
    public Object createBookingPeriod(BookingPeriod request, HttpServletRequest httpServletRequest) {
        List<LocalDate> result = new ArrayList<>();

        LocalDate today = LocalDate.now();
        LocalDate targetMonth = request.getMonth().withDayOfMonth(1); // lấy ngày đầu tháng
        int year = targetMonth.getYear();
        int monthValue = targetMonth.getMonthValue();

        // Nếu tháng nhập nhỏ hơn tháng hiện tại thì tăng năm
        if (monthValue < today.getMonthValue() || (monthValue == today.getMonthValue() && year < today.getYear())) {
            year++;
            targetMonth = LocalDate.of(year, monthValue, 1);
        }

        int lengthOfMonth = targetMonth.lengthOfMonth();

        DayOfWeek targetDay = DayOfWeek.of(request.getWeekDay().intValue());

        for (int day = 1; day <= lengthOfMonth; day++) {
            LocalDate date = LocalDate.of(year, monthValue, day);
            if (date.getDayOfWeek().equals(targetDay) && date.isAfter(today)) {
                result.add(date);
            }
        }
        bookingPeriodRepository.save(request);
        Booking booking = new Booking();
        booking.setStatus(Constant.OrderStatus.pending);
        booking.setTotalPrice(request.getPrice());
        booking.setUserId(JwtUtil.decodeToken(JwtUtil.genStringToken(httpServletRequest)).getUserId());
        bookingRepository.save(booking);
        List<BookingDetail> details = new ArrayList<>();
        for (LocalDate date : result) {
            BookingDetail detail = new BookingDetail();
            detail.setBookingDate(date);
            detail.setQuantity(request.getQuantity());
            detail.setTimeFrame(request.getTimeFrame());
            detail.setFieldTypeId(request.getFieldTypeId());
            detail.setBookingId(booking.getId());
            details.add(detail);
        }
        bookingDetailRepository.saveAll(details);
        bookingPeriodRepository.save(request);
        CreateBookingRequest createBookingRequest = new CreateBookingRequest();
        createBookingRequest.setUrl((paymentServiceClient.linkThanhToan(booking.getId())));
        return createBookingRequest;
    }

    @Override
    public CreateBookingRequest findBookingById(Long id) {
        Booking booking = bookingRepository.findById(id).get();
        List<BookingDetailResponse> details = bookingDetailRepository.findByBookingId(id).stream().map(item->modelMapper.map(item, BookingDetailResponse.class)).toList();
        List<BookingDetailResponse> responses = details.stream().peek(item-> item.setPrice((long) (Objects.requireNonNullElse(stadiumServiceClient.findFieldById(item.getFieldTypeId()).getPrice(),0L) * Constant.TimeFrameEnum.fromValue(item.getTimeFrame())))).toList();
        CreateBookingRequest createBookingRequest = new CreateBookingRequest();
        createBookingRequest.setBookingId(booking.getId());
        createBookingRequest.setUserId(booking.getUserId());
        createBookingRequest.setTotalPrice(booking.getTotalPrice());
        createBookingRequest.setBookingDetailResponses(responses);
        return createBookingRequest;
    }

    @Override
    public Object updateStatusOderByPayment(Integer status, Long orderId) {
        Booking booking = bookingRepository.findById(orderId).get();
        if(Objects.equals(status, Constant.OrderStatus.fail)){
            booking.setStatus(status);
        }
        else if (checkQuantityBooking(findBookingById(orderId))) {
            booking.setStatus(Constant.OrderStatus.completed);
        } else booking.setStatus(Constant.OrderStatus.refund);
        return null;
    }

    public Boolean checkQuantityBooking(CreateBookingRequest booking) {
        if(booking.getBookingDetails() != null && !booking.getBookingDetails().isEmpty()){
            for (BookingDetail bookingDetail : booking.getBookingDetails()) {
                List<BookingDetail> bookingDup = bookingDetailRepository.checkBookingField(bookingDetail.getFieldTypeId(), bookingDetail.getTimeFrame(),bookingDetail.getBookingDate());
                Long quantityBooked = 0L;
                for (BookingDetail bookingDetail1 : bookingDup) {
                    quantityBooked = quantityBooked + bookingDetail1.getQuantity();
                }
                FieldType fieldType = stadiumServiceClient.findFieldById(bookingDetail.getFieldTypeId());
                if(bookingDetail.getQuantity() + quantityBooked > fieldType.getQuantity()){
                    return false;
                }
            }
        }

//        FieldType fieldType = stadiumServiceClient.findFieldById(booking.getFieldId());
//        for (BookingAccessory bookingAccessory : booking.getBookingAccessory()) {
//            int quantityAccessory = bookingAccessory.getQuantity();
//            for (Booking bookingRef : bookingDup) {
//                List<BookingAccessory> list = bookingAccessoryRepository.checkBookingAccessory(bookingRef.getId(), bookingAccessory.getAccessoryId(), bookingAccessory.getQuantity());
//                if(list.isEmpty()){
//                    continue;
//                }
//                quantityAccessory = quantityAccessory + list.get(0).getQuantity();
//            }
//            if (quantityAccessory > fieldType.getQuantity()) {
//                return false;
//            }
//        }
        return true;
    }

    @Override
    public List<TimeFrameSchedule> scheduleClient(Long fieldId, String dateString) {
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<TimeFrameSchedule> result = new ArrayList<>();
            for(int i = 0; i <7; i++){
                LocalDate date1 = date.plusDays(i);
                TimeFrameSchedule timeFrameDate = new TimeFrameSchedule();
                timeFrameDate.setDate(date1);
                List<FieldSchedule> fieldScheduleList = new ArrayList<>();
//                timeFrameDate.setFieldSchedules(fieldScheduleList);
                Constant.TimeFrameEnum.getAllTimeFrames().forEach(item->{
                    FieldSchedule fieldSchedule = new FieldSchedule();
                    fieldSchedule.setFieldId(fieldId);
//                    fieldSchedule.setPrice(item.getScale() * item.getKey());
                    fieldSchedule.setTimeFrame(item.getKey());
                    List<BookingDetail> dup =bookingDetailRepository.checkBookingField(fieldId,item.getKey(),date1);
                    long quantityBooked = 0L;
                    if(dup != null && !dup.isEmpty()){
                        for(BookingDetail bookingDetail : dup){
                            quantityBooked = quantityBooked + bookingDetail.getQuantity();
                        }
                    }
                    fieldSchedule.setQuantity(quantityBooked);
                    fieldScheduleList.add(fieldSchedule);
//                    fieldScheduleList.add(fieldSchedule);
                });
                timeFrameDate.setFieldSchedules(fieldScheduleList);
                result.add(timeFrameDate);
            }
        return result;
    }

    @Override
    public List<BookingDetail> findByBookingId(Long bookingId) {
        return bookingDetailRepository.findByBookingId(bookingId);
    }

    @Override
    public List<CreateBookingRequest> findAllBookings(HttpServletRequest request) {
        UserInfor userInfor = JwtUtil.decodeToken(JwtUtil.genStringToken(request));
        List<Long> bookingId = bookingRepository.findByUserId(userInfor.getUserId()).stream().map(Booking::getUserId).toList();
        List<CreateBookingRequest> result = new ArrayList<>();
        bookingId.forEach(item ->{
            result.add(this.findBookingById(item));
        });
        return result;
    }

    public List<TimeFrameSchedule> scheduleClientPeriod(Long fieldId, List<String> dateString) {
        List<LocalDate> date = dateString.stream()
                .map(item -> LocalDate.parse(item, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .toList();
        List<TimeFrameSchedule> result = new ArrayList<>();
        for(int i = 0; i <date.size(); i++){
            LocalDate date1 = date.get(i);
            TimeFrameSchedule timeFrameDate = new TimeFrameSchedule();
            timeFrameDate.setDate(date1);
            List<FieldSchedule> fieldScheduleList = new ArrayList<>();
//                timeFrameDate.setFieldSchedules(fieldScheduleList);
            Constant.TimeFrameEnum.getAllTimeFrames().forEach(item->{
                FieldSchedule fieldSchedule = new FieldSchedule();
                fieldSchedule.setFieldId(fieldId);
//                    fieldSchedule.setPrice(item.getScale() * item.getKey());
                fieldSchedule.setTimeFrame(item.getKey());
                List<BookingDetail> dup =bookingDetailRepository.checkBookingField(fieldId,item.getKey(),date1);
                long quantityBooked = 0L;
                if(dup != null && !dup.isEmpty()){
                    for(BookingDetail bookingDetail : dup){
                        quantityBooked = quantityBooked + bookingDetail.getQuantity();
                    }
                }
                fieldSchedule.setQuantity(quantityBooked);
                fieldScheduleList.add(fieldSchedule);
//                    fieldScheduleList.add(fieldSchedule);
            });
            timeFrameDate.setFieldSchedules(fieldScheduleList);
            result.add(timeFrameDate);
        }
        return result;
    }
}
