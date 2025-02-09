package com.projectsem4.BookingService.repository;

import com.projectsem4.BookingService.entity.BookingAccessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface BookingAccessoryRepository extends JpaRepository<BookingAccessory, Long> {
    List<BookingAccessory> findByBookingId(long bookingId);

    @Query("SELECT b FROM BookingAccessory b WHERE "
            +"(:bookingId IS NULL OR :bookingId = b.bookingId) "
            +"AND (:accessoryId IS NULL OR :accessoryId = b.accessoryId)"
            +"AND (:quantity IS NULL OR :quantity = b.quantity)")
    List<BookingAccessory> checkBookingAccessory(@Param("bookingId") Long bookingId,
                                                 @Param("accessoryId") Long accessoryId,
                                                 @Param("quantity") Integer quantity);
}
