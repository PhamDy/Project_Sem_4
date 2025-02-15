package com.projectsem4.common_service.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FieldType extends BaseEntity {

    private Long fieldId;

    private String name;

    private Double rating;

    private String description;

    private String phoneNumber;

    private String email;

    private Long quantity;

    private Long size;

    private Long areaId;
}
