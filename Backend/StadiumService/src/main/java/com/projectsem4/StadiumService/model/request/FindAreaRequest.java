package com.projectsem4.StadiumService.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindAreaRequest {
    Double latitude;
    Double longitude;
    Integer distance;
    Long size;
    String timeStart;
    String timeEnd;
    Integer district;
    Long price;
}
