package com.projectsem4.StadiumService.controller;

import com.projectsem4.StadiumService.constant.DistrictEnum;
import com.projectsem4.StadiumService.service.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public/api/v1")
public class StadiumPublicController {
    private final AreaService areaService;

    @GetMapping("/danhMucQuanHuyen")
    public ResponseEntity<List<Map<String, Object>>> danhMucQuanHuyen() {
        List<Map<String, Object>> danhMuc = Arrays.stream(DistrictEnum.values())
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
