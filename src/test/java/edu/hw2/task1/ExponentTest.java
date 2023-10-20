package edu.hw2.task1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ExponentTest {

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
            Arguments.of(new Constant(10), 0, 1),   // (10)^0  = 1
            Arguments.of(new Constant(-10), 0, 1),  // (-10)^0 = 1
            Arguments.of(new Constant(4), 3, 64),   // (4)^3 = 64
            Arguments.of(new Constant(0), 0, 1),    // (0)^0 = 1
            Arguments.of(new Constant(-2), 3, -8),  // (-2)^3 = 8
            Arguments.of(new Constant(-2), 2, 4)    // (-2)^2 = 4
        );
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    public void evaluate_InputExprAndExponent_ResultNumber(Expr firstExpr, int inputExponent, double expectedNumber) {
        // When
        Exponent exponent = new Exponent(firstExpr, inputExponent);
        double actualNumber = exponent.evaluate();

        // Then
        assertThat(actualNumber).isEqualTo(expectedNumber);
    }
}
