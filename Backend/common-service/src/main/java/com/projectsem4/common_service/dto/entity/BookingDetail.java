package com.projectsem4.common_service.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetail {
    private Long id;
    private LocalDate bookingDate;
    private Long timeFrame;
    private Long fieldTypeId;
    private Long quantity;
    private Long bookingId;
}
