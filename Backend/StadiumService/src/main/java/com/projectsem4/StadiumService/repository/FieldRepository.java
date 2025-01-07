package com.projectsem4.StadiumService.repository;

import com.projectsem4.StadiumService.model.entity.Field;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long>, JpaSpecificationExecutor<Field> {
    List<Field> findByAreaId(Long id);
    Page<Field> findAll(Pageable pageable, Specification<Field> specification);
}
