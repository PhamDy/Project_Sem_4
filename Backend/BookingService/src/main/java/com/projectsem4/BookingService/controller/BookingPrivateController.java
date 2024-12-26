package com.projectsem4.BookingService.controller;

import com.projectsem4.BookingService.model.CreateBookingRequest;
import com.projectsem4.BookingService.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/api/v1")
public class BookingPrivateController {
    private final BookingService bookingService;

    @GetMapping
    public String test() {
        return "Booking Service";
    }

    @PostMapping("/create")
    public void create(@RequestBody CreateBookingRequest booking) {
        bookingService.createBooking(booking);
    }

}
