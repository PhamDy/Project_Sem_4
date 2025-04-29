package com.projectsem4.BookingService.repository;

import com.projectsem4.BookingService.entity.Booking;
import com.projectsem4.BookingService.entity.BookingTournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingTournamentRepository extends JpaRepository<BookingTournament, Long> {
}