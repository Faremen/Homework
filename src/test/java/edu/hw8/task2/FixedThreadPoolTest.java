package edu.hw8.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class FixedThreadPoolTest {
    private static final int COUNT_THREADS_IN_POOL = 3;
    public ThreadPool threadPool;

    @BeforeEach
    public void setUp() {
        threadPool = FixedThreadPool.create(COUNT_THREADS_IN_POOL);
        threadPool.start();
    }

    @AfterEach
    public void tearDown() throws Exception {
        threadPool.close();
    }

    private static Stream<Arguments> execute_CalcFibonacciInMultiThread_ProvideParameters() {
        return Stream.of(
            Arguments.of(
                3,
                List.of(0L, 1L, 1L, 2L)
            ),
            Arguments.of(
                10,
                List.of(0L, 1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L, 55L)
            ),
            Arguments.of(
                20,
                List.of(0L, 1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L, 55L, 89L, 144L, 233L, 377L, 610L, 987L, 1597L, 2584L, 4181L, 6765L)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("execute_CalcFibonacciInMultiThread_ProvideParameters")
    public void execute_CalcFibonacciInMultiThreads_ResultFibonacciSequence(int n, List<Long> expected)
        throws InterruptedException {
        // Given
        // Для ожидания завершения выполнения потоков
        CountDownLatch joinLatch = new CountDownLatch(n + 1);

        // When
        List<Long> actual = new ArrayList<>(n + 1);

        for (int i = 0; i <= n; i++) {
            actual.add(null);
            int finalI = i;

            threadPool.execute(() -> {
                actual.set(finalI, calcFibonacci(finalI));

                joinLatch.countDown();
            });
        }

        joinLatch.await();

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    private long calcFibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }

        long[] arr = new long[n + 1];

        arr[0] = 0;
        arr[1] = 1;

        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }

        return arr[n];
    }
}
