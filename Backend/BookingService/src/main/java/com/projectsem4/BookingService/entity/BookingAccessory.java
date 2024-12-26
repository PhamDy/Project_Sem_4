package com.projectsem4.BookingService.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "booking_accessory", schema = "project")
public class BookingAccessory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_accessory_id", nullable = false)
    private Long id;

    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "accessory_id")
    private Long accessoryId;

    @Column(name = "price", precision = 10)
    private BigDecimal price;

    @Column(name = "quantity")
    private Integer quantity;

}