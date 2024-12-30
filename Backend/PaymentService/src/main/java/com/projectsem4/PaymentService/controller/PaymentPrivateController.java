package com.projectsem4.PaymentService.controller;

import com.projectsem4.PaymentService.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/api/v1")
public class PaymentPrivateController {
    private final PaymentService paymentService;

    @PostMapping("/vn-pay/{id}")
    public String createLink(@PathVariable Long id) throws UnsupportedEncodingException {
        String baseUrl = "http://localhost:8080";
        return paymentService.creatPayment(baseUrl,id);
    }

    @GetMapping
    public String test() {
        return "Payment Service";
    }

}
