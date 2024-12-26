package com.projectsem4.TournamentService.repository;

import com.projectsem4.TournamentService.entity.TournamentServiceBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentServiceBookingRepository extends JpaRepository<TournamentServiceBooking, Long> {
}
