package com.projectsem4.BookingService.service;

import com.projectsem4.BookingService.entity.BookingDetail;
import com.projectsem4.BookingService.entity.BookingPeriod;
import com.projectsem4.BookingService.model.request.CreateBookingRequest;
import com.projectsem4.common_service.dto.entity.Price;
import com.projectsem4.common_service.dto.entity.TimeFrameDate;
import com.projectsem4.common_service.dto.entity.TimeFrameSchedule;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface BookingService {
    Object createBooking(CreateBookingRequest request, HttpServletRequest httpServletRequest);
    Object createBookingPeriod(BookingPeriod request, HttpServletRequest httpServletRequest);
    CreateBookingRequest findBookingById(Long id);
    Object updateStatusOderByPayment(Integer status, Long orderId);
    List<TimeFrameSchedule> scheduleClient(Long fieldIds, String date);
    List<TimeFrameSchedule> scheduleClientPeriod(Long fieldId, List<String> dateString);
    List<CreateBookingRequest> findAllBookings(HttpServletRequest request);
    List<BookingDetail> findByBookingId(Long bookingId);
}
