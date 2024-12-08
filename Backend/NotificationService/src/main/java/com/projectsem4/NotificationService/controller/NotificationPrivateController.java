package com.projectsem4.NotificationService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private/api/v1")
public class NotificationPrivateController {

    @GetMapping
    public String test() {
        return "Notification Service";
    }

}
