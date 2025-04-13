package com.projectsem4.NotificationService.controller;

import com.projectsem4.NotificationService.service.EmailService;
import com.projectsem4.common_service.dto.UserInfor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/api/v1")
@RequiredArgsConstructor
public class NotificationPublicController {

    private final EmailService emailService;

    @GetMapping
    public String test() {
        return "Notification Service";
    }

    @GetMapping("/sen-otp-mail")
    public ResponseEntity<?> sendOtpMail(@RequestBody UserInfor userInfor) {
        emailService.sendOtpMail(userInfor);
        return ResponseEntity.ok().build();
    }

}
