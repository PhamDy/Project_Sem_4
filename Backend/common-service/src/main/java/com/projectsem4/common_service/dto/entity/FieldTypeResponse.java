package com.projectsem4.common_service.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FieldTypeResponse {
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
