package com.projectsem4.common_service.dto.response;

import com.projectsem4.common_service.dto.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private List<BookingPeriod> bookingPeriods;
    String url;
}
