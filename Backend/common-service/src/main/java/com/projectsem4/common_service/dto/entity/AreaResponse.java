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
public class AreaResponse {
    private Long areaId;
    private String name;
    private String address;
    private Double rating;
    private String description;
    private String phoneNumber;
    private String email;
    private Double longitude;
    private Double latitude;
    private Integer district;
    private List<FieldTypeResponse> fieldTypeResponseList;
}
