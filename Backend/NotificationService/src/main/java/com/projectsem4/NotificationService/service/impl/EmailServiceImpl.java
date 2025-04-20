package com.projectsem4.NotificationService.service.impl;

import com.projectsem4.common_service.dto.SendEmailBookingDTO;
import com.projectsem4.common_service.dto.UserInfor;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.projectsem4.NotificationService.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String fromMail;

    @Override
    public void sendOtpMail(UserInfor userInfor) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            // Prepare context
            Context context = new Context();
            context.setVariable("userName", userInfor.getUserName());
            context.setVariable("otp", userInfor.getOtp());

            String htmlContent = templateEngine.process("mail/otp.html", context);

            helper.setFrom(fromMail);
            helper.setTo(userInfor.getEmail());
            helper.setSubject("Your OTP Code");
            helper.setText(htmlContent, true);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("Failed to send email", e);
            throw new IllegalStateException("Failed to send email");
        }
    }

    @Override
    public void sendOtpMail(SendEmailBookingDTO sendEmailBookingDTO) {


    }
}
