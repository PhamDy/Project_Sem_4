package com.projectsem4.TournamentService.repository;

import com.projectsem4.TournamentService.entity.TournamentBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentBookingRepository extends JpaRepository<TournamentBooking, Long> {
}
