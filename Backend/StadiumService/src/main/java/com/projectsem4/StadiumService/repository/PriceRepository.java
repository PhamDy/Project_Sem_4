package com.projectsem4.StadiumService.repository;

import com.projectsem4.StadiumService.entity.TimeFrame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<TimeFrame, Long> {
    List<TimeFrame> findByFieldId(Long id);
}
