package com.projectsem4.BookingService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Entity
@Table(name = "booking_tournament_detail_time", schema = "project")
public class BookingTournamentDetailTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_tournament_id_detail", nullable = false)
    private Long id;

    @Column(name = "booking_tournament_id")
    private Long bookingTournamentId;

    private Long timeFrame;

}
