package com.anthony.infrastructure.controllers;

import com.anthony.domain.entities.Category;
import com.anthony.domain.entities.Product;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(Product.ProductNotFoundException.class)
    public ResponseEntity<ControllerException> productNotFound(Product.ProductNotFoundException exception) {
        ControllerException controllerException = new ControllerException(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(controllerException);
    }

    @ExceptionHandler(Category.CategoryNotFoundException.class)
    public ResponseEntity<ControllerException> categoryNotFound(Category.CategoryNotFoundException exception) {
        ControllerException controllerException = new ControllerException(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(controllerException);
    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<ControllerException> handleMappingException(JsonMappingException exception) {
        ControllerException controllerException = new ControllerException(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(controllerException);
    }
}
