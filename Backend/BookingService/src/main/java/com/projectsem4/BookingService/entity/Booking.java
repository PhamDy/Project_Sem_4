package com.projectsem4.BookingService.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "booking", schema = "project")
public class Booking extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id", nullable = false)
    private Long id;

    @Column(name = "field_id")
    private Long fieldId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "booking_date")
    private LocalDate bookingDate;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "total_price", precision = 10)
    private BigDecimal totalPrice;

    @Size(max = 100)
    @Column(name = "payment_status", length = 100)
    private String paymentStatus;

    @Column(name = "payment_method")
    private Integer paymentMethod;

}