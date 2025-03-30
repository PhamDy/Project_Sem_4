package com.projectsem4.common_service.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FieldSchedule {
    Long fieldId;
    Long timeFrame;
//    String fieldName;
    Long quantity;
//    LocalDate date;
    Long price;
}
