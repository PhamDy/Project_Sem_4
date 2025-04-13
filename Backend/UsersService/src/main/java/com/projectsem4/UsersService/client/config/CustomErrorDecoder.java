package com.projectsem4.UsersService.client.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.util.Map;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> errorMap = mapper.readValue(response.body().asInputStream(), Map.class);
            String message = (String) errorMap.get("message");
            int statusCode = response.status();
            return new FeignCustomException(statusCode, message);
        } catch (Exception e) {
            return defaultDecoder.decode(methodKey, response);
        }
    }
}
