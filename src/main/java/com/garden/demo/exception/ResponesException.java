package com.garden.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

class ResponseException {
    private HttpStatusCode status;
    private LocalDateTime dateTime;
    private String message;

    public ResponseException(HttpStatusCode status,String message) {
        this.dateTime = LocalDateTime.now();
        this.status = status;
        this.message = message;

    }

    public HttpStatusCode getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
