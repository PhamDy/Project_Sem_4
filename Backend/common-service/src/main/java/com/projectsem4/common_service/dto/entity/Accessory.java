package com.projectsem4.common_service.dto.entity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Accessory extends BaseEntity {
    private Long accessoryId;
    private String name;
    private Double rating;
    private String description;
    private Long areaId;
    private Integer type;
    private String categoryAccessoryId;
    private Long quantity;
    private BigDecimal price;
}
