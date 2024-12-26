package com.projectsem4.TournamentService.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "tournament_service", schema = "project")
public class TournamentService extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tournament_service_id", nullable = false)
    private Long id;

    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;

    @Size(max = 500)
    @Column(name = "desscription", length = 500)
    private String desscription;

    @Column(name = "price", precision = 10)
    private BigDecimal price;

    @Column(name = "type")
    private Integer type;

}