package com.projectsem4.UsersService.controller;

import com.projectsem4.UsersService.dto.UserDTO;
import com.projectsem4.UsersService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/api/v1")
@RequiredArgsConstructor
public class UserPublicController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.save(userDTO));
    }

    @GetMapping("/regenerate-otp")
    public ResponseEntity<?> regenerateOtp(@RequestParam String email) {
        userService.regenerateOtp(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/active-user")
    public ResponseEntity<?> activeUser(@RequestParam String otp) {
        userService.activeUser(otp);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam String userName,@RequestParam String password) {
        return ResponseEntity.ok().body(userService.login(userName, password));
    }

}
