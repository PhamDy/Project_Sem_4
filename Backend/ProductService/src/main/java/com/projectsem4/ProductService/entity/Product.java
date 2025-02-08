package com.projectsem4.ProductService.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "product", schema = "project")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long id;

    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;

    @Size(max = 100)
    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "price", precision = 10)
    private BigDecimal price;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "area_id")
    private Long areaId;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "category_product_id")
    private Long categoryProductId;

}