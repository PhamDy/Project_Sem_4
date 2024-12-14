package com.projectsem4.StadiumService.controller;

import com.projectsem4.StadiumService.service.AreaService;
import com.projectsem4.common_service.dto.response.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public/api/v1")
public class StadiumPublicController {
    private final AreaService areaService;

    @GetMapping
    public ResponseDTO<Object> getStadium(@RequestParam Long id) {
        return ResponseDTO.ok(areaService.findById(id));
    }

}
