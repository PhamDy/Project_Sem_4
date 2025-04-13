package com.projectsem4.UsersService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long userId;
    private String userName;
    private String password;
    private String name;
    private String address;
    private Integer age;
    private String email;
    private String phoneNumber;
    private Integer type = 1;
    private Integer role = 1;
}
