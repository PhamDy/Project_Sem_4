package com.projectsem4.BookingService.controller;

import com.projectsem4.BookingService.model.request.CreateBookingRequest;
import com.projectsem4.BookingService.service.BookingService;
import com.projectsem4.common_service.dto.entity.Price;
import com.projectsem4.common_service.dto.entity.TimeFrameDate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/calender")
    public Map<TimeFrameDate,Boolean> calenderSchedule(@RequestBody List<Long> fieldId, @RequestParam LocalDate date) {
        return bookingService.scheduleClient(fieldId, date);
    }
}
