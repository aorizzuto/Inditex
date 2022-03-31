package com.inditex.challenge.exception.business;

import com.inditex.challenge.exception.ErrorCode;

public class InvalidBrandException extends BusinessException {
    public InvalidBrandException(ErrorCode errorCode) { super(errorCode); }
}
