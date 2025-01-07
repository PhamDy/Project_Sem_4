package com.projectsem4.StadiumService.service.impl;

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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AreaServiceImpl implements AreaService {
    private final AreaRepository areaRepository;
    private final FieldRepository fieldRepository;
    private final PriceRepository priceRepository;
    private final AccessoryRepository accessoryRepository;

    @Override
    public Boolean createArea(AreaCreateRequest createRequest) {
        Area area = new Area();
        area.setAddress(createRequest.getAddress());
        area.setName(createRequest.getName());
        area.setDescription(createRequest.getDescription());
        area.setEmail(createRequest.getEmail());
        area.setPhoneNumber(createRequest.getPhoneNumber());
        areaRepository.save(area);
        createRequest.getFields().forEach(fieldRequest -> {
            Field field = new Field();
            field.setName(fieldRequest.getName());
            field.setDescription(fieldRequest.getDescription());
            field.setEmail(fieldRequest.getEmail());
            field.setPhoneNumber(fieldRequest.getPhoneNumber());
            field.setSize(fieldRequest.getSize());
            field.setAreaId(area.getAreaId());
            fieldRepository.save(field);
            fieldRequest.getPrices().forEach(priceRequest -> {
                Price price = new Price();
                price.setPriceFrom(priceRequest.getPriceFrom());
                price.setPriceTo(priceRequest.getPriceTo());
                price.setPrice(priceRequest.getPrice());
                price.setFieldId(field.getFieldId());
                priceRepository.save(price);
            });
        });
        return Boolean.TRUE;
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
        response.setAreaId(area.getAreaId());
        List<FieldRequest> fieldRequests = new ArrayList<>();
        List<Field> fields= fieldRepository.findByAreaId(id);
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
        Page<Area> areas =  areaRepository.findAll(pageable);
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
}
