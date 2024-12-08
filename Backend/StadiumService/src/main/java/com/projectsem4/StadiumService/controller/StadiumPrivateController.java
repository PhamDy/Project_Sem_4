package com.projectsem4.StadiumService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private/api/v1")
public class StadiumPrivateController {

    @GetMapping
    public String getStadium() {
        return "Stadium Service";
    }

}
