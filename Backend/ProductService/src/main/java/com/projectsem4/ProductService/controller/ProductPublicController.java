package com.projectsem4.ProductService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/api/v1")
public class ProductPublicController {

    @GetMapping
    public String test() {
        return "Product Service";
    }

}
