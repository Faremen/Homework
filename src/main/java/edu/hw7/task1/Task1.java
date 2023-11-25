package edu.hw7.task1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {

    private final AtomicInteger value = new AtomicInteger(0);

    public int increaseThreads(int countIncrements, int countThreads) throws InterruptedException {
        if (countIncrements <= 0) {
            throw new IllegalArgumentException("countIncrements must be > 0");
        }
        if (countThreads <= 0) {
            throw new IllegalArgumentException("countThreads must be > 0");
        }

        CountDownLatch joinLatch = new CountDownLatch(countThreads);

        for (int i = 0; i < countThreads; i++) {
            new Thread(() -> {
                for (int j = 0; j < countIncrements; j++) {
                    value.incrementAndGet();
                }
                joinLatch.countDown();
            }).start();
        }

        joinLatch.await();

        return value.get();
    }

}
