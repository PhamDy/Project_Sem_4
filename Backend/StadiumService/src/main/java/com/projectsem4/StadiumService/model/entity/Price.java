package com.projectsem4.StadiumService.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Price extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id")
    private Long priceId;

    @Basic
    @Column(name = "price_from")
    private LocalTime priceFrom;

    @Basic
    @Column(name = "price_to")
    private LocalTime priceTo;

    @Basic
    @Column(name = "price")
    private BigDecimal price;

    @Basic
    @Column(name = "field_id")
    private Long fieldId;
}
