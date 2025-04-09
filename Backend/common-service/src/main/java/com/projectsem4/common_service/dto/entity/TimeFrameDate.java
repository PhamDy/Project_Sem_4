package com.projectsem4.common_service.dto.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeFrameDate {
    private Long fieldId;
    private Long timeFrame;
    private String date;
    private Long quantity;
}
