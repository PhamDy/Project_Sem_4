package com.projectsem4.BookingService.service;

import com.projectsem4.BookingService.model.request.CreateBookingRequest;
import com.projectsem4.common_service.dto.entity.Price;
import com.projectsem4.common_service.dto.entity.TimeFrameDate;
import com.projectsem4.common_service.dto.entity.TimeFrameSchedule;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface BookingService {
    void createBooking(CreateBookingRequest request);
    CreateBookingRequest findBookingById(Long id);
    Object updateStatusOderByPayment(Integer status, Long orderId);
<<<<<<< Updated upstream
    List<TimeFrameSchedule> scheduleClient(Long fieldIds, LocalDate date);
=======
    List<TimeFrameDate> scheduleClient(List<Long> fieldIds, LocalDate date);
>>>>>>> Stashed changes
}
