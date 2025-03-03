package com.projectsem4.StadiumService.service.impl;

import com.projectsem4.StadiumService.config.client.BookingServiceClient;
import com.projectsem4.StadiumService.constant.TypeFileEnum;
import com.projectsem4.StadiumService.entity.Accessory;
import com.projectsem4.StadiumService.entity.Area;
import com.projectsem4.StadiumService.entity.FieldType;
import com.projectsem4.StadiumService.entity.FileDb;
import com.projectsem4.StadiumService.model.request.AreaCreateRequest;
import com.projectsem4.StadiumService.model.request.AreaDetailAdmin;
import com.projectsem4.StadiumService.model.request.FieldTypeRequest;
import com.projectsem4.StadiumService.model.request.FindAreaRequest;
import com.projectsem4.StadiumService.repository.*;
import com.projectsem4.StadiumService.service.AreaService;
import com.projectsem4.StadiumService.util.FileUtil;
import com.projectsem4.common_service.dto.constant.Constant;
import com.projectsem4.common_service.dto.entity.FieldDateSchedule;
import com.projectsem4.common_service.dto.entity.FieldSchedule;
import com.projectsem4.common_service.dto.entity.ResponseSchedule;
import com.projectsem4.common_service.dto.entity.TimeFrameSchedule;
import com.projectsem4.common_service.dto.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
            fileRepository.deleteAll(createRequest.getFileList());
        }

        if (files!=null&& !files.isEmpty()){
            for (MultipartFile file : files) {
                FileDb fileDb = new FileDb();
                fileDb.setObjectId(areaId);
                fileDb.setFileName(file.getOriginalFilename());
                fileDb.setTypeFile(TypeFileEnum.TYPE_FILE_1.getKey());
                fileDb.setFilePath(FileUtil.uploadImage(file));
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
        Page<Area> areas = areaRepository.findAll(pageable);

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
        }
    }


    @Override
    public Boolean createField(FieldTypeRequest fieldTypeRequest, Long areaId) {

        return true;
    }

    @Override
    public AreaDetailAdmin findById(Long id) {

        return null;
    }

    @Override
    public Page<AreaDetailAdmin> findAllAreas(Pageable pageable) {
        Page<Area> areas = areaRepository.findAll(pageable);


        return areas.map(area -> findById(area.getAreaId()));
    }

    @Override
    public Page<Area> findAreas(Pageable pageable, FindAreaRequest findAreaRequest) {
        return null;
    }

    @Override
    public Object updateArea(Area area) {
        return area.getAreaId();
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
        FieldType fieldType = fieldTypeRepository.findById(id).orElse(null);

        ResponseSchedule list = new ResponseSchedule();
        DateTimeFormatter dinhDang = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<String> calender = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            LocalDate ngay = LocalDate.now().plusDays(i * index);
            DayOfWeek thu = ngay.getDayOfWeek();
            String thuTiengViet = thu.getDisplayName(java.time.format.TextStyle.FULL, new Locale("vi", "VN"));
            String result = ngay.format(dinhDang) + " - " + thuTiengViet;
            calender.add(result);
        }
        list.setCalender(calender);
        Constant.TimeFrameEnum.getAllTimeFrames().forEach(item->{
            TimeFrameSchedule timeFrameSchedule = new TimeFrameSchedule();
            timeFrameSchedule.setTimeFrame(item.getKey());
            List<FieldSchedule> fieldSchedules = new ArrayList<>();
            fieldRepository.findByFieldTypeId(id).forEach(field->{
                FieldSchedule obj = new FieldSchedule();
                obj.setFieldId(field.getFieldId());
                obj.setFieldName(field.getName());
                List<FieldDateSchedule> fieldDateSchedules = new ArrayList<>();
                for (int i = 0; i <= 6; i++) {
                    FieldDateSchedule fieldDateSchedule = new FieldDateSchedule();
                    fieldDateSchedule.setDate(LocalDate.now().plusDays(i * index));
                    fieldDateSchedule.setPrice((long) (fieldType.getPrice() * item.getScale()));
                    fieldDateSchedules.add(fieldDateSchedule);
                }
                obj.setFieldDateScheduleList(fieldDateSchedules);
                fieldSchedules.add(obj);
            });
            timeFrameSchedule.setFieldSchedules(fieldSchedules);
        });
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
//                fieldTypeRequest.setEmail(fieldType.getEmail());
//                fieldTypeRequest.setPhoneNumber(fieldType.getPhoneNumber());
                fieldTypeRequest.setSize(fieldType.getSize());
//                List<PriceRequest> priceRequests = new ArrayList<>();
//                List<TimeFrame> timeFrames = priceRepository.findByFieldId(fieldType.getFieldTypeId());
//                timeFrames.forEach(timeFrame -> {
//                    PriceRequest priceRequest = new PriceRequest();
//                    priceRequest.setPriceFrom(timeFrame.getTimeFrom());
//                    priceRequest.setPriceTo(timeFrame.getTimeTo());
//                    priceRequest.setPrice(timeFrame.getPrice());
//                    priceRequest.setFieldId(timeFrame.getFieldId());
//                    priceRequests.add(priceRequest);
//                });
//                fieldTypeRequest.setPrices(priceRequests);
//                fieldTypeRequests.add(fieldTypeRequest);
            });
            response.setFields(fieldTypeRequests);
            result.add(response);
        }
        return result;
    }


}
