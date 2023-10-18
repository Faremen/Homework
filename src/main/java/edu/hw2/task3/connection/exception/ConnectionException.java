package edu.hw2.task3.connection.exception;

public class ConnectionException extends RuntimeException {

    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable exception)  {
        super(message, exception);
    }
}
