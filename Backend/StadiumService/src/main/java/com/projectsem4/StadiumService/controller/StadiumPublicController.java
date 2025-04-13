package com.projectsem4.StadiumService.controller;

import com.projectsem4.StadiumService.service.AreaService;
import com.projectsem4.common_service.dto.constant.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/public/api/v1")
public class StadiumPublicController {
    private final AreaService areaService;

    @Autowired
    public StadiumPublicController(AreaService areaService) {
        this.areaService = areaService;
    }

    @GetMapping("/danhMucQuanHuyen")
    public ResponseEntity<List<Map<String, Object>>> danhMucQuanHuyen() {
        List<Map<String, Object>> danhMuc = Arrays.stream(Constant.DistrictEnum.values())
                .map(district -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("key", district.getKey());
                    map.put("value", district.getValue());
                    return map;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(danhMuc);
    }


}
