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
    public void beforeAll() {
        hiddenWord = new HiddenWord("Слово", '*');
    }

    @Test
    public void guessSymbol_InputOneHiddenSymbol_ResultSymbolAddedAndContainedInWord() {
        // Given
        char inputChar = 'С';
        GuessResult expectedResult = new GuessResult(true, true);

        // When
        GuessResult actualResult = hiddenWord.guessSymbol(inputChar);

        // Then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void guessSymbol_InputOneNotHiddenSymbol_ResultSymbolAddedAndNotContainedInWord() {
        // Given
        char inputChar = '1';
        GuessResult expectedResult = new GuessResult(true, false);

        // When
        GuessResult actualResult = hiddenWord.guessSymbol(inputChar);

        // Then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void guessSymbol_InputHiddenSymbolTwice_ResultSymbolNotAddedAndContainedInWord() {
        // Given
        char inputChar = 'С';
        GuessResult expectedResult = new GuessResult(false, true);

        // When
        hiddenWord.guessSymbol(inputChar);
        GuessResult actualResult = hiddenWord.guessSymbol(inputChar);

        // Then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void guessSymbol_InputNotHiddenSymbolTwice_ResultSymbolNotAddedAndNotContainedInWord() {
        // Given
        char inputChar = '1';
        GuessResult expectedResult = new GuessResult(false, false);

        // When
        hiddenWord.guessSymbol(inputChar);
        GuessResult actualResult = hiddenWord.guessSymbol(inputChar);

        // Then
        assertThat(actualResult).isEqualTo(expectedResult);
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

    @ParameterizedTest
    @CsvSource(value = {
        "1, *****",
        "С, С****",
        "о, **о*о"
    })
    public void getMaskedWord_InputSymbol_ResultMaskedWord(char inputSymbol, String expectedResult) {
        // When
        hiddenWord.guessSymbol(inputSymbol);
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
}


