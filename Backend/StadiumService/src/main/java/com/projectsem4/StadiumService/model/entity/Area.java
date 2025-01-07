package com.projectsem4.StadiumService.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Area extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_id")
    private Long areaId;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "address")
    private String address;

    @Basic
    @Column(name = "rating")
    private Double rating;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "phone_number")
    private String phoneNumber;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "longitude")
    private Double longitude;

    @Basic
    @Column(name = "latitude")
    private Double latitude;

    @Basic
    @Column(name = "path")
    private String path;
}
