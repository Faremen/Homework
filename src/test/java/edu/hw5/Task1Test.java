package edu.hw5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.format.DateTimeParseException;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task1Test {

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
            Arguments.of(new String[] {
                "   2022-03-12,   20:20   - 2022-03-12, 23:50",
                "2022-04-01, 21:30 - 2022-04-02, 01:20"
            }, "3ч 40м"),
            Arguments.of(new String[] {
                "2022-03-12, 00:00 - 2022-03-14, 00:00"
            }, "48ч 00м"),
            Arguments.of(new String[] {
                "2021-03-12, 00:00 - 2022-03-12, 00:00"
            }, "8760ч 00м"),
            Arguments.of(new String[] {
                "2022-03-12, 00:00 - 2022-03-12, 00:01",
                "2022-03-12, 00:00 - 2022-03-12, 00:02",
                "2022-03-12, 00:00 - 2022-03-12, 00:03",
                "2022-03-12, 00:00 - 2022-03-12, 00:04",
                "2022-03-12, 00:00 - 2022-03-12, 00:05",
                "2022-03-12, 00:00 - 2022-03-12, 00:06"
            }, "0ч 03м")
        );
    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    public void calcAverageTime_InputStringsWithDates_ResultAverageTime(String[] strings, String expected) {
        // When
        var actual = Task1.calcAverageTime(strings);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void calcAverageTime_InputNullStrings_ResultNullPointerException() {
        assertThatThrownBy(() -> Task1.calcAverageTime(null))
            .isInstanceOf(NullPointerException.class);
    }

    private static Stream<Arguments> incorrectDates_ProvideParameters() {
        return Stream.of(
            Arguments.of(
                (Object) new String[] {
                    "ГГГГ-03-12, 20:20 - 2022-03-12, 23:50"
                }
            ),
            Arguments.of(
                (Object) new String[] {
                    "2022-33-12, 20:20 - 2022-03-12, 23:50"
                }
            ),
            Arguments.of(
                (Object) new String[] {
                    "2022-03-400, 20:20 - 2022-03-12, 23:50"
                }
            ),
            Arguments.of(
                (Object) new String[] {
                    "2022-03-12, 30:20 - 2022-03-12, 23:50"
                }
            ),
            Arguments.of(
                (Object) new String[] {
                    "2022-03-12, 20:60 - 2022-03-12, 23:50"
                }
            )
        );
    }

    @ParameterizedTest
    @MethodSource("incorrectDates_ProvideParameters")
    public void calcAverageTime_InputStringsWithIncorrectDates_ResultDateTimeParseException(String[] strings) {
        assertThatThrownBy(() -> Task1.calcAverageTime(strings))
            .isInstanceOf(DateTimeParseException.class);
    }

    @Test
    public void calcAverageTime_InputStringsWhenFirstDateAfterSecondDate_ResultIllegalArgumentException() {
        String[] strings = new String[] {
            "2022-04-02, 01:20 - 2022-04-01, 21:30"
        };

        assertThatThrownBy(() -> Task1.calcAverageTime(strings))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("first date after second date: 2022-04-02, 01:20 - 2022-04-01, 21:30");
    }
}
