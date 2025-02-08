package com.projectsem4.PaymentService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/api/v1")
public class PaymentPublicController {

    @GetMapping
    public String linkThanhToan() {
        return "https://www.google.com/";
    }

}
