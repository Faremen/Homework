package edu.hw7.task4;

import java.text.DecimalFormat;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PIMonteCarloTest {

    @ParameterizedTest
    @CsvSource(value = {
        "1, 10_000_000, 0.0021",
        "2, 10_000_000, 0.0021",
        "4, 10_000_000, 0.0021",
        "8, 10_000_000, 0.0021",
        "1, 100_000_000, 0.0006",
        "2, 100_000_000, 0.0006",
        "4, 100_000_000, 0.0006",
        "8, 100_000_000, 0.0006",
        "1, 1_000_000_000, 0.00015",
        "2, 1_000_000_000, 0.00015",
        "4, 1_000_000_000, 0.00015",
        "8, 1_000_000_000, 0.00015",
        "32, 1_000_000_000, 0.00015"
    })
    public void calculatePI_InputCountThreadsAndCountPoints_ResultPI(
        int countThreads,
        long countPoints,
        double delta
    ) throws InterruptedException {
        // Given
        PIMonteCarlo piMonteCarlo = new PIMonteCarlo();
        double expected = Math.PI;

        // When
        long start = System.currentTimeMillis();
        var actual = piMonteCarlo.calculatePI(countThreads, countPoints);
        long end = System.currentTimeMillis();

        System.out.printf("Получившееся PI: %f%n", actual);
        System.out.printf("Ожидаемое PI: %f%n", expected);

        System.out.printf(
            "Время расчета при %d потоков и %s точек: %.3f сек",
            countThreads,
            new DecimalFormat("###,###").format(countPoints),
            (end - start) / 1e3
        );

        // Then
        assertEquals(actual, expected, delta);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "-1, 10, countThreads must be > 0",
        "0, 10, countThreads must be > 0",
        "10, -1, countPoints must be > 0",
        "10, 0, countPoints must be > 0",
        "-1, -1, countThreads must be > 0",
        "0, 0, countThreads must be > 0"
    })
    public void calculatePI_InputCountThreadsAndCountPoints_ResultPI(
        int countThreads,
        long countPoints,
        String expectedMessage
    ) {
        // Given
        PIMonteCarlo piMonteCarlo = new PIMonteCarlo();

        assertThatThrownBy(() -> piMonteCarlo.calculatePI(countThreads, countPoints))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(expectedMessage);
    }
}
