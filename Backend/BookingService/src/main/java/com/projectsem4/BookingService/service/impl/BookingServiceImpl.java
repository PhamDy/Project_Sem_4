package com.projectsem4.BookingService.service.impl;

import com.projectsem4.BookingService.entity.Booking;
import com.projectsem4.BookingService.entity.BookingAccessory;
import com.projectsem4.BookingService.entity.BookingReferee;
import com.projectsem4.BookingService.model.request.CreateBookingRequest;
import com.projectsem4.BookingService.repository.BookingAccessoryRepository;
import com.projectsem4.BookingService.repository.BookingRefereeRepository;
import com.projectsem4.BookingService.repository.BookingRepository;
import com.projectsem4.BookingService.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final BookingAccessoryRepository bookingAccessoryRepository;
    private final BookingRefereeRepository bookingRefereeRepository;

    @Override
    public Boolean createBooking(CreateBookingRequest request) {
        Booking booking = new Booking();
        booking.setFieldId(request.getFieldId());
        booking.setUserId(request.getUserId());
        booking.setBookingDate(request.getBookingDate());
        booking.setStartTime(request.getStartTime());
        booking.setEndTime(request.getEndTime());
        bookingRepository.save(booking);
        bookingAccessoryRepository.saveAll(request.getBookingAccessory());
        bookingRefereeRepository.saveAll(request.getBookingReferees());
        return true;
    }

    @Override
    public Object findBookingById(Long id) {
        Booking booking = bookingRepository.findById(id).get();
        List<BookingAccessory> bookingAccessory = bookingAccessoryRepository.findByBookingId(id);
        List<BookingReferee> bookingReferee = bookingRefereeRepository.findByBookingId(id);
        CreateBookingRequest createBookingRequest = new CreateBookingRequest();
        createBookingRequest.setBookingId(booking.getId());
        createBookingRequest.setFieldId(booking.getFieldId());
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
        booking.setStatus(status);
        return null;
    }
}
