package com.projectsem4.StadiumService.service;

import com.projectsem4.StadiumService.model.entity.Accessory;
import com.projectsem4.StadiumService.model.request.AreaCreateRequest;
import com.projectsem4.StadiumService.model.request.FindAreaRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.projectsem4.StadiumService.model.entity.Area;

import java.util.List;

public interface AreaService {
    Boolean createArea(AreaCreateRequest areaCreateRequest);
    AreaCreateRequest findById(Long id);
    Page<AreaCreateRequest> findAllAreas(Pageable pageable);
    Page<Area> findAreas(Pageable pageable, FindAreaRequest findAreaRequest);
    Area updateArea(Area area);
    void deleteArea(Long id);
    Boolean createAccessory(Accessory requestAccessory);
    Accessory findAccessoryById(Long id);
    Accessory updateQuantity(Integer type, Long accessoryId, Long quantity) throws Exception;
    Object findFieldById(Long id);
}
