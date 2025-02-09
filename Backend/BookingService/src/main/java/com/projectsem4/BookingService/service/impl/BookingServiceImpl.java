package com.projectsem4.BookingService.service.impl;

import com.projectsem4.BookingService.client.config.StadiumServiceClient;
import com.projectsem4.BookingService.entity.Booking;
import com.projectsem4.BookingService.entity.BookingAccessory;
import com.projectsem4.BookingService.entity.BookingReferee;
import com.projectsem4.BookingService.model.request.CreateBookingRequest;
import com.projectsem4.BookingService.repository.BookingAccessoryRepository;
import com.projectsem4.BookingService.repository.BookingRefereeRepository;
import com.projectsem4.BookingService.repository.BookingRepository;
import com.projectsem4.BookingService.service.BookingService;
import com.projectsem4.common_service.dto.constant.Constant;
import com.projectsem4.common_service.dto.entity.Field;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final BookingAccessoryRepository bookingAccessoryRepository;
    private final BookingRefereeRepository bookingRefereeRepository;
    private final StadiumServiceClient stadiumServiceClient;

    @Override
    public void createBooking(CreateBookingRequest request) {
        Booking booking = new Booking();
        booking.setFieldId(request.getFieldId());
        booking.setUserId(request.getUserId());
        booking.setBookingDate(request.getBookingDate());
        booking.setQuantity(request.getQuantity());
        booking.setStartTime(request.getStartTime());
        booking.setEndTime(request.getEndTime());
        bookingRepository.save(booking);
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
        createBookingRequest.setFieldId(booking.getFieldId());
        createBookingRequest.setQuantity(booking.getQuantity());
        createBookingRequest.setUserId(booking.getUserId());
        createBookingRequest.setBookingDate(booking.getBookingDate());
        createBookingRequest.setStartTime(booking.getStartTime());
        createBookingRequest.setEndTime(booking.getEndTime());
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
        List<Booking> bookingDup = bookingRepository.checkBookingField(booking.getFieldId(), booking.getBookingDate(), booking.getEndTime(), booking.getStartTime());
        int quantity = bookingDup.size();
        Field field = stadiumServiceClient.findFieldById(booking.getFieldId());
        if (booking.getQuantity() + quantity > field.getQuantity()) {
            return false;
        }
        for (BookingAccessory bookingAccessory : booking.getBookingAccessory()) {
            int quantityAccessory = bookingAccessory.getQuantity();
            for (Booking bookingRef : bookingDup) {
                List<BookingAccessory> list = bookingAccessoryRepository.checkBookingAccessory(bookingRef.getId(), bookingAccessory.getAccessoryId(), bookingAccessory.getQuantity());
                if(list.isEmpty()){
                    continue;
                }
                quantityAccessory = quantityAccessory + list.get(0).getQuantity();
            }
            if (quantityAccessory > field.getQuantity()) {
                return false;
            }
        }
        return true;
    }
}
