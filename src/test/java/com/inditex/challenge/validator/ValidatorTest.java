package com.inditex.challenge.validator;

import com.inditex.challenge.exception.ErrorCode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {

    @ParameterizedTest
    @ValueSource(ints = {-3, -20, -11, -1, -23453})
    public void WHEN_Brand_Id_Is_Negative_THEN_Throw_Exception(Integer value) {
        Exception exception = assertThrows(Exception.class, () -> {
            ChainValidator.validate(value);
        });

        String expectedMessage = ErrorCode.NEGATIVE_ID;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @ParameterizedTest
    @ValueSource(ints = {-3, -20, -11, -1, -23453})
    public void WHEN_Product_Id_Is_Negative_THEN_Throw_Exception(Integer value) {
        Exception exception = assertThrows(Exception.class, () -> {
            ProductValidator.validate(value);
        });

        String expectedMessage = ErrorCode.NEGATIVE_ID;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2020/03/04", "2020-03-04", "03-04-2020-00.00", ""})
    public void WHEN_Date_Format_Is_Incorrect_THEN_Throw_Exception(String date) {
        Exception exception = assertThrows(Exception.class, () -> {
            DateValidator.validateFormat(date);
        });

        String expectedMessage = ErrorCode.BAD_DATE_FORMAT;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 20, 11, 1, 23453})
    public void WHEN_Brand_Id_Is_OK_THEN_Success(Integer value) {
        assertDoesNotThrow(() -> {
            ChainValidator.validate(value);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 20, 11, 1, 23453})
    public void WHEN_Product_Id_OK_THEN_Success(Integer value) {
        assertDoesNotThrow(() -> {
            ProductValidator.validate(value);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"2020-03-04-00.00.30", "2020-03-04-12.32.01", "2003-04-20-00.00.00"})
    public void WHEN_Date_Format_Is_Correct_THEN_Success(String date) {
        assertDoesNotThrow(() -> {
            DateValidator.validateFormat(date);
        });
    }
}
