package com.projectsem4.ProductService.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "category_product", schema = "project")
public class CategoryProduct extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_product_id", nullable = false)
    private Long id;

    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "category_parent")
    private Long categoryParent;

}