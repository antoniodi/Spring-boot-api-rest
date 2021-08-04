package com.anthony.infrastructure.controllers;

import com.anthony.domain.entities.Category;
import com.anthony.domain.entities.Product;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Product.ProductNotFoundException.class)
    public ResponseEntity<ControllerException> productNotFound(Product.ProductNotFoundException exception) {
        final ControllerException controllerException = new ControllerException(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(controllerException);
    }

    @ExceptionHandler(Category.CategoryNotFoundException.class)
    public ResponseEntity<ControllerException> categoryNotFound(Category.CategoryNotFoundException exception) {
        final ControllerException controllerException = new ControllerException(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(controllerException);
    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<ControllerException> handleMappingException(JsonMappingException exception) {
        final ControllerException controllerException = new ControllerException(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(controllerException);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final ControllerException controllerException = new ControllerException(status, ex.getMessage());
        return new ResponseEntity(controllerException, headers, status);
    }

}
