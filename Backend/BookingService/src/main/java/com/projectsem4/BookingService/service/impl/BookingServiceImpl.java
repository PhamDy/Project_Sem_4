package com.projectsem4.BookingService.service.impl;

import com.projectsem4.BookingService.entity.Booking;
import com.projectsem4.BookingService.model.request.CreateBookingRequest;
import com.projectsem4.BookingService.repository.BookingAccessoryRepository;
import com.projectsem4.BookingService.repository.BookingRefereeRepository;
import com.projectsem4.BookingService.repository.BookingRepository;
import com.projectsem4.BookingService.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        return bookingRepository.findById(id).get();
    }
}
