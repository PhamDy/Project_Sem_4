package com.projectsem4.common_service.dto.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class BookingPeriod {

    private Long id;

    private Long bookingId;

    private Long userId;

    private Long fieldTypeId;

    private Long timeFrame;

    private Long weekDay;

    private Long quantity;

    private LocalDate month;

    private Long price;
}
