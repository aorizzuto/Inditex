package com.inditex.challenge.validator;

import com.inditex.challenge.exception.ErrorCode;
import com.inditex.challenge.exception.business.BadDateFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

public class DateValidator {

    private static final Logger logger = LoggerFactory.getLogger(DateValidator.class);

    public static void validateFormat(String applyDate) throws BadDateFormatException {

        logger.info("Chain validation begin.");

        SimpleDateFormat dateFormatterWithHHMMSS = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");

        checkFormat(applyDate, dateFormatterWithHHMMSS);
    }

    private static void checkFormat(String applyDate, SimpleDateFormat dateFormatter) throws BadDateFormatException {
        try {
            dateFormatter.parse(applyDate);
        } catch (Exception e) {
            logger.error("Bad format in date: " + applyDate);
            throw new BadDateFormatException(ErrorCode.BAD_DATE_FORMAT);
        }
    }
}
