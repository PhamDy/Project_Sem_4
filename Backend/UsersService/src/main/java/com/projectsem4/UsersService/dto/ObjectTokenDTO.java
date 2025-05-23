package com.projectsem4.UsersService.dto;

import com.projectsem4.common_service.dto.UserInfor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ObjectTokenDTO {
    private String token;
    private UserInfor userInfor;
}
