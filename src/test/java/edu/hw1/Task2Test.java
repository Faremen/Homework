package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    @Test
    public void testCountDigitsOneSignificantNumber() {
        // Given
        int inputNumber = 0;

        // When
        int count = Task2.countDigits(inputNumber);

        // Then
        assertThat(count).isEqualTo(1);
    }

    @Test
    public void testCountDigitsFourSignificantNumber() {
        // Given
        int inputNumber = 5555;

        // When
        int count = Task2.countDigits(inputNumber);

        // Then
        assertThat(count).isEqualTo(4);
    }

    @Test
    public void testCountDigitsNegativeFourSignificantNumber() {
        // Given
        int inputNumber = -5555;

        // When
        int count = Task2.countDigits(inputNumber);

        // Then
        assertThat(count).isEqualTo(4);
    }
}
