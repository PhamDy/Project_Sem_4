package com.projectsem4.BookingService.repository;

import com.projectsem4.BookingService.entity.BookingAccessory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingAccessoryRepository extends CrudRepository<BookingAccessory, Long> {
    List<BookingAccessory> findByBookingId(long bookingId);
}
