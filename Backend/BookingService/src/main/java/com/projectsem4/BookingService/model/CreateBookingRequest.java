package com.projectsem4.BookingService.model;

import com.projectsem4.BookingService.entity.BookingAccessory;
import com.projectsem4.BookingService.entity.BookingReferee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookingRequest {
    private Long fieldId;
    private Long userId;
    private LocalDate bookingDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private BigDecimal totalPrice;
    private String paymentStatus;
    private Integer paymentMethod;
    private List<BookingAccessory> bookingAccessory;
    private List<BookingReferee> bookingReferees;
}
