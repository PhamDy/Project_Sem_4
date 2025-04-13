package com.projectsem4.common_service.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfor {
    private Long userId;
    private String userName;
    private String password;
    private String name;
    private String address;
    private Integer age;
    private String email;
    private String phoneNumber;
    private String otp;
    private Integer type = 1;
    private Integer role = 1;
}
