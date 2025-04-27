package com.projectsem4.common_service.dto.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter

public class BookingAccessory extends BaseEntity {

    private Long id;

    private Long bookingId;

    private Long accessoryId;

    private BigDecimal price;

    private Integer quantity;

}