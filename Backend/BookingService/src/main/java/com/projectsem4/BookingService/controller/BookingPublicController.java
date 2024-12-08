package com.projectsem4.BookingService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/api/v1")
public class BookingPublicController {

    @GetMapping
    public String test() {
        return "Booking Service";
    }

}
