package com.projectsem4.StadiumService.service;

import com.projectsem4.StadiumService.model.entity.Accessory;
import com.projectsem4.StadiumService.model.request.AreaDetailAdmin;
import com.projectsem4.StadiumService.model.request.FieldTypeRequest;
import com.projectsem4.StadiumService.model.request.FindAreaRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.projectsem4.StadiumService.model.entity.Area;

import java.time.LocalDate;

public interface AreaService {
    Long createArea(Area areaCreateRequest);
    Boolean createField(FieldTypeRequest areaCreateRequest, Long areaId);
    AreaDetailAdmin findById(Long id);
    Page<AreaDetailAdmin> findAllAreas(Pageable pageable);
    Page<Area> findAreas(Pageable pageable, FindAreaRequest findAreaRequest);
    Object updateArea(Area area);
    void deleteArea(Long id);
    Boolean createAccessory(Accessory requestAccessory);
    Accessory findAccessoryById(Long id);
    Accessory updateQuantity(Integer type, Long accessoryId, Long quantity) throws Exception;
    Object findFieldById(Long id);
    Object findAllField(Pageable pageable);
    Object search(FindAreaRequest findAreaRequest,Pageable pageable);
    Object findTimeAvailable(LocalDate date, Long fieldId);
}
