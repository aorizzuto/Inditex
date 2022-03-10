package com.inditex.challenge.validator;

import com.inditex.challenge.exception.ErrorCode;

import java.text.SimpleDateFormat;

public class DateValidator {

    public static void validateFormat(String applyDate) throws Exception {

        SimpleDateFormat dateFormatterWithHHMMSS = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");

        checkFormat(applyDate, dateFormatterWithHHMMSS);
    }

    private static void checkFormat(String applyDate, SimpleDateFormat dateFormatter) throws Exception {
        try {
            dateFormatter.parse(applyDate);
        } catch (Exception e) {
            throw new Exception(ErrorCode.BAD_DATE_FORMAT);
        }
    }
}
