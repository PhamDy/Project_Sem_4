package com.projectsem4.UsersService.repository;

import com.projectsem4.UsersService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);
    User findByEmail(String userName);
    User findByOtp(String otp);

}
