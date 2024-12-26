package com.projectsem4.ProductService.service;

import com.projectsem4.ProductService.entity.Product;
import com.projectsem4.ProductService.model.request.CreateProductRequest;

public interface ProductService {
    Boolean createProduct(CreateProductRequest request);
}
