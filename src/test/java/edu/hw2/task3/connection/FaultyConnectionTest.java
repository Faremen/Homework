package edu.hw2.task3.connection;

import edu.hw2.task3.connection.exception.ConnectionException;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class FaultyConnectionTest {

    @RepeatedTest(50)
    public void execute_CallMethod100Times_ResultAtLeastOneThrowConnectionException() {
        // Given
        List<ConnectionException> exceptionList = new LinkedList<>();
        FaultyConnection connection = new FaultyConnection();
        int countCalls = 100;

        // When
        for (int i = 0; i < countCalls; i++) {
            try {
                connection.execute("Test " + i + " command");
            } catch (ConnectionException e) {
                exceptionList.add(e);
            }
        }

        // Then
        assertThat(exceptionList.size()).isGreaterThan(0);

        exceptionList.forEach((e) -> {
            assertThat(e).isInstanceOf(ConnectionException.class);
        });
    }

    @Test
    public void close_CallMethod50Times_ResultNoThrowException() {
        FaultyConnection connection = new FaultyConnection();

        for (int i = 0; i < 50; i++) {
            assertDoesNotThrow(() -> {
                connection.close();
            });
        }
    }
}
