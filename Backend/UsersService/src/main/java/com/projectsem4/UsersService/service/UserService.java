package com.projectsem4.UsersService.service;

import com.projectsem4.UsersService.dto.ObjectTokenDTO;
import com.projectsem4.UsersService.dto.UserDTO;

public interface UserService {
    Long save(UserDTO userDTO);
    void regenerateOtp(String email);
    void activeUser(String otp);
    ObjectTokenDTO login(String userName, String password);
}
