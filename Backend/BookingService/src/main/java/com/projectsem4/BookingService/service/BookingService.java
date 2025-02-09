package com.projectsem4.BookingService.service;

import com.projectsem4.BookingService.model.request.CreateBookingRequest;

public interface BookingService {
    void createBooking(CreateBookingRequest request);
    CreateBookingRequest findBookingById(Long id);
    Object updateStatusOderByPayment(Integer status, Long orderId);
}
