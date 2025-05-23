package com.projectsem4.BookingService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "booking_period", schema = "project")
public class BookingPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_period_id", nullable = false)
    private Long id;

    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "field_type_id")
    private Long fieldTypeId;

    @Column(name = "time_frame")
    private Long timeFrame;

    @Column(name = "week_day")
    private Long weekDay;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "month")
    private LocalDate month;

    @Column(name = "price")
    private Long price;
}
