package com.projectsem4.StadiumService.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceRequest {
    private Long priceId;
    private LocalTime priceFrom;
    private LocalTime priceTo;
    private BigDecimal price;
    private Long fieldId;
}
