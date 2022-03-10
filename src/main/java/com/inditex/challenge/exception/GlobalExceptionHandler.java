package com.inditex.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseEntityBody> handlerException(Exception exception, WebRequest request) {
        return parseBodyFromErrorCode(exception.getMessage(), 404);
    }

    private ResponseEntity<ResponseEntityBody> parseBodyFromErrorCode(String message, Integer status){
        ResponseEntityBody response = new ResponseEntityBody(message);
        response.setErrorCode("400");
        return ResponseEntity.status(status).body(response);
    }
}