package edu.hw2.task3.connection;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class StableConnectionTest {

    @Test
    public void execute_CallMethod50Times_ResultNoThrowException() {
        int countCalls = 50;

        try (StableConnection connection = new StableConnection()) {
            for (int i = 0; i < countCalls; i++) {
                assertDoesNotThrow(() -> {
                    connection.execute("Test Command");
                });
            }
        }
    }

    @Test
    public void close_CallMethod50Times_ResultNoThrowException() {
        StableConnection connection = new StableConnection();
        int countCalls = 50;

        for (int i = 0; i < countCalls; i++) {
            assertDoesNotThrow(() -> {
                connection.close();
            });
        }
    }
}
