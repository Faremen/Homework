package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {

    @ParameterizedTest
    @CsvSource(value = {
        "А123ВЕ777, true",
        "А123ВЕ13, true",
        "О777ОО177, true",
        "123АВЕ777, false",
        "А123ВГ77, false",
        "А123ВЕ7777, false",
        "AAAAА123ВЕ777, false",
        "А12323ВЕ777, false"
    })
    public void checkRusCarNumber(String number, boolean expected) {
        // When
        var actual = Task5.checkRusCarNumber(number);

        // Then
        assertThat(actual).isEqualTo(expected);
    }
}
