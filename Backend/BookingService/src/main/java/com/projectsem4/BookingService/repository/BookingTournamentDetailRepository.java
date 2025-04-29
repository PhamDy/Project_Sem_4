package com.projectsem4.BookingService.repository;

import com.projectsem4.BookingService.entity.BookingTournament;
import com.projectsem4.BookingService.entity.BookingTournamentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingTournamentDetailRepository extends JpaRepository<BookingTournamentDetail, Long> {
}