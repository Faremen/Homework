package edu.hw5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task6Test {

    @ParameterizedTest
    @CsvSource(value = {
        "achfdbaabgabcaabg, abc, true",
        "123[abc]asds, [abc], true",
        "123[abc]asds, [, true",
        "123, d, false",
        "-.*--, .*, true",
        "'', dsdsd, false"
    })
    public void isContains_InputStringAndSubstring_ResultContainsSubstringInString(String string, String substring, boolean expected) {
        // When
        var actual = Task6.isContains(string, substring);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void isContains_InputNullStringAndNullSubstring_ResultNullPointerException() {
        assertThatThrownBy(() -> Task6.isContains(null, null))
            .isInstanceOf(NullPointerException.class);
    }
}
