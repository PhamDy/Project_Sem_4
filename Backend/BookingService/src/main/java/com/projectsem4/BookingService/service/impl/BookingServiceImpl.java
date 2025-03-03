package com.projectsem4.BookingService.service.impl;

import com.projectsem4.BookingService.client.StadiumServiceClient;
import com.projectsem4.BookingService.entity.Booking;
import com.projectsem4.BookingService.entity.BookingAccessory;
import com.projectsem4.BookingService.entity.BookingDetail;
import com.projectsem4.BookingService.entity.BookingReferee;
import com.projectsem4.BookingService.model.request.CreateBookingRequest;
import com.projectsem4.BookingService.repository.BookingAccessoryRepository;
import com.projectsem4.BookingService.repository.BookingDetailRepository;
import com.projectsem4.BookingService.repository.BookingRefereeRepository;
import com.projectsem4.BookingService.repository.BookingRepository;
import com.projectsem4.BookingService.service.BookingService;
import com.projectsem4.common_service.dto.constant.Constant;
import com.projectsem4.common_service.dto.entity.FieldType;
import com.projectsem4.common_service.dto.entity.Price;
import com.projectsem4.common_service.dto.entity.TimeFrameDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final BookingAccessoryRepository bookingAccessoryRepository;
    private final BookingRefereeRepository bookingRefereeRepository;
    private final StadiumServiceClient stadiumServiceClient;
    private final BookingDetailRepository bookingDetailRepository;

    @Override
    public void createBooking(CreateBookingRequest request) {
        Booking booking = new Booking();
        booking.setUserId(request.getUserId());
        bookingRepository.save(booking);
        bookingDetailRepository.saveAll(request.getBookingDetails());
        bookingAccessoryRepository.saveAll(request.getBookingAccessory());
        bookingRefereeRepository.saveAll(request.getBookingReferees());
    }

    @Override
    public CreateBookingRequest findBookingById(Long id) {
        Booking booking = bookingRepository.findById(id).get();
        List<BookingAccessory> bookingAccessory = bookingAccessoryRepository.findByBookingId(id);
        List<BookingReferee> bookingReferee = bookingRefereeRepository.findByBookingId(id);
        CreateBookingRequest createBookingRequest = new CreateBookingRequest();
        createBookingRequest.setBookingId(booking.getId());
        createBookingRequest.setUserId(booking.getUserId());
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
        for (BookingDetail bookingDetail : booking.getBookingDetails()) {
            List<BookingDetail> bookingDup = bookingDetailRepository.checkBookingField(bookingDetail.getFieldId(), booking.getBookingDate(), booking.getTimeFrameId());
            if(!bookingDup.isEmpty()){
                return false;
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
    public List<Price> findTimeAvailable(List<Price> price, LocalDate date, Long quantity) {
        for (Price p : price) {
            long quantityBook = bookingRepository.checkTimeAvailable(date,p.getPriceTo(),p.getPriceFrom()).size();
            p.setQuantity(quantity - quantityBook);
        }
        return price;
    }

    @Override
    public Object scheduleClient(List<Long> fieldIds, LocalDate date) {
        Map<TimeFrameDate,Boolean> result;
        return null;
    }
}
