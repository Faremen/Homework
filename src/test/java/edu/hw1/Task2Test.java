package edu.hw1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
            Arguments.of(0, 1),
            Arguments.of(5, 1),
            Arguments.of(5555, 4),
            Arguments.of(-5555, 4),
            Arguments.of(Integer.MAX_VALUE, 10)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    public void countDigits_InputNumber_ResultCountDigits(int inputNumber, int expectedCount) {
        // When
        int actualCount = Task2.countDigits(inputNumber);

        // Then
        assertThat(actualCount).isEqualTo(expectedCount);
    }
}
