package edu.hw5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task4Test {

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
            Arguments.of("password",     false),
            Arguments.of("pas|sword",    true),
            Arguments.of("|pa!!sswo~rd", true),
            Arguments.of("!!!!password", true),
            Arguments.of("~!@#$%^&*|",   true)
        );
    }


    @ParameterizedTest
    @MethodSource("provideParameters")
    public void checkPassword_InputPassword_ResultContainsRequiredCharsInPassword(String password, boolean expected) {
        // When
        var actual = Task4.checkPassword(password);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void checkPassword_InputNullPassword_ResultNullPointerException() {
        assertThatThrownBy(() -> Task4.checkPassword(null))
            .isInstanceOf(NullPointerException.class);
    }
}
