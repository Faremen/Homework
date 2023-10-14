package edu.hw1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task7Test {

    private static Stream<Arguments> rotateLeft_ProvideParameters() {
        return Stream.of(
            Arguments.of(16, 1, 1),                                 // 10000 (16) -> 00001 (1)
            Arguments.of(17, 2, 6),                                 // 10001 (17) -> 00110 (6)
            Arguments.of(27, 2, 15),                                // 11011 (27) -> 01111 (15)
            Arguments.of(27, 10, 27),                               // 11011 (27) -> 11011 (27)
            Arguments.of(Integer.MAX_VALUE, 10, Integer.MAX_VALUE), // 1111...1111 -> 1111...1111
            Arguments.of(1, Integer.MAX_VALUE, 1),
            Arguments.of(63, 20, 63)                                // 111111 (63) -> 111111 (63)
        );
    }

    @ParameterizedTest
    @MethodSource("rotateLeft_ProvideParameters")
    public void rotateLeft_InputNumberAndShift_ResultCyclicallyShiftNumber(
        int inputNumber,
        int inputShift,
        int expectedNumber
    ) {
        // When
        int actualNumber = Task7.rotateLeft(inputNumber, inputShift);

        // Then
        assertThat(actualNumber).isEqualTo(expectedNumber);
    }

    private static Stream<Arguments> rotateRight_ProvideParameters() {
        return Stream.of(
            Arguments.of(8, 1, 4),                                  // 1000 (8) -> 0100 (4)
            Arguments.of(19, 2, 28),                                // 10011 (19) -> 11100 (28)
            Arguments.of(16, 1, 8),                                 // 10000 (16) -> 01000 (8)
            Arguments.of(17, 2, 12),                                // 10001 (17) -> 01100 (12)
            Arguments.of(27, 2, 30),                                // 11011 (27) -> 11110 (30)
            Arguments.of(27, 10, 27),                                // 11011 (27) -> 11011 (27)
            Arguments.of(Integer.MAX_VALUE, 10, Integer.MAX_VALUE), // 1111...1111 -> 1111...1111
            Arguments.of(1, Integer.MAX_VALUE, 1),
            Arguments.of(63, 20, 63)                                // 111111 (63) -> 111111 (63)
        );
    }

    @ParameterizedTest
    @MethodSource("rotateRight_ProvideParameters")
    public void rotateRight_InputNumberAndShift_ResultCyclicallyShiftNumber(
        int inputNumber,
        int inputShift,
        int expectedNumber
    ) {
        // When
        int actualNumber = Task7.rotateRight(inputNumber, inputShift);

        // Then
        assertThat(actualNumber).isEqualTo(expectedNumber);
    }

    private static Stream<Arguments> provideParametersForException() {
        return Stream.of(
            Arguments.of(0, 0, "First and second arguments must be more than 0"),
            Arguments.of(0, 2, "First argument must be more than 0"),
            Arguments.of(27, 0, "Second argument must be more than 0"),
            Arguments.of(-10, -2, "First and second arguments must be more than 0"),
            Arguments.of(10, -2, "Second argument must be more than 0"),
            Arguments.of(-10, 2, "First argument must be more than 0")
        );
    }

    @ParameterizedTest
    @MethodSource("provideParametersForException")
    public void rotateLeft_InputInvalidNumberAndShift_ResultThrowIllegalArgumentException(
        int inputNumber,
        int inputShift,
        String expectedMessage
    ) {
        assertThatThrownBy(() -> {
            Task7.rotateLeft(inputNumber, inputShift);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage(expectedMessage);
    }

    @ParameterizedTest
    @MethodSource("provideParametersForException")
    public void rotateRight_InputInvalidNumberAndShift_ResultThrowIllegalArgumentException(
        int inputNumber,
        int inputShift,
        String expectedMessage
    ) {
        assertThatThrownBy(() -> {
            Task7.rotateRight(inputNumber, inputShift);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage(expectedMessage);
    }
}
