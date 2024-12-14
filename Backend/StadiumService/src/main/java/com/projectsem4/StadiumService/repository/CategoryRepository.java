package com.projectsem4.StadiumService.repository;

import com.projectsem4.StadiumService.model.entity.CategoryAccessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryAccessory, Long> {
}
