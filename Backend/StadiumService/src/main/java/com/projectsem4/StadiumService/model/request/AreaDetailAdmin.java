package com.projectsem4.StadiumService.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaDetailAdmin {
    Long areaId;
    String name;
    String address;
    Double rating;
    String description;
    String phoneNumber;
    Double longitude;
    Double latitude;
    Integer district;
    String path;
    String email;
    List<FieldTypeRequest> fields;

}
