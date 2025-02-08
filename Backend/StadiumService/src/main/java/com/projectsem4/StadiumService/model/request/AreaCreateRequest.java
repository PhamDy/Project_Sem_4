package com.projectsem4.StadiumService.model.request;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
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
    Double longitude;
    Double latitude;
    String path;
    String email;
    List<FieldRequest> fields;

}
