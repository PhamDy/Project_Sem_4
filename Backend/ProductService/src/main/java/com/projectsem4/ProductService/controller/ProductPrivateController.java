package com.projectsem4.ProductService.controller;

import com.projectsem4.ProductService.model.request.CreateProductRequest;
import com.projectsem4.ProductService.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/api/v1")
public class ProductPrivateController {
    private final ProductService productService;

    @GetMapping
    public String test() {
        return "Product Service";
    }

    @PostMapping("/create")
    public void create(@RequestBody CreateProductRequest product) {
        productService.createProduct(product);
    }

}
