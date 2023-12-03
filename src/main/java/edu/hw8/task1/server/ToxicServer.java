package edu.hw8.task1.server;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ToxicServer implements Closeable {
    private final ServerSocket serverSocket;
    private final ExecutorService executorService;
    private final Thread worker;

    public ToxicServer(int maxProcessedConnections, int port) throws IOException {
        executorService = Executors.newFixedThreadPool(maxProcessedConnections);
        serverSocket = new ServerSocket(port);
        worker = createWorker();
    }

    public void start() {
        worker.start();
    }

    private Thread createWorker() {
        return new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Socket socket = serverSocket.accept();
                    executorService.execute(new RequestHandler(socket));
                }
            } catch (IOException ignored) {
            }
        }, "ToxicServerWorker");
    }

    public boolean isStarted() {
        return worker.isAlive();
    }

    public boolean isClosed() {
        return worker.isInterrupted()
            && executorService.isTerminated()
            && serverSocket.isClosed();
    }

    @Override
    public void close() throws IOException {
        serverSocket.close();
        executorService.close();
        worker.interrupt();
        try {
            worker.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
