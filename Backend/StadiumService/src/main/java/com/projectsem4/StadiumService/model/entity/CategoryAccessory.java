package com.projectsem4.StadiumService.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CategoryAccessory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "category_accessory_id")
    private String categoryAccessoryId;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "parent_id")
    private String parentId;
}
