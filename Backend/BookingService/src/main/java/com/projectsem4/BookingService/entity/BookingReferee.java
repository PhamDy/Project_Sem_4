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
@Table(name = "booking_referee", schema = "project")
public class BookingReferee extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_referee_id")
    private Long bookingRefereeId;

    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "referee_id")
    private Long refereeId;

    @Column(name = "price", precision = 10)
    private BigDecimal price;

}