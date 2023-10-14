package edu.hw1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {

    @ParameterizedTest
    @CsvSource(value = {
        "11,        true",
        "11211230,  true",
        "363,       true",
        "36324,     false",
        "8,         false",
        "-121,      true"
    })
    public void isPalindromeDescendant_InputNumber_ResultIsNumberPalindromesDescendant(
        int inputNumber,
        boolean expectedIsPalindromeDescendant
    ) {
        // When
        boolean actualIsPalindromeDescendant = Task5.isPalindromeDescendant(inputNumber);

        // Then
        assertThat(actualIsPalindromeDescendant).isEqualTo(expectedIsPalindromeDescendant);
    }
}
