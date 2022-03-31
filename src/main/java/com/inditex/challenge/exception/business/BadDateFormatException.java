package com.inditex.challenge.exception.business;

import com.inditex.challenge.exception.ErrorCode;

public class BadDateFormatException extends BusinessException {
    public BadDateFormatException(ErrorCode errorCode) { super(errorCode); }
}
