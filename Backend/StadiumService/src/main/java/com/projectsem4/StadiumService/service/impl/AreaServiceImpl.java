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
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
        area.setLongitude(createRequest.getLongitude());
        area.setLatitude(createRequest.getLatitude());
        area.setPath(createRequest.getPath());
        area.setPhoneNumber(createRequest.getPhoneNumber());
        areaRepository.save(area);
        createRequest.getFields().forEach(fieldRequest -> {
            Field field = new Field();
            field.setName(fieldRequest.getName());
            field.setDescription(fieldRequest.getDescription());
            field.setEmail(fieldRequest.getEmail());
            field.setQuantity(fieldRequest.getQuantity());
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
        Specification<Field> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (findAreaRequest.getLatitude() != null && findAreaRequest.getLongitude() != null && findAreaRequest.getDistance() != null) {
                Double latitude = findAreaRequest.getLatitude();
                Double longitude = findAreaRequest.getLongitude();

                // Hàm tính khoảng cách dựa trên công thức Haversine
                Expression<Double> distanceExpression = criteriaBuilder.function(
                        "6371 * ACOS", // Hàm tính khoảng cách dựa trên SQL
                        Double.class,
                        criteriaBuilder.sum(
                                criteriaBuilder.prod(
                                        criteriaBuilder.function("COS", Double.class, criteriaBuilder.function("RADIANS", Double.class, criteriaBuilder.literal(latitude))),
                                        criteriaBuilder.prod(
                                                criteriaBuilder.function("COS", Double.class, criteriaBuilder.function("RADIANS", Double.class, root.get("latitude"))),
                                                criteriaBuilder.function(
                                                        "COS",
                                                        Double.class,
                                                        criteriaBuilder.diff(
                                                                criteriaBuilder.function("RADIANS", Double.class, root.get("longitude")),
                                                                criteriaBuilder.function("RADIANS", Double.class, criteriaBuilder.literal(longitude))
                                                        )
                                                )
                                        )
                                ),
                                criteriaBuilder.prod(
                                        criteriaBuilder.function("SIN", Double.class, criteriaBuilder.function("RADIANS", Double.class, criteriaBuilder.literal(latitude))),
                                        criteriaBuilder.function("SIN", Double.class, criteriaBuilder.function("RADIANS", Double.class, root.get("latitude")))
                                )
                        )
                );

                // Áp dụng điều kiện khoảng cách
                predicates.add(criteriaBuilder.lessThanOrEqualTo(distanceExpression, (double) findAreaRequest.getDistance()));
            }

            // Thêm các điều kiện khác nếu cần
            if (findAreaRequest.getPrice() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), findAreaRequest.getPrice()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        return fieldRepository.findAll(pageable,specification);
    }
//
//    @Override
//    public Page<KeyListResponse> keyList(KeySearchForm form, Pageable pageable) {
//
//
//        Specification<ProxyKeys> spec = (root, criteriaQuery, cb) -> cb.and(
//                form.getKeyword() != null ? cb.like(root.get("myKey"), "%" + form.getKeyword() + "%") : cb.conjunction(),
//                form.getKeyType() != null ? cb.equal(root.get("packageId"), form.getKeyType()) : cb.conjunction(),
//                Objects.equals(form.getStatus(), "VALID") ? cb.greaterThanOrEqualTo(root.get("expireDate"), LocalDateTime.now()) : cb.conjunction(),
//                Objects.equals(form.getStatus(), "INVALID") ? cb.lessThan(root.get("expireDate"), LocalDateTime.now()) : cb.conjunction()
//        );
//
//        Page<ProxyKeys> proxyKeys = proxyKeysRepository.findAll(spec, pageable);
//
//        if (proxyKeys.isEmpty()) {
//            return Page.empty();
//        }
//
//        return KeyMapper.INSTANCE.keyResponse(proxyKeys);
//    }
}
