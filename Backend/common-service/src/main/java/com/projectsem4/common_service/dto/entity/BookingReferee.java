package com.projectsem4.common_service.dto.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BookingReferee extends BaseEntity {

    private Long bookingRefereeId;

    private Long bookingId;

    private Long refereeId;

    private BigDecimal price;

}