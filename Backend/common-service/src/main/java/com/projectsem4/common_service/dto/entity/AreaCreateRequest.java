package com.projectsem4.common_service.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaCreateRequest {

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
    private List<FileDb> fileList;
}
