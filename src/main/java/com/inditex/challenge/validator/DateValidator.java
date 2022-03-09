package com.inditex.challenge.validator;

import com.inditex.challenge.exception.ErrorCode;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class DateValidator {

    public static void validateFormat(String applyDate) throws Exception {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd", Locale.US);

        try {
            LocalDate.parse(applyDate, dateFormatter);
        } catch (DateTimeParseException e) {
            throw new Exception(ErrorCode.BAD_DATE_FORMAT);
        }
    }
}
