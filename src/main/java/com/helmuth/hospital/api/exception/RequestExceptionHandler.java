package com.helmuth.hospital.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RequestExceptionHandler {

    @ExceptionHandler(FormDataException.class)
    public ResponseEntity<RequestException> handleRequestException(Exception e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(badRequest).body(
                new RequestException(e.getMessage(), badRequest)
        );
    }
}
