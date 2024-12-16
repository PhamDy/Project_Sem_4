package com.projectsem4.StadiumService.service;

import com.projectsem4.StadiumService.model.request.AreaCreateRequest;
import com.projectsem4.StadiumService.model.request.FindAreaRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.projectsem4.StadiumService.model.entity.Area;

public interface AreaService {
    Boolean createArea(AreaCreateRequest areaCreateRequest);
    AreaCreateRequest findById(Long id);
    Page<Area> findAllAreas(Pageable pageable);
    Page<Area> findAreas(Pageable pageable, FindAreaRequest findAreaRequest);
    Area updateArea(Area area);
    void deleteArea(Long id);
}
