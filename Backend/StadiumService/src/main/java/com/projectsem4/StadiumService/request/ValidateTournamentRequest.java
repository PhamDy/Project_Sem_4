package com.projectsem4.StadiumService.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateTournamentRequest {
    Long fieldId;
    Long quantity;
    LocalDate startDate;
    LocalDate endDate;
}
