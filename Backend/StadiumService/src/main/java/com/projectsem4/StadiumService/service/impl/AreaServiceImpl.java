package com.projectsem4.StadiumService.service.impl;

import com.projectsem4.StadiumService.config.client.BookingServiceClient;
import com.projectsem4.StadiumService.model.entity.Accessory;
import com.projectsem4.StadiumService.model.entity.Area;
import com.projectsem4.StadiumService.model.entity.Field;
import com.projectsem4.StadiumService.model.entity.Price;
import com.projectsem4.StadiumService.model.request.AreaCreateRequest;
import com.projectsem4.StadiumService.model.request.FieldRequest;
import com.projectsem4.StadiumService.model.request.FindAreaRequest;
import com.projectsem4.StadiumService.model.request.PriceRequest;
import com.projectsem4.StadiumService.repository.AccessoryRepository;
import com.projectsem4.StadiumService.repository.AreaRepository;
import com.projectsem4.StadiumService.repository.FieldRepository;
import com.projectsem4.StadiumService.repository.PriceRepository;
import com.projectsem4.StadiumService.service.AreaService;
import com.projectsem4.common_service.dto.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    private final PriceRepository priceRepository;
    private final AccessoryRepository accessoryRepository;
    private final BookingServiceClient bookingServiceClient;

    @Override
    public Long createArea(Area createRequest) {
        Area area = areaRepository.save(createRequest);
        return area.getAreaId();
    }

    @Override
    public Boolean createField(FieldRequest fieldRequest, Long areaId) {
            Field field = new Field();
            field.setName(fieldRequest.getName());
            field.setDescription(fieldRequest.getDescription());
            field.setEmail(fieldRequest.getEmail());
            field.setQuantity(fieldRequest.getQuantity());
            field.setPhoneNumber(fieldRequest.getPhoneNumber());
            field.setSize(fieldRequest.getSize());
            field.setAreaId(areaId);
            fieldRepository.save(field);
            fieldRequest.getPrices().forEach(priceRequest -> {
                Price price = new Price();
                price.setPriceFrom(priceRequest.getPriceFrom());
                price.setPriceTo(priceRequest.getPriceTo());
                price.setPrice(priceRequest.getPrice());
                price.setFieldId(field.getFieldId());
                priceRepository.save(price);
            });
        return true;
    }

    @Override
    public AreaCreateRequest findById(Long id) {
        AreaCreateRequest response = new AreaCreateRequest();
        Area area = areaRepository.findById(id).get();
        response.setAddress(area.getAddress());
        response.setName(area.getName());
        response.setDescription(area.getDescription());
        response.setEmail(area.getEmail());
        response.setPhoneNumber(area.getPhoneNumber());
        response.setDescription(area.getDescription());
        response.setLatitude(area.getLatitude());
        response.setLongitude(area.getLongitude());
        response.setPath(area.getPath());
        response.setAreaId(area.getAreaId());
        List<FieldRequest> fieldRequests = new ArrayList<>();
        List<Field> fields = fieldRepository.findByAreaId(id);
        fields.forEach(field -> {
            FieldRequest fieldRequest = new FieldRequest();
            fieldRequest.setFieldId(field.getFieldId());
            fieldRequest.setName(field.getName());
            fieldRequest.setDescription(field.getDescription());
            fieldRequest.setEmail(field.getEmail());
            fieldRequest.setPhoneNumber(field.getPhoneNumber());
            fieldRequest.setSize(field.getSize());
            List<PriceRequest> priceRequests = new ArrayList<>();
            List<Price> prices = priceRepository.findByFieldId(field.getFieldId());
            prices.forEach(price -> {
                PriceRequest priceRequest = new PriceRequest();
                priceRequest.setPriceFrom(price.getPriceFrom());
                priceRequest.setPriceTo(price.getPriceTo());
                priceRequest.setPrice(price.getPrice());
                priceRequest.setFieldId(price.getFieldId());
                priceRequests.add(priceRequest);
            });
            fieldRequest.setPrices(priceRequests);
            fieldRequests.add(fieldRequest);
        });
        response.setFields(fieldRequests);
        return response;
    }

    @Override
    public Page<AreaCreateRequest> findAllAreas(Pageable pageable) {
        Page<Area> areas = areaRepository.findAll(pageable);
        return areas.map(area -> findById(area.getAreaId()));
    }

    @Override
    public Page<Area> findAreas(Pageable pageable, FindAreaRequest findAreaRequest) {
        return null;
    }

    @Override
    public Area updateArea(Area area) {
        return null;
    }

    @Override
    public void deleteArea(Long id) {
        Area area = areaRepository.findById(id).orElse(null);
        area.setStatus(Constants.Status.DELETE);
        areaRepository.save(area);
        List<Field> fields = fieldRepository.findByAreaId(id);
        fields.forEach(field -> {
            field.setStatus(Constants.Status.DELETE);
            fieldRepository.save(field);
            List<Price> prices = priceRepository.findByFieldId(field.getFieldId());
            prices.forEach(price -> {
                price.setStatus(Constants.Status.DELETE);
                priceRepository.save(price);
            });
        });
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
        List<Field> fields = fieldRepository.searchField(findAreaRequest.getLatitude(),findAreaRequest.getLongitude(),
                findAreaRequest.getDistance(),findAreaRequest.getSize(),findAreaRequest.getTimeStart(),
                findAreaRequest.getTimeEnd(),findAreaRequest.getDistrict(),findAreaRequest.getPrice());
        Map<Long,List<Field>> map = fields.stream().collect(Collectors.groupingBy(Field::getAreaId));
        List<AreaCreateRequest> result = new ArrayList<>();
        for (Long areaId : map.keySet()) {
            AreaCreateRequest response = new AreaCreateRequest();
            Area area = areaRepository.findById(areaId).get();
            response.setAddress(area.getAddress());
            response.setName(area.getName());
            response.setDescription(area.getDescription());
            response.setEmail(area.getEmail());
            response.setPhoneNumber(area.getPhoneNumber());
            response.setDescription(area.getDescription());
            response.setLatitude(area.getLatitude());
            response.setLongitude(area.getLongitude());
            response.setPath(area.getPath());
            response.setAreaId(area.getAreaId());
            List<FieldRequest> fieldRequests = new ArrayList<>();
            List<Field> field1 = map.get(areaId);
            field1.forEach(field -> {
                FieldRequest fieldRequest = new FieldRequest();
                fieldRequest.setFieldId(field.getFieldId());
                fieldRequest.setName(field.getName());
                fieldRequest.setDescription(field.getDescription());
                fieldRequest.setEmail(field.getEmail());
                fieldRequest.setPhoneNumber(field.getPhoneNumber());
                fieldRequest.setSize(field.getSize());
                List<PriceRequest> priceRequests = new ArrayList<>();
                List<Price> prices = priceRepository.findByFieldId(field.getFieldId());
                prices.forEach(price -> {
                    PriceRequest priceRequest = new PriceRequest();
                    priceRequest.setPriceFrom(price.getPriceFrom());
                    priceRequest.setPriceTo(price.getPriceTo());
                    priceRequest.setPrice(price.getPrice());
                    priceRequest.setFieldId(price.getFieldId());
                    priceRequests.add(priceRequest);
                });
                fieldRequest.setPrices(priceRequests);
                fieldRequests.add(fieldRequest);
            });
            response.setFields(fieldRequests);
            result.add(response);
        }
        return result;
    }

    @Override
    public Object findTimeAvailable(LocalDate date, Long fieldId) {
        Field field = fieldRepository.findById(fieldId).orElse(null);
        List<com.projectsem4.common_service.dto.entity.Price> prices = priceRepository.findByFieldId(fieldId).stream().map(this ::mapPrice).toList();
        return bookingServiceClient.findTimeAvailable(date,prices, field.getQuantity());
    }

    public com.projectsem4.common_service.dto.entity.Price mapPrice(Price price){
        com.projectsem4.common_service.dto.entity.Price price1 = new com.projectsem4.common_service.dto.entity.Price();
        price1.setPrice(price.getPrice());
        price1.setPriceId(price.getPriceId());
        price1.setPriceFrom(price.getPriceFrom());
        price1.setPriceTo(price.getPriceTo());
        price1.setFieldId(price.getFieldId());
        return price1;
    }
}
