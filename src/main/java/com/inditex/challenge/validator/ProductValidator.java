package com.inditex.challenge.validator;

import com.inditex.challenge.exception.ErrorCode;
import com.inditex.challenge.exception.business.InvalidProductException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductValidator {

    private static final Logger logger = LoggerFactory.getLogger(ProductValidator.class);

    public static void validate(Integer id) throws InvalidProductException {
        logger.info("Product validation begin.");

        if (id < 0) {
            logger.error("Invalid id: " + id);
            throw new InvalidProductException(ErrorCode.NEGATIVE_ID);
        }
    }
}
