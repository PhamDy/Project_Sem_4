package com.projectsem4.StadiumService.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldRequest {
    private Long fieldId;
    private String name;
    private Double rating;
    private String description;
    private String phoneNumber;
    private String email;
    private Integer size;
    private Long areaId;
    private List<PriceRequest> prices;
}
