package com.projectsem4.StadiumService.repository;

import com.projectsem4.StadiumService.model.entity.Field;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long>, JpaSpecificationExecutor<Field> {
    List<Field> findByAreaId(Long id);
    @Query(value = "CALL search_field(:latitude, :longitude, :distance, :size, :timeStart, :timeEnd, :price)", nativeQuery = true)
    List<Field> searchField(
            @Param("latitude") Double latitude,
            @Param("longitude") Double longitude,
            @Param("distance") Integer distance,
            @Param("size") Long size,
            @Param("timeStart") String timeStart,
            @Param("timeEnd") String timeEnd,
            @Param("price") BigDecimal price
    );
}
