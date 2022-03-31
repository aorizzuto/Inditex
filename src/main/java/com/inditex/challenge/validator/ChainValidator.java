package com.inditex.challenge.validator;

import com.inditex.challenge.exception.ErrorCode;
import com.inditex.challenge.exception.business.InvalidBrandException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChainValidator {

    private static final Logger logger = LoggerFactory.getLogger(ChainValidator.class);

    public static void validate(Integer id) throws InvalidBrandException {
        logger.info("Chain validation begin.");

        if (id < 0) {
            logger.error("Exception while validating id: " + id);
            throw new InvalidBrandException(ErrorCode.NEGATIVE_ID);
        }
    }
}
