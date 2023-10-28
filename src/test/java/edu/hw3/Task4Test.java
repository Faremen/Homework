package edu.hw3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class Task4Test {

    @ParameterizedTest
    @CsvSource(value = {
        "1,    I",
        "2,    II",
        "12,   XII",
        "16,   XVI",
        "3999, MMMCMXCIX"
    })
    public void convertToRoman_InputArabicNumber_ResultRomanNumber(int inputNumber, String expected) {
        // When
        String actual = Task4.convertToRoman(inputNumber);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "-1",
        "0",
        "4000",
        "" + Integer.MAX_VALUE,
        "" + Integer.MIN_VALUE
    })
    public void convertToRoman_InputInvalidNumber_ResultIllegalArgumentException(int inputNumber) {
        assertThatThrownBy(() -> {
            Task4.convertToRoman(inputNumber);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("number must be within the boundaries [1, 3999]");
    }
}
