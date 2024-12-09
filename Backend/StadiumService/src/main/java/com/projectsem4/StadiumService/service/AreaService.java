package com.projectsem4.StadiumService.service;

import com.projectsem4.StadiumService.model.request.FindAreaRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.awt.geom.Area;

public interface AreaService {
    Area createArea();
    Area findById(String id);
    Page<Area> findAllAreas(Pageable pageable);
    Page<Area> findAreas(Pageable pageable, FindAreaRequest findAreaRequest);
    Area updateArea(Area area);
    Area deleteArea(String id);
}
