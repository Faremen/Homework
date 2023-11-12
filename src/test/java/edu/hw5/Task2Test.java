package edu.hw5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task2Test {

    private static Stream<Arguments> findAllFridaysThirteenthInYear_ProvideParameters() {
        return Stream.of(
            Arguments.of(
                1925,
                new LocalDate[] {
                    LocalDate.of(1925, 2, 13),
                    LocalDate.of(1925, 3, 13),
                    LocalDate.of(1925, 11, 13)
                }
            ),
            Arguments.of(
                2024,
                new LocalDate[] {
                    LocalDate.of(2024, 9, 13),
                    LocalDate.of(2024, 12, 13)
                }
            )
        );
    }

    @ParameterizedTest
    @MethodSource("findAllFridaysThirteenthInYear_ProvideParameters")
    public void findAllFridaysThirteenthInYear_InputYear_ResultAllDateFridaysThirteenthInYear(
        int year,
        LocalDate[] expected
    ) {
        // When
        var actual = Task2.findAllFridaysThirteenthInYear(year).toArray();

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findAllFridaysThirteenthInYear_InputNegativeYear_ResultIllegalArgumentException() {
        int year = -100;

        assertThatThrownBy(() -> Task2.findAllFridaysThirteenthInYear(year))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("year < 0");
    }

    private static Stream<Arguments> findNextFridayThirteenth_ProvideParameters() {
        return Stream.of(
            Arguments.of(
                LocalDate.of(1925, 1, 1),
                LocalDate.of(1925, 2, 13)
            ),
            Arguments.of(
                LocalDate.of(1925, 2, 13),
                LocalDate.of(1925, 3, 13)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("findNextFridayThirteenth_ProvideParameters")
    public void findNextFridayThirteenth_InputDate_ResultDateNextFridayThirteenth(LocalDate date, LocalDate expected) {
        // When
        var actual = Task2.findNextFridayThirteenth(date);

        // Then
        assertThat(actual).isEqualTo(expected);
    }
}
