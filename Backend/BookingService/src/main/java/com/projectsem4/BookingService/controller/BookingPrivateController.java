package com.projectsem4.BookingService.controller;

import com.projectsem4.BookingService.model.request.CreateBookingRequest;
import com.projectsem4.BookingService.service.BookingService;
import com.projectsem4.common_service.dto.entity.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/private/api/v1")
public class BookingPrivateController {
    @Autowired
    private BookingService bookingService;

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

    @PostMapping("/check-valid-time")
    public List<Price> validTime(@RequestParam LocalDate date, @RequestBody List<Price> price) {
        return bookingService.findTimeAvailable(price, date);
    }
}
