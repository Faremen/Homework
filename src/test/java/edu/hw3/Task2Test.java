package edu.hw3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task2Test {

    private static Stream<Arguments> clusterize_ProvideParameters() {
        return Stream.of(
            Arguments.of("()()()",                  new String[] {"()", "()", "()"}),
            Arguments.of("((()))",                  new String[] {"((()))"}),
            Arguments.of("((()))(())()()(()())",    new String[] {"((()))", "(())", "()", "()", "(()())"}),
            Arguments.of("((())())(()(()()))",      new String[] {"((())())", "(()(()()))"}),
            Arguments.of("123",                     null),
            Arguments.of("  ()",                    null),
            Arguments.of("ds()ds",                  null),
            Arguments.of(")(",                      null),
            Arguments.of("(((((",                   null),
            Arguments.of("(",                       null),
            Arguments.of("",                        null),
            Arguments.of("())",                     null),
            Arguments.of("()(",                     null)
        );
    }

    @ParameterizedTest
    @MethodSource("clusterize_ProvideParameters")
    public void clusterize_InputString_ResultStringArray(String inputStr, String[] expected) {
        // When
        String[] actual = Task2.clusterize(inputStr);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullSource
    public void clusterize_InputNull_ResultNullPointerException(String inputStr) {
        assertThatThrownBy(() -> {
            Task2.clusterize(inputStr);
        }).isInstanceOf(NullPointerException.class);
    }
}
