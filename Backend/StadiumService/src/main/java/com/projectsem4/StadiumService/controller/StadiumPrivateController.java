package com.projectsem4.StadiumService.controller;

import com.projectsem4.StadiumService.model.entity.Accessory;
import com.projectsem4.StadiumService.model.request.AreaCreateRequest;
import com.projectsem4.StadiumService.model.request.FieldRequest;
import com.projectsem4.StadiumService.model.request.FindAreaRequest;
import com.projectsem4.StadiumService.service.AreaService;

import com.projectsem4.common_service.dto.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/api/v1")
public class StadiumPrivateController {
    private final AreaService areaService;

    @PostMapping("/area")
    public ResponseEntity<Boolean> createArea(@RequestBody AreaCreateRequest areaCreateRequest) {
        return ResponseEntity.ok(areaService.createArea(areaCreateRequest));
    }

    @PostMapping("/field")
    public ResponseEntity<Boolean> createField(@RequestBody FieldRequest areaCreateRequest, @RequestParam Long areaId) {
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
}
