package com.inditex.challenge.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    NEGATIVE_ID("Id should be positive", HttpStatus.BAD_REQUEST),
    BAD_DATE_FORMAT("Date format should be yyyy-MM-dd-HH.mm.ss", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus status;

    ErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
