package com.projectsem4.common_service.dto.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class BookingDetail {

    private Long id;

    private LocalDate bookingDate;

    private Long timeFrame;

    private Long fieldTypeId;

    private Long quantity;

    private Long bookingId;
}
