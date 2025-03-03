package com.projectsem4.StadiumService.service;

import com.projectsem4.StadiumService.entity.Accessory;
import com.projectsem4.StadiumService.model.request.AreaCreateRequest;
import com.projectsem4.StadiumService.model.request.AreaDetailAdmin;
import com.projectsem4.StadiumService.model.request.FieldTypeRequest;
import com.projectsem4.StadiumService.model.request.FindAreaRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.projectsem4.StadiumService.entity.Area;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface AreaService {
    Long createArea(AreaCreateRequest areaCreateRequest, List<MultipartFile> files);
    AreaCreateRequest getAreaById(Long areaId);
    Page<AreaCreateRequest> getListArea(Pageable pageable);
    void deleteAreaById(Long areaId);



    Boolean createField(FieldTypeRequest areaCreateRequest, Long areaId);
    AreaDetailAdmin findById(Long id);
    Page<AreaDetailAdmin> findAllAreas(Pageable pageable);
    Page<Area> findAreas(Pageable pageable, FindAreaRequest findAreaRequest);
    Object updateArea(Area area);

    Boolean createAccessory(Accessory requestAccessory);
    Accessory findAccessoryById(Long id);
    Accessory updateQuantity(Integer type, Long accessoryId, Long quantity) throws Exception;
    Object findFieldById(Long id);
    Object findFieldByIdAndCalender(Long id, Long index);
    Object findAllField(Pageable pageable);
    Object search(FindAreaRequest findAreaRequest,Pageable pageable);
}
