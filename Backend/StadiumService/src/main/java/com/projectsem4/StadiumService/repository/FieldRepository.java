package com.projectsem4.StadiumService.repository;

import com.projectsem4.StadiumService.entity.FieldType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface FieldRepository extends JpaRepository<FieldType, Long>, JpaSpecificationExecutor<FieldType> {
    List<FieldType> findByAreaId(Long id);
    @Query(value = "CALL search_field(:latitude, :longitude, :distance, :size, :timeStart, :timeEnd, :district, :price)", nativeQuery = true)
    List<FieldType> searchField(
            @Param("latitude") Double latitude,
            @Param("longitude") Double longitude,
            @Param("distance") Integer distance,
            @Param("size") Long size,
            @Param("timeStart") String timeStart,
            @Param("timeEnd") String timeEnd,
            @Param("district") Integer district,
            @Param("price") BigDecimal price
    );
}
