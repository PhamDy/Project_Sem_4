package com.projectsem4.BookingService.model.request;

import com.projectsem4.BookingService.entity.*;
import com.projectsem4.common_service.dto.entity.AreaResponse;
import com.projectsem4.common_service.dto.entity.BookingDetailResponse;
import com.projectsem4.common_service.dto.entity.FieldType;
import com.projectsem4.common_service.dto.entity.FieldTypeResponse;
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
    private Long userId;
    private Long totalPrice;
    private String paymentStatus;
    private Integer paymentMethod;
    private List<BookingAccessory> bookingAccessory;
    private List<BookingReferee> bookingReferees;
    private List<BookingDetail> bookingDetails;
    private List<BookingDetailResponse> bookingDetailResponses;
    private BookingPeriod bookingPeriods;
    private BookingTournament bookingTournament;
    String url;
}
