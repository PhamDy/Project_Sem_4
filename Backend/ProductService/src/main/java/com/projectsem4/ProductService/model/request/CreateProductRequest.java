package com.projectsem4.ProductService.model.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private Long quantity;
    private Long areaId;
    private Double rating;
    private Long categoryProductId;
}
