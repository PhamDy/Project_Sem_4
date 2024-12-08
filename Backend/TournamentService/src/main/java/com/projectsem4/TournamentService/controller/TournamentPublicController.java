package com.projectsem4.TournamentService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/api/v1")
public class TournamentPublicController {

    @GetMapping
    public String test() {
        return "Tournament Service";
    }

}
