package com.projectsem4.TournamentService.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "tournament_booking", schema = "project")
public class TournamentBooking extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tounament_booking_id", nullable = false)
    private Long id;

    @Size(max = 500)
    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "match_total")
    private Integer matchTotal;

    @Column(name = "field_id")
    private Long fieldId;

    @Column(name = "type")
    private Integer type;

    @Column(name = "field_quantity")
    private Integer fieldQuantity;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "price", precision = 10)
    private BigDecimal price;

}