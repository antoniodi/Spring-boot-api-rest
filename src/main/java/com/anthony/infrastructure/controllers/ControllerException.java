package com.anthony.infrastructure.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ControllerException {

    private HttpStatus httpStatus;

    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss" )
    private LocalDateTime dateTime = LocalDateTime.now();

    private String message;

    public ControllerException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
