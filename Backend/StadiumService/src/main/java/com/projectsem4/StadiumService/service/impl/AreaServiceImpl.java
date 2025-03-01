package com.projectsem4.StadiumService.service.impl;

import com.projectsem4.StadiumService.config.client.BookingServiceClient;
import com.projectsem4.StadiumService.constant.TypeFileEnum;
import com.projectsem4.StadiumService.entity.Accessory;
import com.projectsem4.StadiumService.entity.Area;
import com.projectsem4.StadiumService.entity.FieldType;
import com.projectsem4.StadiumService.entity.FileDb;
import com.projectsem4.StadiumService.model.request.AreaDetailAdmin;
import com.projectsem4.StadiumService.model.request.FieldTypeRequest;
import com.projectsem4.StadiumService.model.request.FindAreaRequest;
import com.projectsem4.StadiumService.repository.AccessoryRepository;
import com.projectsem4.StadiumService.repository.AreaRepository;
import com.projectsem4.StadiumService.repository.FieldRepository;
import com.projectsem4.StadiumService.repository.FileRepository;
import com.projectsem4.StadiumService.service.AreaService;
import com.projectsem4.StadiumService.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AreaServiceImpl implements AreaService {
    private final AreaRepository areaRepository;
    private final FieldRepository fieldRepository;
    private final AccessoryRepository accessoryRepository;
    private final FileRepository fileRepository;
    private final BookingServiceClient bookingServiceClient;

    @Override
    public Long createArea(Area createRequest, List<MultipartFile> files) {
        Area area = areaRepository.save(createRequest);

        List<FileDb> fileDbs = new ArrayList<>();
        for (MultipartFile file : files) {
            FileDb fileDb = new FileDb();
            fileDb.setObjectId(area.getAreaId());
            fileDb.setFileName(file.getOriginalFilename());
            fileDb.setTypeFile(TypeFileEnum.TYPE_FILE_1.getKey());
            fileDb.setFilePath(FileUtil.uploadImage(file));
            fileDbs.add(fileDb);
        }
        fileRepository.saveAll(fileDbs);
        return area.getAreaId();
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
    public void deleteArea(Long id) {

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
        return fieldRepository.findById(id).get();
    }

    @Override
    public Object findAllField(Pageable pageable) {
        return fieldRepository.findAll(pageable);
    }

    @Override
    public Object search(FindAreaRequest findAreaRequest, Pageable pageable) {
        List<FieldType> fieldTypes = fieldRepository.searchField(findAreaRequest.getLatitude(),findAreaRequest.getLongitude(),
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
            response.setPath(area.getPath());
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

    @Override
    public Object findTimeAvailable(LocalDate date, Long fieldId) {
        FieldType fieldType = fieldRepository.findById(fieldId).orElse(null);
        return null;
//        return bookingServiceClient.findTimeAvailable(date,prices, fieldType.getQuantity());
    }

}
