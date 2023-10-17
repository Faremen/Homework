package edu.project1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.ByteArrayInputStream;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HangmanGameTest {
    @ParameterizedTest
    @CsvSource(value = {
        "1,     true",
        "123,   true",
        "Слово, false"
    })
    public void initHangmanGame_InputWord_ResultIsEndGame(String word, boolean expectedResult) {
        // When
        HangmanGame hangmanGame = new HangmanGame(word);
        boolean actualResult = hangmanGame.getIsEndGame();

        // Then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "1,     false",
        "123,   false",
        "0,     true",
        "-100,  true"
    })
    public void initHangmanGame_InputCountAttempts_ResultIsEndGame(int countAttempts, boolean expectedResult) {
        // When
        HangmanGame hangmanGame = new HangmanGame("Слово", countAttempts);
        boolean actualResult = hangmanGame.getIsEndGame();

        // Then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> run_InputSymbols_ResultIsEndGame_ProvideParameters() {
        return Stream.of(
            Arguments.of("",                false),
            Arguments.of("\n",              false),
            Arguments.of("1111",            false),
            Arguments.of("1\n2\n3\n4\n5",   true),
            Arguments.of("С\nл\nо\nв",      true),
            Arguments.of("С\nл\nо\nВ",      true)
        );
    }

    @ParameterizedTest
    @MethodSource("run_InputSymbols_ResultIsEndGame_ProvideParameters")
    public void run_InputSymbols_ResultIsEndGame(String inputSymbols, boolean expectedResult) {
        // When
        System.setIn(new ByteArrayInputStream(inputSymbols.getBytes()));
        HangmanGame hangmanGame = new HangmanGame("Слово");
        hangmanGame.run();
        boolean actualResult = hangmanGame.getIsEndGame();

        // Then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> run_InputSymbols_ResultAttempts_ProvideParameters() {
        return Stream.of(
            Arguments.of("",                5),
            Arguments.of("\n",              5),
            Arguments.of("1111",            5),
            Arguments.of("1111\n1\n2",      3),
            Arguments.of("1",               4),
            Arguments.of("1\n1\n1\n1",      4),
            Arguments.of("1\n2\n3",         2),
            Arguments.of("1\n2\n3\n4\n5",   0),
            Arguments.of("С",               5),
            Arguments.of("С\n1\n2",         3),
            Arguments.of("С\nл\nо\nв",      5),
            Arguments.of("С\nл\nо\nВ",      5)
        );
    }

    @ParameterizedTest
    @MethodSource("run_InputSymbols_ResultAttempts_ProvideParameters")
    public void run_InputSymbols_ResultAttempts(String inputSymbols, int expectedResult) {
        // When
        System.setIn(new ByteArrayInputStream(inputSymbols.getBytes()));
        HangmanGame hangmanGame = new HangmanGame("Слово", 5);
        hangmanGame.run();
        int actualResult = hangmanGame.getAttempts();

        // Then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> run_InputSymbols_ResultIsAttemptLeft_ProvideParameters() {
        return Stream.of(
            Arguments.of("",                false),
            Arguments.of("\n",              false),
            Arguments.of("1111",            false),
            Arguments.of("1111\n1\n2",      false),
            Arguments.of("1",               false),
            Arguments.of("1\n1\n1\n1",      false),
            Arguments.of("1\n2\n3",         false),
            Arguments.of("1\n2\n3\n4\n5",   true),
            Arguments.of("С",               false),
            Arguments.of("С\n1\n2",         false),
            Arguments.of("С\nл\nо\nв",      false),
            Arguments.of("С\nл\nо\nВ",      false)
        );
    }

    @ParameterizedTest
    @MethodSource("run_InputSymbols_ResultIsAttemptLeft_ProvideParameters")
    public void run_InputSymbols_ResultIsAttemptLeft(String inputSymbols, boolean expectedResult) {
        // When
        System.setIn(new ByteArrayInputStream(inputSymbols.getBytes()));
        HangmanGame hangmanGame = new HangmanGame("Слово", 5);
        hangmanGame.run();
        boolean actualResult = hangmanGame.getIsAttemptLeft();

        // Then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> run_InputSymbols_ResultIsWordGuessed_ProvideParameters() {
        return Stream.of(
            Arguments.of("",                false),
            Arguments.of("\n",              false),
            Arguments.of("1111",            false),
            Arguments.of("1111\n1\n2",      false),
            Arguments.of("1",               false),
            Arguments.of("1\n1\n1\n1",      false),
            Arguments.of("1\n2\n3",         false),
            Arguments.of("1\n2\n3\n4\n5",   false),
            Arguments.of("С",               false),
            Arguments.of("С\n1\n2",         false),
            Arguments.of("С\nл\nо\nв",      true),
            Arguments.of("С\nл\nо\nВ",      true)
        );
    }

    @ParameterizedTest
    @MethodSource("run_InputSymbols_ResultIsWordGuessed_ProvideParameters")
    public void run_InputSymbols_ResultIsWordGuessed(String inputSymbols, boolean expectedResult) {
        // When
        System.setIn(new ByteArrayInputStream(inputSymbols.getBytes()));
        HangmanGame hangmanGame = new HangmanGame("Слово", 5);
        hangmanGame.run();
        boolean actualResult = hangmanGame.getIsWordGuessed();

        // Then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> run_InputSymbols_ResultMaskedWord_ProvideParameters() {
        return Stream.of(
            Arguments.of("",                "*****"),
            Arguments.of("\n",              "*****"),
            Arguments.of("1111",            "*****"),
            Arguments.of("1111\n1\n2",      "*****"),
            Arguments.of("1",               "*****"),
            Arguments.of("1\n1\n1\n1",      "*****"),
            Arguments.of("1\n2\n3",         "*****"),
            Arguments.of("1\n2\n3\n4\n5",   "*****"),
            Arguments.of("С",               "с****"),
            Arguments.of("о\nо",            "**о*о"),
            Arguments.of("С\n1\n2",         "с****"),
            Arguments.of("С\nл\nо\nв",      "слово"),
            Arguments.of("С\nл\nо\nВ",      "слово")
        );
    }

    @ParameterizedTest
    @MethodSource("run_InputSymbols_ResultMaskedWord_ProvideParameters")
    public void run_InputSymbols_ResultMaskedWord(String inputSymbols, String expectedResult) {
        // When
        System.setIn(new ByteArrayInputStream(inputSymbols.getBytes()));
        HangmanGame hangmanGame = new HangmanGame("Слово", 5);
        hangmanGame.run();
        String actualResult = hangmanGame.getMaskedWord();

        // Then
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
