package com.projectsem4.StadiumService.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidatePeriodRequest {
    Long fieldId;
    Long weekDay;
    Long quantity;
    LocalDate date;
}
