package com.projectsem4.TournamentService.controller;

import com.projectsem4.TournamentService.entity.TournamentService;
import com.projectsem4.TournamentService.model.request.CreateTourBooking;
import com.projectsem4.TournamentService.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/api/v1")
public class TournamentPrivateController {
    private final BookingService bookingService;

    @GetMapping
    public String test() {
        return "Tournament Service";
    }

    @PostMapping("/create-booking-tournamnent")
    public void createBookingTournament(@RequestBody CreateTourBooking tourBooking) {
        bookingService.createBooking(tourBooking);
    }

}
