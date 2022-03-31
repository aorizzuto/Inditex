package com.inditex.challenge.exception.business;

import com.inditex.challenge.exception.ErrorCode;

public class InvalidProductException extends BusinessException {
    public InvalidProductException(ErrorCode errorCode) { super(errorCode); }
}
