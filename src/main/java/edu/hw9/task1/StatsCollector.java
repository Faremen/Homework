package edu.hw9.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StatsCollector implements AutoCloseable {
    private final ExecutorService executorService;
    private final Queue<Metric> metrics = new ConcurrentLinkedQueue<>();

    public StatsCollector(int countThreads) {
        executorService = Executors.newFixedThreadPool(countThreads);
    }

    public void push(String metricName, double[] values) {
        executorService.execute(
            putMetric(metricName, values)
        );
    }

    private Runnable putMetric(String name, double[] values) {
        return () -> {
            double sum = 0.0;
            double max = values[0];
            double min = values[0];

            for (double value : values) {
                if (value > max) {
                    max = value;
                }

                if (value < min) {
                    min = value;
                }

                sum += value;
            }

            metrics.add(new Metric(
                name, sum, sum / values.length, max, min
            ));
        };
    }

    public List<Metric> stats() {
        return new ArrayList<>(metrics);
    }

    @Override
    public void close() throws Exception {
        executorService.close();
    }
}
