package com.projectsem4.StadiumService.controller;

import com.projectsem4.StadiumService.model.request.AreaCreateRequest;
import com.projectsem4.StadiumService.service.AreaService;

import com.projectsem4.common_service.dto.response.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private/api/v1")
public class StadiumPrivateController {
    private final AreaService areaService;

    @PostMapping
    public ResponseDTO<Boolean> createArea(AreaCreateRequest areaCreateRequest) {
        return ResponseDTO.ok(areaService.createArea(areaCreateRequest));
    }

    @GetMapping
    public String test(){
        return "test";
    }

}
