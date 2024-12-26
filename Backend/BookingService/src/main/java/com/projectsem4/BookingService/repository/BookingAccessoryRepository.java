package com.projectsem4.BookingService.repository;

import com.projectsem4.BookingService.entity.BookingAccessory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingAccessoryRepository extends CrudRepository<BookingAccessory, Long> {
}
