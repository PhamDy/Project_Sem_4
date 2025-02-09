package com.projectsem4.BookingService.repository;

import com.projectsem4.BookingService.entity.BookingReferee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRefereeRepository extends JpaRepository<BookingReferee, Long> {
    List<BookingReferee> findByBookingId(Long bookingId);
}
