package com.projectsem4.StadiumService.service.impl;

import com.projectsem4.StadiumService.config.client.BookingServiceClient;
import com.projectsem4.StadiumService.constant.TypeFileEnum;
import com.projectsem4.StadiumService.entity.*;
import com.projectsem4.StadiumService.entity.Accessory;
import com.projectsem4.StadiumService.entity.FieldType;
import com.projectsem4.StadiumService.model.request.AreaCreateRequest;
import com.projectsem4.StadiumService.model.request.AreaDetailAdmin;
import com.projectsem4.StadiumService.model.request.FieldTypeRequest;
import com.projectsem4.StadiumService.model.request.FindAreaRequest;
import com.projectsem4.StadiumService.repository.*;
import com.projectsem4.StadiumService.service.AreaService;
import com.projectsem4.StadiumService.service.FileService;
import com.projectsem4.common_service.dto.constant.Constant;
import com.projectsem4.common_service.dto.entity.*;
import com.projectsem4.common_service.dto.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AreaServiceImpl implements AreaService {
    private final AreaRepository areaRepository;
    private final FieldRepository fieldRepository;
    private final FieldTypeRepository fieldTypeRepository;
    private final AccessoryRepository accessoryRepository;
    private final FileRepository fileRepository;
    private final ModelMapper modelMapper;
    private final BookingServiceClient bookingServiceClient;

    @Override
    public List<AreaResponse> findAllAreas(Pageable pageable) {
        List<Area> areas = areaRepository.findAll();
//        if (area.getAreaId()==null){
//            throw new NotFoundException("Area not found");
//        }
        List<AreaResponse> result = new ArrayList<>();
        areas.forEach(area->{
            AreaResponse areaResponse = modelMapper.map(area, AreaResponse.class);
            List<FieldType> fieldTypes = fieldTypeRepository.findByAreaId(area.getAreaId());
            List<FieldTypeResponse> fieldTypeResponses = new ArrayList<>();
            fieldTypes.forEach(fieldType -> {
                FieldTypeResponse fieldTypeResponse = modelMapper.map(fieldType, FieldTypeResponse.class);
                List<FieldType> fields = fieldRepository.findByFieldTypeId(fieldType.getFieldTypeId());
                List<FieldResponse> fieldResponses = new ArrayList<>();
                fields.forEach(item->{
                    FieldResponse fieldResponse = modelMapper.map(item, FieldResponse.class);
                    fieldResponses.add(fieldResponse);
                });
                fieldTypeResponses.add(fieldTypeResponse);
            });
            areaResponse.setFieldTypeResponseList(fieldTypeResponses);
            result.add(areaResponse);
        });
        return result;
    }

    private final FileService fileService;

    @Override
    @Transactional
    public Long createArea(AreaCreateRequest createRequest,
                           List<MultipartFile> files) {
        Area area = modelMapper.map(createRequest, Area.class);
        area = areaRepository.save(area);
        Long areaId = area.getAreaId();
        List<FileDb> fileDbs = new ArrayList<>();
        if (createRequest.getAreaId()!=null &&
                createRequest.getFileList()!=null &&
                !createRequest.getFileList().isEmpty()){

            for (FileDb fileDb : createRequest.getFileList()) {
                if (fileDb.getFilePath()!=null && !fileDb.getFilePath().isEmpty()) {
                    fileService.deleteFile(fileDb.getFilePath());
                }
            }

            fileRepository.deleteAll(createRequest.getFileList());
        }

        if (files!=null&& !files.isEmpty()){
            for (MultipartFile file : files) {
                FileDb fileDb = new FileDb();
                fileDb.setObjectId(areaId);
                fileDb.setFileName(file.getOriginalFilename());
                fileDb.setTypeFile(TypeFileEnum.TYPE_FILE_1.getKey());
                fileDb.setFilePath(fileService.uploadFile(file));
                fileDbs.add(fileDb);
            }
        }

        if (!fileDbs.isEmpty()){
            fileRepository.saveAll(fileDbs);
        }
        return areaId;
    }

    @Override
    public AreaCreateRequest getAreaById(Long areaId) {
        Area area = areaRepository.getById(areaId);
        if (area.getAreaId()==null){
            throw new NotFoundException("Area not found");
        }
        AreaCreateRequest areaCreateRequest = modelMapper.map(area, AreaCreateRequest.class);
        List<FileDb> fileDbs = fileRepository.findByObjectIdAndTypeFile(areaId, TypeFileEnum.TYPE_FILE_1.getKey());
        areaCreateRequest.setFileList(fileDbs);
        return areaCreateRequest;
    }

    @Override
    public Page<AreaCreateRequest> getListArea(Pageable pageable) {
        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("areaId").descending()
        );
        Page<Area> areas = areaRepository.findAll(sortedPageable);

        // Lấy danh sách ID của các Area để lấy FileDb tương ứng
        List<Long> areaIds = areas.getContent().stream()
                .map(Area::getAreaId)
                .collect(Collectors.toList());

        // Lấy danh sách file tương ứng với các areaId
        Map<Long, List<FileDb>> fileMap = Optional.ofNullable(
                        fileRepository.findByObjectIdInAndTypeFile(areaIds, TypeFileEnum.TYPE_FILE_1.getKey())
                ).orElse(Collections.emptyList())
                .stream()
                .collect(Collectors.groupingBy(FileDb::getObjectId));


        // Chuyển đổi dữ liệu từ Area -> AreaCreateRequest
        List<AreaCreateRequest> areaCreateRequests = areas.getContent().stream()
                .map(area -> {
                    AreaCreateRequest dto = new AreaCreateRequest();
                    BeanUtils.copyProperties(area, dto);
                    dto.setFileList(fileMap.getOrDefault(area.getAreaId(), new ArrayList<>()));
                    return dto;
                })
                .collect(Collectors.toList());

        return new PageImpl<>(areaCreateRequests, pageable, areas.getTotalElements());
    }

    @Override
    public void deleteAreaById(Long areaId) {
        Area area = areaRepository.getById(areaId);
        if (area.getAreaId()!=null){
            areaRepository.delete(area);
            List<FileDb> fileDb = fileRepository.findByObjectIdAndTypeFile(areaId, TypeFileEnum.TYPE_FILE_1.getKey());
            if (fileDb!=null && !fileDb.isEmpty()){
                fileRepository.deleteAll(fileDb);
            }
        }
    }

    @Override
    public Area findAreaById(Long areaId) {
        Area area = areaRepository.findAreaByAreaId(areaId);
        return area;
    }

    @Override
    public Long createFieldType(FieldType fieldTypeRequest) {
        if (fieldTypeRequest.getFieldTypeId()!=null){
            FieldType fieldType1 = fieldTypeRepository.findById(fieldTypeRequest.getFieldTypeId()).get();
            if (fieldType1.getFieldTypeId()!=null){
                modelMapper.map(fieldType1, fieldTypeRequest);
            }
            return fieldType1.getFieldTypeId();
        }else {
            return fieldTypeRepository.save(fieldTypeRequest).getFieldTypeId();
        }
    }

    @Override
    public Page<FieldType> getListFieldType(Pageable pageable) {
        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("fieldTypeId").descending()
        );
        return fieldTypeRepository.findAll(sortedPageable);
    }

    @Override
    public void deleteFieldTypeById(Long fieldTypeId) {
        FieldType fieldType = fieldTypeRepository.getById(fieldTypeId);
        if (fieldType.getAreaId()!=null){
            fieldTypeRepository.delete(fieldType);
        }
    }




    @Override
    public AreaDetailAdmin findById(Long id) {

        return null;
    }


    @Override
    public Boolean createAccessory(Accessory requestAccessory) {
        accessoryRepository.save(requestAccessory);
        return true;
    }

    @Override
    public Accessory findAccessoryById(Long id) {
        return accessoryRepository.findById(id).get();
    }

    @Override
    public Accessory updateQuantity(Integer type, Long accessoryId, Long quantity) throws Exception {
        Accessory accessory = accessoryRepository.findById(accessoryId).orElse(null);
        switch (type) {
            case 1:
                accessory.setQuantity(accessory.getQuantity() + quantity);
                break;
            case 2:
                if (quantity > accessory.getQuantity()) {
                    throw new Exception("Số lượng không hợp lệ");
                }
                accessory.setQuantity(accessory.getQuantity() - quantity);
                break;
        }
        return accessory;
    }

    @Override
    public Object findFieldById(Long id) {
        return fieldTypeRepository.findById(id).get();
    }

    @Override
    public Object findFieldByIdAndCalender(Long id, Long index) {
        FieldType field = fieldRepository.findById(id).get();
        List<TimeFrameSchedule> schedule = bookingServiceClient.calenderSchedule(LocalDate.now().plusDays(7 * index), id);
        FieldType fieldType = fieldTypeRepository.findById(id).orElseThrow();

        ResponseSchedule list = new ResponseSchedule();
        DateTimeFormatter dinhDang = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<LocalDate> calender = new ArrayList<>();
        LocalDate firstDate = LocalDate.now();
        calender.add(firstDate);
        calender.add(firstDate.plusDays(6));
        for (int i = 0; i < schedule.size(); i++) {
            LocalDate ngay = LocalDate.now().plusDays(i + (index * 7));
            DayOfWeek thu = ngay.getDayOfWeek();
            schedule.get(i).setDate(ngay);
            String thuTiengViet = thu.getDisplayName(java.time.format.TextStyle.FULL, new Locale("vi", "VN"));
            String result = ngay.format(dinhDang) + " - " + thuTiengViet;
            schedule.get(i).setDateString(result);
            List<FieldSchedule> fieldSchedules = new ArrayList<>();
            for(Constant.TimeFrameEnum item : Constant.TimeFrameEnum.getAllTimeFrames()){
                FieldSchedule fieldSchedule = new FieldSchedule();
                fieldSchedule.setTimeFrame(item.getKey());
                fieldSchedule.setTimeFrameStr(item.getValue());
                fieldSchedule.setFieldId(field.getFieldTypeId());
                fieldSchedule.setPrice((long) (fieldType.getPrice() != null ? fieldType.getPrice() * item.getScale() : 0.0));
                fieldSchedule.setTimeFrame(item.getKey());
                fieldSchedule.setQuantity(fieldType.getQuantity() - (fieldSchedule.getQuantity() != null ? fieldSchedule.getQuantity() : 0));
                fieldSchedule.setFieldId(field.getFieldTypeId());
                fieldSchedules.add(fieldSchedule);
                schedule.get(i).setFieldSchedules(fieldSchedules);
            }
        }
        list.setCalender(calender);
        list.setTimeFrames(schedule);
        return list;
    }

    @Override
    public Object findAllField(Pageable pageable) {
        return fieldTypeRepository.findAll(pageable);
    }

    @Override
    public Object search(FindAreaRequest findAreaRequest, Pageable pageable) {
        List<FieldType> fieldTypes = fieldTypeRepository.searchField(findAreaRequest.getLatitude(),findAreaRequest.getLongitude(),
                findAreaRequest.getDistance(),findAreaRequest.getSize(),findAreaRequest.getTimeStart(),
                findAreaRequest.getTimeEnd(),findAreaRequest.getDistrict(),findAreaRequest.getPrice());
        Map<Long,List<FieldType>> map = fieldTypes.stream().collect(Collectors.groupingBy(FieldType::getAreaId));
        List<AreaDetailAdmin> result = new ArrayList<>();
        for (Long areaId : map.keySet()) {
            AreaDetailAdmin response = new AreaDetailAdmin();
            Area area = areaRepository.findById(areaId).get();
            response.setAddress(area.getAddress());
            response.setName(area.getName());
            response.setDescription(area.getDescription());
            response.setEmail(area.getEmail());
            response.setPhoneNumber(area.getPhoneNumber());
            response.setDescription(area.getDescription());
            response.setDistrict(area.getDistrict());
            response.setLatitude(area.getLatitude());
            response.setLongitude(area.getLongitude());
            response.setAreaId(area.getAreaId());
            List<FieldTypeRequest> fieldTypeRequests = new ArrayList<>();
            List<FieldType> fieldType1 = map.get(areaId);
            fieldType1.forEach(fieldType -> {
                FieldTypeRequest fieldTypeRequest = new FieldTypeRequest();
                fieldTypeRequest.setFieldTypeId(fieldType.getFieldTypeId());
                fieldTypeRequest.setName(fieldType.getName());
                fieldTypeRequest.setDescription(fieldType.getDescription());
                fieldTypeRequest.setSize(fieldType.getSize());
                fieldTypeRequests.add(fieldTypeRequest);
            });
            response.setFields(fieldTypeRequests);
            result.add(response);
        }
        return result;
    }

    @Override
    public Object findAllFieldInArea(Long areaId) {
        Area area = areaRepository.findById(areaId).get();
        if (area.getAreaId()==null){
            throw new NotFoundException("Area not found");
        }
        AreaResponse areaResponse = modelMapper.map(area, AreaResponse.class);
        List<FieldType> fieldTypes = fieldTypeRepository.findByAreaId(areaId);
        List<FieldTypeResponse> fieldTypeResponses = new ArrayList<>();
        fieldTypes.forEach(fieldType -> {
            FieldTypeResponse fieldTypeResponse = modelMapper.map(fieldType, FieldTypeResponse.class);
            List<FieldType> fields = fieldRepository.findByFieldTypeId(fieldType.getFieldTypeId());
            List<FieldResponse> fieldResponses = new ArrayList<>();
            fields.forEach(item->{
                FieldResponse fieldResponse = modelMapper.map(item, FieldResponse.class);
                fieldResponses.add(fieldResponse);
            });
        fieldTypeResponses.add(fieldTypeResponse);
        });
        areaResponse.setFieldTypeResponseList(fieldTypeResponses);
        return areaResponse;
//        return true;
    }


}
