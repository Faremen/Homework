package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task4Test {

    @Test
    public void fixStringEvenLengthInputString() {
        // Given
        String inputStr = "hTsii  s aimex dpus rtni.g";
        String expectedStr = "This is a mixed up string.";

        // When
        String actualSrt = Task4.fixString(inputStr);

        // Then
        assertThat(actualSrt).isEqualTo(expectedStr);
    }

    @Test
    public void fixStringOddLengthString() {
        // Given
        String inputStr = "12345";
        String expectedExceptionMessage = "Length string must be even";

        assertThatThrownBy(() -> {
            Task4.fixString(inputStr);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage(expectedExceptionMessage);
    }

    @Test
    public void fixStringEmptyString() {
        // Given
        String inputStr = "";
        String expectedExceptionMessage = "Empty string";

        assertThatThrownBy(() -> {
            Task4.fixString(inputStr);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage(expectedExceptionMessage);
    }

    @Test
    public void fixStringLengthStringEqualsOne() {
        // Given
        String inputStr = "9";
        String expectedExceptionMessage= "Length string must be even";

        assertThatThrownBy(() -> {
            Task4.fixString(inputStr);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage(expectedExceptionMessage);
    }
}
