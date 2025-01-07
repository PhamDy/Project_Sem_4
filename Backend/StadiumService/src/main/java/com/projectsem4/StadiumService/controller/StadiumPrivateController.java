package com.projectsem4.StadiumService.controller;

import com.projectsem4.StadiumService.model.entity.Accessory;
import com.projectsem4.StadiumService.model.request.AreaCreateRequest;
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

    @PostMapping("/accessory")
    public ResponseEntity<Boolean> createAccessory(@RequestBody Accessory accessory) {
        return ResponseEntity.ok(areaService.createAccessory(accessory));
    }

    @GetMapping("/area/{id}")
    public ResponseEntity<Object> getAreaById(@PathVariable Long id) {
        return ResponseEntity.ok(areaService.findById(id));
    }

    @GetMapping("/all-area")
    public ResponseEntity<Object> getAllArea(Pageable pageable) {
        return ResponseEntity.ok(areaService.findAllAreas(pageable));
    }

}
