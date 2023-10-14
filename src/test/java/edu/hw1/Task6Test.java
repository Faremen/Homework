package edu.hw1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task6Test {

    @ParameterizedTest
    @CsvSource(value = {
        "3524, 3",
        // Для 1000 постоянную Капрекара тоже можно вычислить: 1000 => 1000 - 0001 = 0999 => 9990 - 0999 => ...
        "1000, 5",
        "9998, 5",
        "6174, 0"
    })
    public void countK_InputNumber_ResultCountK(int inputNumber, int expectedCountK) {
        // When
        int actualCountK = Task6.countK(inputNumber);

        // Then
        assertThat(actualCountK).isEqualTo(expectedCountK);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "9999999,   Number must be greater than 999 and less than 9999",
        "0,         Number must be greater than 999 and less than 9999",
        "-4321,     Number must be greater than 999 and less than 9999",
        "4444,      Four identical digits are not allowed"
    })
    public void countK_InputNumber_ResultThrowIllegalArgumentException(int inputNumber, String expectedMessage) {
        assertThatThrownBy(() -> {
            Task6.countK(inputNumber);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage(expectedMessage);
    }
}
