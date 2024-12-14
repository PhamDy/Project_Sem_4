package com.projectsem4.common_service.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponsePage<T> {
    private int statusCode;
    private String message;
    private PageDTO<T> data;
}
