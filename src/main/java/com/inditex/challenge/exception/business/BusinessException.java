package com.inditex.challenge.exception.business;

import com.inditex.challenge.exception.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends Exception {

    private ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
