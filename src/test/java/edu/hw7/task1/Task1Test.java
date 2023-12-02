package edu.hw7.task1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task1Test {
    @ParameterizedTest
    @CsvSource(value = {
        "10, 10, 100",
        "1_000_000, 4, 4_000_000",
        "1, 1, 1"
    })
    public void increaseThreads_InputCountIncrementsAndCountThreads_ResultExpectedValue(
        int countIncrements,
        int countThreads,
        int expected
    ) throws InterruptedException {
        // Given
        Task1 task1 = new Task1();

        // When
        var actual = task1.increaseThreads(countIncrements, countThreads);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "-1, 10, countIncrements must be > 0",
        "0, 10, countIncrements must be > 0",
        "10, -1, countThreads must be > 0",
        "10, 0, countThreads must be > 0",
        "-1, -1, countIncrements must be > 0",
        "0, 0, countIncrements must be > 0"
    })
    public void increaseThreads_InputIncorrectCountIncrementsAndIncorrectCountThreads_ResultIllegalArgumentException(
        int countIncrements,
        int countThreads,
        String expectedMessage
    ) throws InterruptedException {
        // Given
        Task1 task1 = new Task1();

        assertThatThrownBy(() -> task1.increaseThreads(countIncrements, countThreads))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(expectedMessage);
    }
}
