package com.projectsem4.StadiumService.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindAreaRequest {
    private BigDecimal price;
    private String areaName;
    private Long size;
}
