package com.projectsem4.common_service.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Price extends BaseEntity {

    private Long priceId;

    private LocalTime priceFrom;

    private LocalTime priceTo;

    private BigDecimal price;

    private Long fieldId;

    private Long quantity;
}