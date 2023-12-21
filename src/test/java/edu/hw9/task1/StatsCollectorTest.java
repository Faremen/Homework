package edu.hw9.task1;

import edu.util.Pair;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class StatsCollectorTest {

    private static Stream<Arguments> putMetric_ProvideParameters() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Pair<>("name1", new double[] {0.5, 10.0})
                ),
                List.of(
                    new Metric("name1", 10.5, 5.25, 10.0, 0.5)
                ),
                3
            ),
            Arguments.of(
                List.of(
                    new Pair<>("name1", new double[] {0.5})
                ),
                List.of(
                    new Metric("name1", 0.5, 0.5, 0.5, 0.5)
                ),
                3
            ),
            Arguments.of(
                List.of(
                    new Pair<>("name1", new double[] {0.5, 10.0}),
                    new Pair<>("name2", new double[] {0, 1.0, 2.0, 3.0, 4.0, 5.0}),
                    new Pair<>("name3", new double[] {0.5})
                ),
                List.of(
                    new Metric("name1", 10.5, 5.25, 10.0, 0.5),
                    new Metric("name2", 15.0, 2.5, 5.0, 0.0),
                    new Metric("name3", 0.5, 0.5, 0.5, 0.5)
                ),
                3
            ),
            Arguments.of(
                List.of(
                    new Pair<>("name1", new double[] {10.0, 0.5}),
                    new Pair<>("name2", new double[] {5.0, 0, 1.0, 2.0, 3.0, 4.0}),
                    new Pair<>("name3", new double[] {0.5})
                ),
                List.of(
                    new Metric("name1", 10.5, 5.25, 10.0, 0.5),
                    new Metric("name2", 15.0, 2.5, 5.0, 0.0),
                    new Metric("name3", 0.5, 0.5, 0.5, 0.5)
                ),
                3
            )
        );
    }

    @ParameterizedTest
    @MethodSource("putMetric_ProvideParameters")
    public void putMetric_putNamesAndValues_ResultMetrics(
        List<Pair<String, double[]>> namesAndValuesList,
        List<Metric> expected,
        int countThreads
    )
        throws Exception {
        // Given
        StatsCollector collector = new StatsCollector(countThreads);

        // Для ожидания завершения выполнения потоков
        CountDownLatch joinLatch = new CountDownLatch(namesAndValuesList.size());

        // Для исключения времени создания и запуска потоков
        CountDownLatch readyLatch = new CountDownLatch(namesAndValuesList.size());
        CountDownLatch startLatch = new CountDownLatch(1);

        // When
        for (var pair : namesAndValuesList) {
            new Thread(() -> {
                try {
                    readyLatch.countDown();
                    startLatch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                collector.push(pair.key(), pair.value());

                joinLatch.countDown();
            }).start();
        }

        // Ожидание запуска потоков, чтобы одновременно запустить выполнение нужного кода
        readyLatch.await();
        startLatch.countDown();

        joinLatch.await();

        collector.close();
        var actual = collector.stats();

        // Then
        assertThat(actual).hasSameSizeAs(expected);
        assertThat(actual).containsAll(expected);
    }
}
