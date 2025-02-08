package com.projectsem4.TournamentService.service.impl;

import com.projectsem4.TournamentService.entity.TournamentBooking;
import com.projectsem4.TournamentService.model.request.CreateTourBooking;
import com.projectsem4.TournamentService.repository.TournamentBookingRepository;
import com.projectsem4.TournamentService.repository.TournamentServiceBookingRepository;
import com.projectsem4.TournamentService.repository.TournamentServiceRepository;
import com.projectsem4.TournamentService.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final TournamentBookingRepository tournamentBookingRepository;
    private final TournamentServiceBookingRepository tournamentServiceBookingRepository;
    private final TournamentServiceRepository tournamentServiceRepository;
    @Override
    public Boolean createBooking(CreateTourBooking request) {
        TournamentBooking tournamentBooking = new TournamentBooking();
        tournamentBooking.setDescription(request.getDescription());
        tournamentBooking.setPrice(request.getPrice());
        tournamentBooking.setMatchTotal(request.getMatchTotal());
        tournamentBooking.setFieldId(request.getFieldId());
        tournamentBooking.setType(request.getType());
        tournamentBooking.setFieldQuantity(request.getFieldQuantity());
        tournamentBooking.setUserId(request.getUserId());
        tournamentBookingRepository.save(tournamentBooking);
        request.getBookingServiceList().forEach(requestBooking -> {
            requestBooking.setTounamentBookingId(tournamentBooking.getId());
            tournamentServiceBookingRepository.save(requestBooking);
        });
        return true;
    }
}
