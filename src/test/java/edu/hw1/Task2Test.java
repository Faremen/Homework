package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    @Test
    public void countDigitsZero() {
        // Given
        int inputNumber = 0;
        int expectedCount = 1;

        // When
        int actualCount = Task2.countDigits(inputNumber);

        // Then
        assertThat(actualCount).isEqualTo(expectedCount);
    }

    @Test
    public void countDigitsOneDigitNumber() {
        // Given
        int inputNumber = 5;
        int expectedCount = 1;

        // When
        int actualCount = Task2.countDigits(inputNumber);

        // Then
        assertThat(actualCount).isEqualTo(expectedCount);
    }

    @Test
    public void countDigitsFourDigitNumber() {
        // Given
        int inputNumber = 5555;
        int expectedCount = 4;


        // When
        int actualCount = Task2.countDigits(inputNumber);

        // Then
        assertThat(actualCount).isEqualTo(expectedCount);
    }

    @Test
    public void countDigitsNegativeFourDigitNumber() {
        // Given
        int inputNumber = -5555;
        int expectedCount = 4;

        // When
        int actualCount = Task2.countDigits(inputNumber);

        // Then
        assertThat(actualCount).isEqualTo(expectedCount);
    }
}
