package com.projectsem4.common_service.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FieldDateSchedule {
    Boolean isBooking;
    LocalDate date;
    Long price;
}
