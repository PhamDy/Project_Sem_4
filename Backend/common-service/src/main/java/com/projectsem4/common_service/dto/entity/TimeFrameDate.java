package com.projectsem4.common_service.dto.entity;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeFrameDate {
    private Long fieldId;
    private Long timeFrame;
    private LocalDate date;
}
