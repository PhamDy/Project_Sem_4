package com.projectsem4.StadiumService.repository;

import com.projectsem4.StadiumService.entity.Area;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {
    Page<Area> findAllByOrderByNameAsc(Pageable pageable);
}
