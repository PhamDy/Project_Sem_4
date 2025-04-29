package com.projectsem4.BookingService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "booking_tournament", schema = "project")
public class BookingTournament extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_tournament_id", nullable = false)
    private Long id;

    private String registerName;
    private String tournamentName;
    private Long numberTeam;
    private Long numberMatch;
    private LocalDate startDay;
    private LocalDate endDay;
    private String email;
    private String phoneNumber;
    private Boolean referee;
    private String banner;
    private Boolean photo;
    private Long jersey;
    private Boolean water;
    private Boolean speaker;
    private Boolean mc;
    private Boolean opening;
    private Long bookingId;
    private Long price;
    private Long quantity;
}
