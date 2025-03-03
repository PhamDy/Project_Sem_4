package com.projectsem4.BookingService.model.request;

import com.projectsem4.BookingService.entity.BookingAccessory;
import com.projectsem4.BookingService.entity.BookingDetail;
import com.projectsem4.BookingService.entity.BookingReferee;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookingRequest {
    private Long bookingId;
    private Long fieldId;
    private Long userId;
    private LocalDate bookingDate;
    private Long timeFrameId;
    private BigDecimal totalPrice;
    private String paymentStatus;
    private Integer paymentMethod;
    private List<BookingAccessory> bookingAccessory;
    private List<BookingReferee> bookingReferees;
    private List<BookingDetail> bookingDetails;
}
