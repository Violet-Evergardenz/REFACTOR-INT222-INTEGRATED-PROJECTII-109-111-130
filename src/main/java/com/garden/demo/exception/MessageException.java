package com.garden.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class MessageException extends HttpStatusCodeException {
    private String message;
    public MessageException(HttpStatus status, String message){
        super(status);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
