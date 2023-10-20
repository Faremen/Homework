package edu.hw2.task1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MultiplicationTest {

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
            Arguments.of(new Constant(10),  new Constant(0), 0),   // (10) * (0)  = 0
            Arguments.of(new Constant(-10), new Constant(0), 0),   // (-10) * (0) = 0
            Arguments.of(new Constant(4),   new Constant(3), 12),  // (4) * (3) = 12
            Arguments.of(new Constant(0),   new Constant(0), 0),   // (0) * (0) = 0
            Arguments.of(new Constant(-2),  new Constant(3), -6),  // (-2) * (3) = -6
            Arguments.of(new Constant(-2),  new Constant(2), -4)   // (-2) * (2) = -4
        );
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    public void evaluate_InputTwoExpr_ResultNumber(Expr firstExpr, Expr secondExponent, double expectedNumber) {
        // When
        Multiplication multiplication = new Multiplication(firstExpr, secondExponent);
        double actualNumber = multiplication.evaluate();

        // Then
        assertThat(actualNumber).isEqualTo(expectedNumber);
    }
}
