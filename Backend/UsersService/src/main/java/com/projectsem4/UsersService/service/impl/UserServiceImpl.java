package com.projectsem4.UsersService.service.impl;

import com.projectsem4.UsersService.dto.UserDTO;
import com.projectsem4.UsersService.entity.User;
import com.projectsem4.UsersService.exception.BadRequestException;
import com.projectsem4.UsersService.repository.UserRepository;
import com.projectsem4.UsersService.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long save(UserDTO userDTO) {
        User user = userRepository.findByUserName(userDTO.getUserName());
        if (user==null) {
            user = userRepository.findByEmail(userDTO.getEmail());
        }
        if(user==null){
            throw new BadRequestException("Username already exists");
        }
        user = modelMapper.map(userDTO, User.class);
        userRepository.save(user);
        return user.getUserId();
    }

    @Override
    public void regenerateOtp(String email) {
        User user = userRepository.findByEmail(email);
        if(user==null){
            throw new BadRequestException("Email not found");
        }
        String otp = UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase();
        user.setOtp(otp);
        userRepository.save(user);

    }

    @Override
    public void activeUser(String otp) {
        User user = userRepository.findByOtp(otp);
        if(user == null || !user.getOtp().equals(otp)){
            throw new BadRequestException("Invalid otp");
        }
        user.setIsActive(true);
        userRepository.save(user);
    }

}