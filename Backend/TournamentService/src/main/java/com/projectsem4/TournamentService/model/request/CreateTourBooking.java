package com.projectsem4.TournamentService.model.request;

import com.projectsem4.TournamentService.entity.TournamentServiceBooking;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTourBooking {
    private String description;
    private Integer matchTotal;
    private Long fieldId;
    private Integer type;
    private Integer fieldQuantity;
    private Long userId;
    private BigDecimal price;
    private List<TournamentServiceBooking> bookingServiceList;
}
