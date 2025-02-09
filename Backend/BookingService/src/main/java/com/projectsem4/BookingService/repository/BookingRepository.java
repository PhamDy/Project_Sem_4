package com.projectsem4.BookingService.repository;

import com.projectsem4.BookingService.entity.Booking;
import com.projectsem4.BookingService.entity.BookingAccessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b WHERE "
            +"(:fieldId IS NULL OR :fieldId = b.fieldId) "
            +"AND (:bookingDate IS NULL OR :bookingDate = b.bookingDate)"
            +"AND ((b.endTime BETWEEN :startTime AND :endTime) OR (b.startTime BETWEEN :startTime AND :endTime) "
            +"OR (b.endTime >= :endTime AND b.startTime <= :startTime))")
    List<Booking> checkBookingField(@Param("fieldId") Long fieldId,
                                    @Param("bookingDate") LocalDate bookingDate,
                                    @Param("endTime") LocalTime endTime,
                                    @Param("startTime") LocalTime startTime);

    @Query("SELECT b FROM Booking b WHERE "
            +"(:fieldId IS NULL OR :fieldId = b.fieldId) "
            +"AND (:bookingDate IS NULL OR :bookingDate = b.bookingDate)"
            +"AND ((b.endTime BETWEEN :startTime AND :endTime) OR (b.startTime BETWEEN :startTime AND :endTime) "
            +"OR (b.endTime >= :endTime AND b.startTime <= :startTime))")
    List<BookingAccessory> checkBookingAccessory(@Param("fieldId") Long fieldId,
                                             @Param("bookingDate") LocalDate bookingDate,
                                             @Param("endTime") LocalTime endTime,
                                             @Param("startTime") LocalTime startTime);
}
