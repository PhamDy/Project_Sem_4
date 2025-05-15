package com.projectsem4.StadiumService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FieldType extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "field_type_id")
    private Long fieldTypeId;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "quantity")
    private Long quantity;

    @Basic
    @Column(name = "size")
    private Long size;

    @Basic
    @Column(name = "area_id")
    private Long areaId;

    @Basic
    @Column(name = "price")
    private Long price;

    @Basic
    @Column(name = "Rating")
    private Double rating;

}
