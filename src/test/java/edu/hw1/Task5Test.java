package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {

    @Test
    public void isPalindromeDescendantPalindromeEvenLength() {
        // Given
        int inputNumber = 11;

        // When
        boolean actualIsPalindromeDescendant = Task5.isPalindromeDescendant(inputNumber);

        // Then
        assertThat(actualIsPalindromeDescendant).isTrue();
    }

    @Test
    public void isPalindromeDescendantNotPalindromeEvenLength() {
        // Given
        int inputNumber = 11211230;

        // When
        boolean actualIsPalindromeDescendant = Task5.isPalindromeDescendant(inputNumber);

        // Then
        assertThat(actualIsPalindromeDescendant).isTrue();
    }

    @Test
    public void isPalindromeDescendantPalindromeOddLength() {
        // Given
        int inputNumber = 363;

        // When
        boolean actualIsPalindromeDescendant = Task5.isPalindromeDescendant(inputNumber);

        // Then
        assertThat(actualIsPalindromeDescendant).isTrue();
    }

    @Test
    public void isPalindromeDescendantNotPalindromeOddLength() {
        // Given
        int inputNumber = 36324;

        // When
        boolean actualIsPalindromeDescendant = Task5.isPalindromeDescendant(inputNumber);

        // Then
        assertThat(actualIsPalindromeDescendant).isFalse();
    }

    @Test
    public void isPalindromeDescendantValueLengthEqualsOne() {
        // Given
        int inputNumber = 8;

        // When
        boolean actualIsPalindromeDescendant = Task5.isPalindromeDescendant(inputNumber);

        // Then
        assertThat(actualIsPalindromeDescendant).isFalse();
    }

    @Test
    public void isPalindromeDescendantNegativePalindrome() {
        // Given
        int inputNumber = -121;

        // When
        boolean actualIsPalindromeDescendant = Task5.isPalindromeDescendant(inputNumber);

        // Then
        assertThat(actualIsPalindromeDescendant).isTrue();
    }
}
