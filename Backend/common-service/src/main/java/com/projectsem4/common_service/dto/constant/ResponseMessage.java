package com.projectsem4.common_service.dto.constant;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;

@UtilityClass
public class ResponseMessage {
    public int STATUS_200 = HttpStatus.OK.value();
    public int STATUS_201 = HttpStatus.CREATED.value();
    public int STATUS_204 = HttpStatus.NO_CONTENT.value();
    public int STATUS_400 = HttpStatus.BAD_REQUEST.value();
    public int STATUS_401 = HttpStatus.UNAUTHORIZED.value();
    public int STATUS_403 = HttpStatus.FORBIDDEN.value();
    public int STATUS_404 = HttpStatus.NOT_FOUND.value();
    public int STATUS_409 = HttpStatus.CONFLICT.value();
    public int STATUS_500 = HttpStatus.INTERNAL_SERVER_ERROR.value();
}
