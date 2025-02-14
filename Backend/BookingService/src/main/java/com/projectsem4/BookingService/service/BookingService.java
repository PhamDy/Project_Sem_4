package com.projectsem4.BookingService.service;

import com.projectsem4.BookingService.model.request.CreateBookingRequest;
import com.projectsem4.common_service.dto.entity.Price;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    void createBooking(CreateBookingRequest request);
    CreateBookingRequest findBookingById(Long id);
    Object updateStatusOderByPayment(Integer status, Long orderId);
    List<Price> findTimeAvailable(List<Price> price, LocalDate date, Long quantity);
}
