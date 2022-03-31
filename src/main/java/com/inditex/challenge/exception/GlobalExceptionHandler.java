package com.inditex.challenge.exception;

import com.inditex.challenge.exception.business.InvalidBrandException;
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
        return parseBodyFromErrorCode(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidBrandException.class)
    public ResponseEntity<ResponseEntityBody> handlerBrandException(InvalidBrandException exception, WebRequest request) {
        return parseBodyFromErrorCode(exception.getMessage(), exception.getErrorCode().getStatus());
    }

    private ResponseEntity<ResponseEntityBody> parseBodyFromErrorCode(String message, HttpStatus status){
        ResponseEntityBody response = new ResponseEntityBody(message);
        response.setErrorCode(String.valueOf(status.value()));
        return ResponseEntity.status(status).body(response);
    }
}