package com.projectsem4.BookingService.repository;

import com.projectsem4.BookingService.entity.BookingReferee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRefereeRepository extends CrudRepository<BookingReferee, Long> {
}
