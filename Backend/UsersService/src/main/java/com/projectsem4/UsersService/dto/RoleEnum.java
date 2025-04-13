package com.projectsem4.UsersService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  RoleEnum {

    USER(1, "User"),
    ADMIN(2, "Admin");

    private final Integer key;
    private final String values;

    public static RoleEnum fromKey(Integer key) {
        for (RoleEnum role : RoleEnum.values()) {
            if (role.getKey().equals(key)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role key: " + key);
    }

}
