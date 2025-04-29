package com.projectsem4.StadiumService.service;

import com.projectsem4.StadiumService.entity.Accessory;
import com.projectsem4.StadiumService.entity.Area;
import com.projectsem4.StadiumService.entity.FieldType;
import com.projectsem4.StadiumService.model.request.AreaCreateRequest;
import com.projectsem4.StadiumService.model.request.AreaDetailAdmin;
import com.projectsem4.StadiumService.model.request.FieldTypeRequest;
import com.projectsem4.StadiumService.model.request.FindAreaRequest;
import com.projectsem4.common_service.dto.entity.AreaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface AreaService {
    Long createArea(AreaCreateRequest areaCreateRequest, List<MultipartFile> files);
    AreaCreateRequest getAreaById(Long areaId);
    Page<AreaCreateRequest> getListArea(Pageable pageable);
    void deleteAreaById(Long areaId);
    Area findAreaById(Long areaId);

    Long createFieldType(FieldType fieldType);
    Page<FieldType> getListFieldType(Pageable pageable);
    void deleteFieldTypeById(Long fieldTypeId);

    AreaDetailAdmin findById(Long id);
    List<AreaResponse> findAllAreas(Pageable pageable);
    Boolean createAccessory(Accessory requestAccessory);
    Accessory findAccessoryById(Long id);
    Accessory updateQuantity(Integer type, Long accessoryId, Long quantity) throws Exception;
    Object findFieldById(Long id);
    Object findFieldByIdAndCalender(Long id, Long index);
    Object findAllField(Pageable pageable);
    Object search(FindAreaRequest findAreaRequest,Pageable pageable);
    Object findAllFieldInArea(Long areaId);
    Object findTimeFrame(Long weekDay, LocalDate month, Long quantity, Long fieldType);
    public Object findTimeFrameTournament(LocalDate startDate, LocalDate endDate, Long quantity, Long fieldTypeId);
}
