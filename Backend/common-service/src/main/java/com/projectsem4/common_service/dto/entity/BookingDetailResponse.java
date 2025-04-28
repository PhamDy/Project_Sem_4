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
public class BookingDetailResponse {
    private Long id;
    private LocalDate bookingDate;
    private Long timeFrame;
    private Long fieldTypeId;
    private Long quantity;
    private Long bookingId;
    private Long price;
    private FieldTypeResponse fieldType;
    private AreaCreateRequest areaResponse;
}
