package com.garden.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResponseEntityErrorException {
    @ExceptionHandler(MessageException.class)
    public ResponseEntity<ResponseException> handleException(MessageException messageException) {
        ResponseException response = new ResponseException(messageException.getStatusCode(), messageException.getMessage());
        return new ResponseEntity<>(response,response.getStatus());
    }
}
