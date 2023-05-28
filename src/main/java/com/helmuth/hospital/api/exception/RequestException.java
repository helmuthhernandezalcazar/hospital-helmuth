package com.helmuth.hospital.api.exception;

import org.springframework.http.HttpStatus;

public class RequestException {
    private String message;
    private HttpStatus httpStatus;

    public RequestException() {
    }

    public RequestException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
