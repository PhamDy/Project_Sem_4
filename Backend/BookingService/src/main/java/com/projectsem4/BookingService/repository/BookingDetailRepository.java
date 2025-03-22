package com.projectsem4.BookingService.repository;

import com.projectsem4.BookingService.entity.Booking;
import com.projectsem4.BookingService.entity.BookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface BookingDetailRepository extends JpaRepository<BookingDetail, Long> {
    @Query("SELECT b FROM BookingDetail b WHERE "
            + "(:fieldId IS NULL OR :fieldId = b.fieldId) "
            + "AND (:timeFrame IS NULL OR :timeFrame = b.timeFrame)")
    List<BookingDetail> checkBookingField(@Param("fieldId") Long fieldId,
                                    @Param("timeFrame") Long timeFrame);
}
