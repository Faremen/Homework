package edu.hw5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task7Test {

    @ParameterizedTest
    @CsvSource(value = {
        "01001001001,   true",
        "00001111,      true",
        "10100101,      false",
        "001,           false",
        "11000,         true",
        "00100,         false",
        "110,           true",
        "0,             false",
        "ds0s,          false",
        "ds,            false"
    })
    public void isContainsLeastThreeCharactersThirdCharacterBeingZero_InputString_ResultBoolean(
        String inputSrt,
        Boolean expected
    ) {
        // When
        var actual = Task7.isContainsLeastThreeCharactersThirdCharacterBeingZero(inputSrt);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void isContainsLeastThreeCharactersThirdCharacterBeingZero_InputNullString_ResultNullPointerException() {
        assertThatThrownBy(() -> Task7.isContainsLeastThreeCharactersThirdCharacterBeingZero(null))
            .isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "01001001001,   false",
        "00001111,      false",
        "10100101,      true",
        "001,           false",
        "11000,         false",
        "00100,         true",
        "110,           false",
        "00,            true",
        "0,             true",
        "1,             true",
        "a1a,           false",
        "0aaa0,         false"
    })
    public void isStartsAndEndsSameCharacter_InputString_ResultBoolean(String inputSrt, Boolean expected) {
        // When
        var actual = Task7.isStartsAndEndsSameCharacter(inputSrt);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void isStartsAndEndsSameCharacter_InputNullString_ResultNullPointerException() {
        assertThatThrownBy(() -> Task7.isStartsAndEndsSameCharacter(null))
            .isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "01001001001,   false",
        "00001111,      false",
        "10100101,      false",
        "001,           true",
        "11000,         false",
        "00100,         false",
        "110,           true",
        "11,            true",
        "0,             true",
        "abc,           false",
        "'',            false"
    })
    public void isLengthFromOneToThree_InputString_ResultBoolean(String inputSrt, Boolean expected) {
        // When
        var actual = Task7.isLengthFromOneToThree(inputSrt);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void isLengthFromOneToThree_InputNullString_ResultNullPointerException() {
        assertThatThrownBy(() -> Task7.isLengthFromOneToThree(null))
            .isInstanceOf(NullPointerException.class);
    }
}
