package com.projectsem4.TournamentService.service;

import com.projectsem4.TournamentService.model.request.CreateTourBooking;

public interface BookingService {
    Boolean createBooking(CreateTourBooking request);
}
