package com.projectsem4.ProductService.service.impl;

import com.projectsem4.ProductService.entity.Product;
import com.projectsem4.ProductService.model.request.CreateProductRequest;
import com.projectsem4.ProductService.repository.ProductRepository;
import com.projectsem4.ProductService.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Boolean createProduct(CreateProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setQuantity(request.getQuantity());
        product.setPrice(request.getPrice());
        product.setAreaId(request.getAreaId());
        product.setRating(0.0);
        productRepository.save(product);
        return true;
    }
}
