package edu.hw2.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AdditionTest {

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
            Arguments.of(new Constant(4),              new Constant(3), 7),  // (4 + 3)      = 7
            Arguments.of(new Constant(-4),             new Constant(3), -1), // (-4 + 3)     = -1
            Arguments.of(new Negate(new Constant(10)), new Constant(3), -7), // (-(10) + 3)  = -7
            Arguments.of(new Constant(0),              new Constant(0), 0)   // (0 + 0)      = 0
        );
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    public void evaluate_InputTwoExpr_ResultNumber(Expr firstExpr, Expr secondExpr, double expectedNumber) {
        // When
        Addition addition = new Addition(firstExpr, secondExpr);
        double actualNumber = addition.evaluate();

        // Then
        assertThat(actualNumber).isEqualTo(expectedNumber);
    }

    @Test
    @DisplayName("Big expr: ((2 + 4) * (-(1)))^2 + 1 = 37")
    public void evaluate_InputBigExpr_ResultNumber() {
        // Given
        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Constant(1));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, 2);
        var res = new Addition(exp, new Constant(1));

        double expectedNumber = 37;

        // When
        double actualNumber = res.evaluate();

        // Then
        assertThat(actualNumber).isEqualTo(expectedNumber);

    }
}
