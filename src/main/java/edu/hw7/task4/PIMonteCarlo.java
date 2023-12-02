package edu.hw7.task4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

public class PIMonteCarlo {

    private static final int CIRCLE_RADIUS = 1;

    @SuppressWarnings("MagicNumber")
    public double calculatePI(int countThreads, long countPoints) throws InterruptedException {
        if (countThreads <= 0) {
            throw new IllegalArgumentException("countThreads must be > 0");
        }
        if (countPoints <= 0) {
            throw new IllegalArgumentException("countPoints must be > 0");
        }

        long pointForOneThread = countPoints / countThreads;
        long lostPoints = countPoints % countThreads;

        List<Future<Long>> futures = new ArrayList<>();

        for (int i = 0; i < countThreads; i++) {
            if (i == 0) {
                futures.add(startThrowingPoints(pointForOneThread + lostPoints));
            } else {
                futures.add(startThrowingPoints(pointForOneThread));
            }
        }

        long pointsInCircle = 0;

        for (var f : futures) {
            try {
                pointsInCircle += f.get();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        return 4.0 * pointsInCircle / countPoints;
    }

    private Future<Long> startThrowingPoints(long countPoints) {
        CompletableFuture<Long> result = new CompletableFuture<>();

        new Thread(() -> {
            long pointsInCircle = 0;

            for (int i = 0; i < countPoints; i++) {
                if (throwPoint()) {
                    pointsInCircle++;
                }
            }

            result.complete(pointsInCircle);
        }).start();

        return result;
    }

    private boolean throwPoint() {
        double x = ThreadLocalRandom.current().nextDouble() * CIRCLE_RADIUS;
        double y = ThreadLocalRandom.current().nextDouble() * CIRCLE_RADIUS;

        return x * x + y * y <= CIRCLE_RADIUS * CIRCLE_RADIUS;
    }
}
