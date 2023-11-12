package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {

    // Нечетной длины
    @ParameterizedTest
    @CsvSource(value = {
        "01001001001,   true",
        "00001111,      false",
        "10100101,      false",
        "001,           true",
        "11000,         true",
        "00100,         true",
        "110,           true",
        "11,            false",
        "0,             true",
        "1,             true",
        "'',            false",
        "ds0s,          false",
        "ds,            false",
        "dsd,           false"
    })
    public void isOddLength_InputString_ResultBoolean(String inputSrt, Boolean expected) {
        // When
        var actual = Task8.isOddLength(inputSrt);

        // Then
        assertThat(actual).isEqualTo(expected);
    }


    // Начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину
    @ParameterizedTest
    @CsvSource(value = {
        "01001001001,   true",
        "00001111,      false",
        "10100101,      true",
        "001,           true",
        "11000,         false",
        "00100,         true",
        "110,           false",
        "11,            true",
        "0,             true",
        "01,            false",
        "1,             false",
        "'',            false",
        "ds0s,          false",
        "ds,            false",
        "0ds,           false",
        "1dsd,          false"
    })
    public void isStartsZeroAndOddLengthOrStartsOneAndEvenLength_InputString_ResultBoolean(String inputSrt, Boolean expected) {
        // When
        var actual = Task8.isStartsZeroAndOddLengthOrStartsOneAndEvenLength(inputSrt);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    // Количество 0 кратно 3
    @ParameterizedTest
    @CsvSource(value = {
        "01001001001,   false",
        "00001111,      false",
        "10100101,      false",
        "001,           false",
        "11000,         true",
        "00100,         false",
        "110,           false",
        "11,            false",
        "0,             false",
        "000,           true",
        "0001000,       true",
        "000100,        false",
        "01,            false",
        "1,             false",
        "'',            false",
        "ds0s,          false",
        "ds,            false",
        "ds000,         false"
    })
    public void isCountZeroMultipleThree_InputString_ResultBoolean(String inputSrt, Boolean expected) {
        // When
        var actual = Task8.isCountZeroMultipleThree(inputSrt);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    // Любая строка, кроме 11 или 111
    @ParameterizedTest
    @CsvSource(value = {
        "11,            false",
        "111,           false",
        "0111,          true",
        "111000,        true",
        "111111,        true",
        "00100,         true",
        "110,           true",
        "0,             true",
        "000,           true",
        "0001000,       true",
        "000100,        true",
        "01,            true",
        "1,             true",
        "'',            false",
        "ds0s,          false",
        "ds,            false",
        "ds000,         false"
    })
    public void isExcept11Or111_InputString_ResultBoolean(String inputSrt, Boolean expected) {
        // When
        var actual = Task8.isExcept11Or111(inputSrt);

        // Then
        assertThat(actual).isEqualTo(expected);
    }


    // Каждый нечетный символ равен 1
    @ParameterizedTest
    @CsvSource(value = {
        "01001001001,   false",
        "00001111,      false",
        "101,           true",
        "111111,        true",
        "1111110,       false",
        "001,           false",
        "110,           false",
        "11,            true",
        "0,             false",
        "1,             true",
        "000,           false",
        "01,            false",
        "'',            false",
        "ds0s,          false",
        "1d1s1,         false",
        "ds000,         false"
    })
    public void isEachOddCharacterIsOne_InputString_ResultBoolean(String inputSrt, Boolean expected) {
        // When
        var actual = Task8.isEachOddCharacterIsOne(inputSrt);

        // Then
        assertThat(actual).isEqualTo(expected);
    }


    // Нет последовательных 1
    @ParameterizedTest
    @CsvSource(value = {
        "01001001001,   true",
        "00001111,      false",
        "101,           true",
        "111111,        false",
        "1111110,       false",
        "001,           true",
        "110,           false",
        "11,            false",
        "0,             true",
        "1,             true",
        "000,           true",
        "01,            true",
        "'',            false",
        "ds0s,          false",
        "1d1s1,         false",
        "11d1s1,        false",
        "ds000,         false"
    })
    public void isNoConsecutiveOnes_InputString_ResultBoolean(String inputSrt, Boolean expected) {
        // When
        var actual = Task8.isNoConsecutiveOnes(inputSrt);

        // Then
        assertThat(actual).isEqualTo(expected);
    }
}
