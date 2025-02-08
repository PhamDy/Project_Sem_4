package com.projectsem4.BookingService.controller;

import com.projectsem4.BookingService.client.PaymentServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final PaymentServiceClient paymentServiceClient;

    @GetMapping
    public String test() {
        return paymentServiceClient.linkThanhToan();
    }

}
