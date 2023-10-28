package edu.hw3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task1Test {

    private static Stream<Arguments> atbahs_ProvideParameters() {
        return Stream.of(
            Arguments.of("AbCdEfGhIjKlMnOpQrStUvWxYz", "ZyXwVuTsRqPoNmLkJiHgFeDcBa"),
            Arguments.of("Привет, Мир!", "Привет, Мир!"),
            Arguments.of("!/|\\)([]<>-=+_*&%", "!/|\\)([]<>-=+_*&%"),
            Arguments.of("Hello world!", "Svool dliow!"),
            Arguments.of("", ""),
            Arguments.of(" Z ", " A "),
            Arguments.of("\n  \n", "\n  \n"),
            Arguments.of("Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler", "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi")
        );
    }

    @ParameterizedTest
    @MethodSource("atbahs_ProvideParameters")
    public void atbash_InputString_ResultEncryptedString(String inputStr, String expected) {
        // When
        String actual = Task1.atbash(inputStr);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullSource
    public void atbash_InputNull_ResultNullPointerException(String inputStr) {
        assertThatThrownBy(() -> {
            Task1.atbash(inputStr);
        }).isInstanceOf(NullPointerException.class);
    }
}
