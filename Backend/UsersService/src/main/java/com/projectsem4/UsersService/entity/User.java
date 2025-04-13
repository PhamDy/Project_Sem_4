package com.projectsem4.UsersService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Basic
    @Column(name = "user_name")
    private String userName;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "address")
    private String address;

    @Basic
    @Column(name = "age")
    private Integer age;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "phone_number")
    private String phoneNumber;

    @Basic
    @Column(name = "type", columnDefinition = "INTEGER DEFAULT 1") // 1 là tk user, 0 là khách vãng lai
    private Integer type;

}
