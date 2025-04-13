package com.projectsem4.BookingService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "booking_detail", schema = "project")
public class BookingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_detail_id", nullable = false)
    private Long id;

    @Column(name = "booking_date")
    private LocalDate bookingDate;

    @Column(name = "time_frame")
    private Long timeFrame;

    @Column(name = "field_type_id")
    private Long fieldTypeId;

    @Column(name = "quantity")
    private Long quantity;

}
