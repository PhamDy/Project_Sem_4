package com.projectsem4.StadiumService.repository;

import com.projectsem4.StadiumService.model.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
    List<Price> findByFieldId(Long id);
}
