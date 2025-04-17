package com.projectsem4.NotificationService.controller;

import com.projectsem4.NotificationService.service.EmailService;
import com.projectsem4.common_service.dto.UserInfor;
import com.projectsem4.common_service.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/api/v1")
@RequiredArgsConstructor
public class NotificationPublicController {

    private final EmailService emailService;

    @GetMapping
    public Object test(HttpServletRequest request) {
        return JwtUtil.decodeToken(request);
    }

    @PostMapping("/sen-otp-mail")
    public void sendOtpMail(@RequestBody UserInfor userInfor) {
        emailService.sendOtpMail(userInfor);
    }

}
