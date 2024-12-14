package com.projectsem4.common_service.dto.response;


public class ResponseData<T> {
    private final int statusCode;
    private final String message;
    private T data;

    /**
     * Response data for the API to retrieve data successfully. For GET, POST only
     * @param statusCode
     * @param message
     * @param data
     */
    public ResponseData(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    /**
     * Response data when the API executes successfully or getting error. For PUT, PATCH, DELETE
     * @param statusCode
     * @param message
     */
    public ResponseData(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getstatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
