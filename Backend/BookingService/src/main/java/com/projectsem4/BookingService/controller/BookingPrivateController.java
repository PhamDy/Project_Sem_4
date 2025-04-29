package com.projectsem4.BookingService.controller;

import com.projectsem4.BookingService.entity.BookingDetail;
import com.projectsem4.BookingService.entity.BookingPeriod;
import com.projectsem4.BookingService.model.request.CreateBookingRequest;
import com.projectsem4.BookingService.model.request.CreateBookingTournament;
import com.projectsem4.BookingService.service.BookingService;
import com.projectsem4.common_service.dto.entity.Price;
import com.projectsem4.common_service.dto.entity.TimeFrameDate;
import com.projectsem4.common_service.dto.entity.TimeFrameSchedule;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
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
        @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> create(@RequestBody CreateBookingRequest booking, HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(bookingService.createBooking(booking, httpServletRequest));
    }

    @PostMapping("/create-period")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> create(@RequestBody BookingPeriod booking, HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(bookingService.createBookingPeriod(booking, httpServletRequest));
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
    public List<TimeFrameSchedule> calenderSchedule(@RequestParam Long fieldId, @RequestParam String date) {
        return bookingService.scheduleClient(fieldId, date);
    }

    @GetMapping("/bookingDetail/{bookingId}")
    public List<BookingDetail> findByBookingId(@PathVariable Long bookingId) {
        return bookingService.findByBookingId(bookingId);
    }

    @GetMapping("/booking")
    public ResponseEntity<?> findAllByUser(HttpServletRequest request) {
        return ResponseEntity.ok(bookingService.findAllBookings(request));
    }


    @PostMapping("/validate-period")
    public List<TimeFrameSchedule> validatePeriod(@RequestParam Long fieldId, @RequestParam List<String> date) {
        return bookingService.scheduleClientPeriod(fieldId, date);
    }

    @PostMapping("/create-tournament")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> createTournament(@RequestBody CreateBookingTournament booking, HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(bookingService.createTournament(booking, httpServletRequest));
    }

    @PostMapping("/validate-tournament")
    public List<TimeFrameSchedule> validateTournament(@RequestParam List<Long> fieldId, @RequestParam List<String> date) {
        return bookingService.scheduleClientPeriod(fieldId, date);
    }
}
