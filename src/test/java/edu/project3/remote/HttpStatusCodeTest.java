package edu.project3.remote;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class HttpStatusCodeTest {
    @ParameterizedTest
    @CsvSource(value = {
        "100, Continue",
        "200, OK",
        "404, Not Found",
        "505, HTTP Version Not Supported"
    })
    public void getByValue_InputCode_ResultHttpStatusCode(int code, String expected) {
        // When
        HttpStatusCode actual = HttpStatusCode.getByValue(code);

        // Then
        assertThat(actual.toString()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "600",
        "-1",
        "0"
    })
    public void getByValue_InputIncorrectCode_ResultIllegalArgumentException(int value) {

        assertThatThrownBy(() -> HttpStatusCode.getByValue(value))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
