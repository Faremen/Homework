package edu.hw5.task3;

import edu.hw5.Task1;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class Task3Test {

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
            Arguments.of(
                " 2020-10-10  ",
                LocalDate.of(2020, 10, 10)
            ),
            Arguments.of(
                "  2020-12-20   ",
                LocalDate.of(2020, 12, 20)
            ),
            Arguments.of(
                "2020-12-2",
                LocalDate.of(2020, 12, 2)
            ),
            Arguments.of(
                "1/3/1976",
                LocalDate.of(1976, 3, 1)
            ),
            Arguments.of(
                " 20/12/1976  ",
                LocalDate.of(1976, 12, 20)
            ),
            Arguments.of(
                "1/3/20",
                LocalDate.of(2020, 3, 1)
            ),
            Arguments.of(
                "  tomorrow  ",
                LocalDate.now().plusDays(1)
            ),
            Arguments.of(
                "ToMOrRoW",
                LocalDate.now().plusDays(1)
            ),
            Arguments.of(
                "   today ",
                LocalDate.now()
            ),
            Arguments.of(
                "ToDAy",
                LocalDate.now()
            ),
            Arguments.of(
                "  yesterday   ",
                LocalDate.now().minusDays(1)
            ),
            Arguments.of(
                "YeStErDay",
                LocalDate.now().minusDays(1)
            ),
            Arguments.of(
                "  1  day   ago ",
                LocalDate.now().minusDays(1)
            ),
            Arguments.of(
                "1 dAy agO",
                LocalDate.now().minusDays(1)
            ),
            Arguments.of(
                "  2234  days   ago  ",
                LocalDate.now().minusDays(2234)
            ),
            Arguments.of(
                "2234 day ago",
                LocalDate.now().minusDays(2234)
            ),
            Arguments.of(
                "2234 DayS agO",
                LocalDate.now().minusDays(2234)
            ),
            Arguments.of(
                "dsds days ago",
                null
            ),
            Arguments.of(
                "32dsds",
                null
            ),
            Arguments.of(
                "32dsds",
                null
            ),
            Arguments.of(
                "dsds-10-10",
                null
            ),
            Arguments.of(
                "d/d/1ds",
                null
            ),
            Arguments.of(
                "",
                null
            ),
            Arguments.of(
                null,
                null
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    public void parseDate_InputStringDate_ResultOptionLocalDate(String date, LocalDate expected) {
        // When
        var actual = Task3.parseDate(date);

        // Then
        assertThat(actual.orElse(null)).isEqualTo(expected);
    }
}
