package com.projectsem4.UsersService.client.config;

public class FeignCustomException extends RuntimeException {
    private int statusCode;
    private String message;

    public FeignCustomException(String message) {
        super(message);
    }

    public FeignCustomException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}
