package com.projectsem4.common_service.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FieldResponse {
    private Long fieldId;

    private String name;

    private String description;

    private Long fieldTypeId;

    private Double rating;
}
