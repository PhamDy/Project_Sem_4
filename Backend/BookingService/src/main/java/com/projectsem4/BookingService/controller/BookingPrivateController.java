package com.projectsem4.BookingService.controller;

import com.projectsem4.BookingService.model.request.CreateBookingRequest;
import com.projectsem4.BookingService.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/find-by-id/{id}")
    public Object getById(@PathVariable Long id) {
        return bookingService.findBookingById(id);
    }

    @GetMapping("/update-status-by-payment")
    public Object updateStatusByPayment(@RequestParam(name = "status") Integer status,
                                        @RequestParam(name= "orderId")  Long id) {
        return bookingService.updateStatusOderByPayment(status,id);
    }
}
