package com.projectsem4.StadiumService.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Accessory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "accessory_id")
    private String accessoryId;

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
    @Column(name = "area_id")
    private String areaId;

    @Basic
    @Column(name = "type")
    private Integer type;

    @Basic
    @Column(name = "category_accessory_id")
    private String categoryAccessoryId;

    @Basic
    @Column(name = "quantity")
    private String quantity;

    @Basic
    @Column(name = "price")
    private BigDecimal price;
}
