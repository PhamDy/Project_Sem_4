package com.projectsem4.StadiumService.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaCreateRequest {
    Long areaId;
    String name;
    String address;
    Double rating;
    String description;
    String phoneNumber;
    String email;
    List<FieldRequest> fields;

}
