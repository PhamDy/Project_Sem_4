package com.projectsem4.BookingService.service;

import com.projectsem4.BookingService.model.request.CreateBookingRequest;

public interface BookingService {
    Boolean createBooking(CreateBookingRequest request);
    Object findBookingById(Long id);
    Object updateStatusOderByPayment(Integer status, Long orderId);
}
