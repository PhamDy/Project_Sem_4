package com.projectsem4.BookingService.service;

import com.projectsem4.BookingService.entity.Booking;
import com.projectsem4.BookingService.model.CreateBookingRequest;

public interface BookingService {
    Boolean createBooking(CreateBookingRequest request);
}
