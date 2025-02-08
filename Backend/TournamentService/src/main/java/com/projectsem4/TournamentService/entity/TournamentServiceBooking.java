package com.projectsem4.TournamentService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "tournament_service_booking", schema = "project")
public class TournamentServiceBooking extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tounament_service_booking_id", nullable = false)
    private Long id;

    @Column(name = "tounament_booking_id")
    private Long tounamentBookingId;

    @Column(name = "tournament_service_id")
    private Long tournamentServiceId;

    @Column(name = "price", precision = 10)
    private BigDecimal price;

}