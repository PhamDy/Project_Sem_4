package com.projectsem4.BookingService.service.impl;

import com.projectsem4.BookingService.client.PaymentServiceClient;
import com.projectsem4.BookingService.client.StadiumServiceClient;
import com.projectsem4.BookingService.entity.Booking;
import com.projectsem4.BookingService.entity.BookingAccessory;
import com.projectsem4.BookingService.entity.BookingDetail;
import com.projectsem4.BookingService.entity.BookingReferee;
import com.projectsem4.BookingService.model.request.CreateBookingRequest;
import com.projectsem4.BookingService.repository.*;
import com.projectsem4.BookingService.service.BookingService;
import com.projectsem4.common_service.dto.constant.Constant;
import com.projectsem4.common_service.dto.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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

    @Override
    public Object createBooking(CreateBookingRequest request) {
        Booking booking = new Booking();
        booking.setUserId(request.getUserId());
        booking.setTotalPrice(request.getTotalPrice());
        bookingRepository.save(booking);
        if(request.getBookingDetails() != null && !request.getBookingDetails().isEmpty()) {
            bookingDetailRepository.saveAll(request.getBookingDetails());
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
    public CreateBookingRequest findBookingById(Long id) {
        Booking booking = bookingRepository.findById(id).get();
        List<BookingAccessory> bookingAccessory = bookingAccessoryRepository.findByBookingId(id);
        List<BookingReferee> bookingReferee = bookingRefereeRepository.findByBookingId(id);
        CreateBookingRequest createBookingRequest = new CreateBookingRequest();
        createBookingRequest.setBookingId(booking.getId());
        createBookingRequest.setUserId(booking.getUserId());
        createBookingRequest.setTotalPrice(booking.getTotalPrice());
        createBookingRequest.setBookingAccessory(bookingAccessory);
        createBookingRequest.setBookingReferees(bookingReferee);
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
    public List<TimeFrameSchedule> scheduleClient(Long fieldId, LocalDate date) {
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
}
