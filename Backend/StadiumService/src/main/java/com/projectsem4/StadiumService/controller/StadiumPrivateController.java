package com.projectsem4.StadiumService.controller;

import com.projectsem4.StadiumService.entity.Accessory;
import com.projectsem4.StadiumService.entity.FieldType;
import com.projectsem4.StadiumService.model.request.AreaCreateRequest;
import com.projectsem4.StadiumService.model.request.FieldTypeRequest;
import com.projectsem4.StadiumService.model.request.FindAreaRequest;
import com.projectsem4.StadiumService.request.ValidatePeriodRequest;
import com.projectsem4.StadiumService.service.AreaService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
//@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/private/api/v1")
public class StadiumPrivateController {

    private final AreaService areaService;

    @PostMapping("/area")
    public ResponseEntity<?> createArea(@ModelAttribute AreaCreateRequest area,
                                        @RequestParam(value = "files", required = false) List<MultipartFile> files) {
        return ResponseEntity.ok(areaService.createArea(area, files));
    }

    @GetMapping("/area/{areaId}")
    public ResponseEntity<?> createArea(@PathVariable Long areaId) {
        return ResponseEntity.ok(areaService.getAreaById(areaId));
    }

    @GetMapping("/area/list")
    public ResponseEntity<Object> getListArea(Pageable pageable) {
        return ResponseEntity.ok(areaService.getListArea(pageable));
    }

    @DeleteMapping("/area/{areaId}")
    public ResponseEntity<?> deleteArea(@PathVariable Long areaId) {
        areaService.deleteAreaById(areaId);
        return ResponseEntity.ok("Area deleted");
    }

    @PostMapping("/fieldType")
    public ResponseEntity<?> createFieldType(@RequestBody FieldType fieldType, @RequestParam Long areaId) {
        return ResponseEntity.ok(areaService.createFieldType(fieldType));
    }


    @GetMapping("/fieldType")
    public ResponseEntity<?> getListFieldType(Pageable pageable) {
        return ResponseEntity.ok(areaService.getListFieldType(pageable));
    }

    @DeleteMapping("/fieldType/{fieldTypeId}")
    public ResponseEntity<?> deleteFieldType(@PathVariable Long fieldTypeId) {
        areaService.deleteFieldTypeById(fieldTypeId);
        return ResponseEntity.ok("FieldType deleted");
    }

    @PostMapping("/accessory")
    public ResponseEntity<Boolean> createAccessory(@RequestBody Accessory accessory) {
        return ResponseEntity.ok(areaService.createAccessory(accessory));
    }

    @GetMapping("/area")
    public ResponseEntity<Object> getAreaById(@RequestParam Long id) {
        return ResponseEntity.ok(areaService.findById(id));
    }

    @GetMapping("/area/{id}/field")
    public ResponseEntity<Object> getFieldByAreaId(@PathVariable Long id) {
        return ResponseEntity.ok(areaService.findAllFieldInArea(id));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all-area")
    public ResponseEntity<Object> getAllArea(Pageable pageable) {
        return ResponseEntity.ok(areaService.findAllAreas(pageable));
    }

    @GetMapping("/accessory/{id}")
    public ResponseEntity<Object> getAccessoryById(@PathVariable Long id) {
        return ResponseEntity.ok(areaService.findAccessoryById(id));
    }

    @GetMapping("/field/{id}")
    public ResponseEntity<Object> getFieldById(@PathVariable Long id) {
        return ResponseEntity.ok(areaService.findFieldById(id));
    }

    @GetMapping("/all-field")
    public ResponseEntity<Object> getAllField(Pageable pageable) {
        return ResponseEntity.ok(areaService.findAllField(pageable));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/search-field")
    public ResponseEntity<Object> searchField(Pageable pageable, @RequestBody FindAreaRequest findAreaRequest) {
        return ResponseEntity.ok(areaService.search(findAreaRequest,pageable));
    }

    @GetMapping("/calender")
    public ResponseEntity<Object> findTimeAvailable(@RequestParam(name = "id") Long id, @RequestParam(name = "index") Long index) {
        return ResponseEntity.ok(areaService.findFieldByIdAndCalender(id,index));
    }

    @GetMapping("/find-area-by-id")
    public ResponseEntity<?> findAreaById(@RequestParam Long id) {
        return ResponseEntity.ok(areaService.findAreaById(id));
    }

    @GetMapping("/validate-period")
    public ResponseEntity<Object> findTimeAvailablePeriod(@RequestBody ValidatePeriodRequest request) {
        return ResponseEntity.ok(areaService.findTimeFrame(request.getWeekDay(), request.getDate(), request.getQuantity(), request.getFieldId()));
    }

//    @PostMapping("/update-area")
//    public ResponseEntity<Object> updateArea(@ModelAttribute Area area,
//                                             @RequestParam(value = "img") MultipartFile img) {
//        return ResponseEntity.ok(areaService.updateArea(area));
//    }


}
