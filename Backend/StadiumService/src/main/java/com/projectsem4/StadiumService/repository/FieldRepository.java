package com.projectsem4.StadiumService.repository;

import com.projectsem4.StadiumService.model.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {
    List<Field> findByAreaId(Long id);
}
