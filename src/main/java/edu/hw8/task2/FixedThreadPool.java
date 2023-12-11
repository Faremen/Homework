package edu.hw8.task2;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;

public class FixedThreadPool implements ThreadPool {
    private final Thread[] threads;
    private final BlockingQueue<Runnable> tasks;
    private int numThread = 0;

    private FixedThreadPool(int countThreads) {
        threads = new Thread[countThreads];
        tasks = new LinkedBlockingQueue<>();
    }

    public static FixedThreadPool create(int countThreads) {
        return new FixedThreadPool(countThreads);
    }

    @Override
    public void start() {
        numThread = 0;

        for (int i = 0; i < threads.length; i++) {
            if (threads[i] == null || threads[i].isInterrupted()) {
                threads[i] = createThread();
                threads[i].start();
            }
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (Arrays.stream(threads).anyMatch(Thread::isInterrupted)) {
            throw new RejectedExecutionException(this + " is close");
        }

        try {
            tasks.put(runnable);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void close() throws Exception {
        for (var t : threads) {
            t.interrupt();
        }

        for (var t : threads) {
            t.join();
        }
    }

    private Thread createThread() {
        return new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                     tasks.take().run();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "ThreadPool-Thread " + numThread++);
    }
}
