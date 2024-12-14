package com.projectsem4.StadiumService.repository;

import com.projectsem4.StadiumService.model.entity.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoryRepository extends JpaRepository<Accessory, Long> {
}
