package edu.hw1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task4Test {

    @ParameterizedTest
    @CsvSource(value = {
        "hTsii  s aimex dpus rtni.g, This is a mixed up string.",
        "123456, 214365",
        "badce, abcde"
    })
    public void fixString_InputString_ResultStringWhichPairsCharactersSwapped(String inputStr, String expectedStr) {
        // When
        String actualSrt = Task4.fixString(inputStr);

        // Then
        assertThat(actualSrt).isEqualTo(expectedStr);
    }

    @Test
    public void fixString_InputEmptyString_ResultThrowIllegalArgumentException() {
        // Given
        String inputStr = "";
        String expectedExceptionMessage = "Empty string";

        assertThatThrownBy(() -> {
            Task4.fixString(inputStr);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage(expectedExceptionMessage);
    }

    @Test
    public void fixString_InputNullString_ResultNullPointerException() {
        // Given
        String inputStr = null;

        assertThatThrownBy(() -> {
            Task4.fixString(inputStr);
        }).isInstanceOf(NullPointerException.class);
    }
}
