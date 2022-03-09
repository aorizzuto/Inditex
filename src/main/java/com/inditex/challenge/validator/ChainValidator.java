package com.inditex.challenge.validator;

import com.inditex.challenge.exception.ErrorCode;

public class ChainValidator {

    public static void validate(Integer id) throws Exception {
        if (id < 0) throw new Exception(ErrorCode.NEGATIVE_ID);
    }
}
