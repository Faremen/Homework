package edu.project1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class HiddenWordTest {
    private HiddenWord hiddenWord;

    @BeforeEach
    public void setUp() {
        hiddenWord = new HiddenWord("Слово", '*');
    }

    @Test
    public void getMaskedWord_NotInputSymbols_ResultCompletelyMaskedWord() {
        // Given
        String expectedResult = "*****";

        // When
        String actualResult = hiddenWord.getMaskedWord();

        // Then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> guessSymbol_ProvideParameters() {
        return Stream.of(
            Arguments.of("С", new GuessResult(true, true)),
            Arguments.of("СС", new GuessResult(false, true)),
            Arguments.of("1", new GuessResult(true, false)),
            Arguments.of("11", new GuessResult(false, false))
        );
    }

    @ParameterizedTest
    @MethodSource("guessSymbol_ProvideParameters")
    public void guessSymbol_InputSymbols_ResultGuessResult(String inputSymbols, GuessResult expectedResult) {
        // When
        GuessResult actualResult = null;
        for (int i = 0; i < inputSymbols.length(); i++) {
            actualResult = hiddenWord.guessSymbol(inputSymbols.charAt(i));
        }

        // Then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "1,             *****",
        "С,             С****",
        "о,             **о*о",
        "Св,            С**в*",
        "Сол,           Сло*о",
        "олСв,          Слово",
        "Слововывывы,   Слово"
    })
    public void getMaskedWord_InputSymbols_ResultMaskedWord(String inputSymbols, String expectedResult) {
        // When
        inputSymbols.chars().forEach((c) -> hiddenWord.guessSymbol((char) c));
        String actualResult = hiddenWord.getMaskedWord();

        // Then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "1,             false",
        "С,             false",
        "о,             false",
        "Св,            false",
        "Сол,           false",
        "олСв,          true",
        "Слововывывы,   true"
    })
    public void isWordGuessed_InputSymbols_ResultIsWordGuessed(String inputSymbols, boolean expectedResult) {
        // When
        inputSymbols.chars().forEach((c) -> hiddenWord.guessSymbol((char) c));
        boolean actualResult = hiddenWord.isWordGuessed();

        // Then
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}


