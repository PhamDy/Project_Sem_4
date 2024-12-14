package com.projectsem4.StadiumService.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Field extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "field_id")
    private Long fieldId;

    @Basic
    @Column(name = "name")
    private String name;

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
    @Column(name = "size")
    private Integer size;

    @Basic
    @Column(name = "area_id")
    private Long areaId;
}
