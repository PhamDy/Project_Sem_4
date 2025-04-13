package com.projectsem4.common_service.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking extends BaseEntity {
    private Long id;

    private Long fieldId;

    private Long userId;

    private LocalDate bookingDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private Long totalPrice;

    private String paymentStatus;

    private Integer paymentMethod;

}