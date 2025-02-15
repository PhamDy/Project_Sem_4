package com.projectsem4.StadiumService.controller;

import com.projectsem4.StadiumService.model.entity.Accessory;
import com.projectsem4.StadiumService.model.entity.Area;
import com.projectsem4.StadiumService.model.request.FieldTypeRequest;
import com.projectsem4.StadiumService.model.request.FindAreaRequest;
import com.projectsem4.StadiumService.service.AreaService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/private/api/v1")
public class StadiumPrivateController {

    private final AreaService areaService;

    @PostMapping("/area")
    public ResponseEntity<?> createArea(@ModelAttribute Area area,
                                        @RequestParam(value = "img") MultipartFile img) {
        return ResponseEntity.ok(areaService.createArea(area));
    }

    @PostMapping("/field")
    public ResponseEntity<Boolean> createField(@RequestBody FieldTypeRequest areaCreateRequest, @RequestParam Long areaId) {
        return ResponseEntity.ok(areaService.createField(areaCreateRequest,areaId));
    }

    @PostMapping("/accessory")
    public ResponseEntity<Boolean> createAccessory(@RequestBody Accessory accessory) {
        return ResponseEntity.ok(areaService.createAccessory(accessory));
    }

    @GetMapping("/area/{id}")
    public ResponseEntity<Object> getAreaById(@PathVariable Long id) {
        return ResponseEntity.ok(areaService.findById(id));
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

    @PostMapping("/time-available")
    public ResponseEntity<Object> findTimeAvailable(@RequestParam(name = "date") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate date, @RequestParam(name = "fieldId") Long fieldId) {
        return ResponseEntity.ok(areaService.findTimeAvailable(date,fieldId));
    }

    @PostMapping("/update-area")
    public ResponseEntity<Object> updateArea(@ModelAttribute Area area,
                                             @RequestParam(value = "img") MultipartFile img) {
        return ResponseEntity.ok(areaService.updateArea(area));
    }

    @PostMapping("/delete-area")
    public ResponseEntity<?> deleteArea(@RequestParam Long id) {
        areaService.deleteArea(id);
        return ResponseEntity.ok(ResponseEntity.noContent());
    }
}
