package com.projectsem4.NotificationService.service;

import com.projectsem4.common_service.dto.UserInfor;

public interface EmailService {

    void sendOtpMail(UserInfor userInfor);

}
