package com.projectsem4.StadiumService.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindAreaRequest {
    private BigDecimal price;
    private Double longitude;
    private Double latitude;
    private Integer distance = 5;
    private Long size;
}
