package com.projectsem4.BookingService.repository;

import com.projectsem4.BookingService.entity.BookingPeriod;
import com.projectsem4.BookingService.entity.BookingReferee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingPeriodRepository extends JpaRepository<BookingPeriod, Long> {
    BookingPeriod findByBookingId(Long bookingId);
}
