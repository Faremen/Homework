package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task6Test {

    @Test
    public void countKNumber3524ResultCount3() {
        // Given
        int inputNumber = 3524;
        int expectedCountK = 3;

        // When
        int actualCountK = Task6.countK(inputNumber);

        // Then
        assertThat(actualCountK).isEqualTo(expectedCountK);
    }

    // Для 1000 постоянную Капрекара тоже можно вычислить
    @Test
    public void countKNumber1000ResultCount5() {
        // Given
        int inputNumber = 1000;
        int expectedCountK = 5;

        // When
        int actualCountK = Task6.countK(inputNumber);

        // Then
        assertThat(actualCountK).isEqualTo(expectedCountK);
    }

    @Test
    public void countKNumber9998ResultCount5() {
        // Given
        int inputNumber = 9998;
        int expectedCountK = 5;

        // When
        int actualCountK = Task6.countK(inputNumber);

        // Then
        assertThat(actualCountK).isEqualTo(expectedCountK);
    }

    @Test
    public void countKNumberGreaterThan9999ThrowIllegalArgumentException() {
        int inputNumber = 9999999;

        assertThatThrownBy(() -> {
            Task6.countK(inputNumber);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Number must be greater than 999 and less than 9999");
    }

    @Test
    public void countKNumberLessThan1000ThrowIllegalArgumentException() {
        int inputNumber = 0;

        assertThatThrownBy(() -> {
            Task6.countK(inputNumber);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Number must be greater than 999 and less than 9999");
    }

    @Test
    public void countKNegativeNumberThrowIllegalArgumentException() {
        int inputNumber = -4321;

        assertThatThrownBy(() -> {
            Task6.countK(inputNumber);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Number must be greater than 999 and less than 9999");
    }

    @Test
    public void countKNumberContainsFourIdenticalDigitsThrowIllegalArgumentException() {
        int inputNumber = 4444;

        assertThatThrownBy(() -> {
            Task6.countK(inputNumber);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Four identical digits are not allowed");
    }
}
