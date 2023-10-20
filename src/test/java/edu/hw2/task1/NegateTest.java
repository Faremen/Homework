package edu.hw2.task1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NegateTest {

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
            Arguments.of(new Constant(4), -4),                                      // -(4)     = -4
            Arguments.of(new Constant(0), 0),                                       // -(0)     = 0
            Arguments.of(new Constant(1.1), -1.1),                                  // -(1.1)   = -1.1
            Arguments.of(new Constant(0.1), -0.1),                                  // -(0.1)   = -0.1
            Arguments.of(new Addition(new Constant(2), new Constant(4)), -6),  // -(2 + 4) = -6
            Arguments.of(new Negate(new Constant(1)), 1)                            // -(-(1))  = 1
        );
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    public void evaluate_InputExpr_ResultNegativeExpr(Expr inputExpr, double expectedNumber) {
        // When
        Negate negate = new Negate(inputExpr);
        double actualNumber = negate.evaluate();

        // Then
        assertThat(actualNumber).isEqualTo(expectedNumber);
    }
}
