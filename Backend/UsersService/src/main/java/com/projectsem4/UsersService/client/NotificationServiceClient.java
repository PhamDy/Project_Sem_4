package com.projectsem4.UsersService.client;

import com.projectsem4.UsersService.client.config.FeignConfig;
import com.projectsem4.common_service.dto.UserInfor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "NotificationServiceClient", url = "${config.url.notification-service-url}", configuration = FeignConfig.class)
public interface NotificationServiceClient {

    @PostMapping(value = "/public/api/v1/sen-otp-mail", consumes = "application/json")
    void sendOtpMail(@RequestBody UserInfor userInfor);

}
