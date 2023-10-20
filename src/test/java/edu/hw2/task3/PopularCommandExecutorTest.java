package edu.hw2.task3;

import edu.hw2.task3.connection.exception.ConnectionException;
import edu.hw2.task3.connection.manager.DefaultConnectionManager;
import edu.hw2.task3.connection.manager.FaultyConnectionManager;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.LinkedList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class PopularCommandExecutorTest {

    @RepeatedTest(50)
    public void updatePackages_InputFaultyConnManagerAnd5MaxAttemptsAndCallMethod100Times_ResultAtLeastOneThrowConnectionException() {
        // Given
        List<ConnectionException> exceptionList = new LinkedList<>();
        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(new FaultyConnectionManager(), 5);
        int countCalls = 100;

        // When
        for (int i = 0; i < countCalls; i++) {
            try {
                popularCommandExecutor.updatePackages();
            } catch (ConnectionException e) {
                exceptionList.add(e);
            } catch (Exception ignored) {}
        }

        // Then
        assertThat(exceptionList.size()).isGreaterThan(0);

        exceptionList.forEach((e) -> {
            assertThat(e).isInstanceOf(ConnectionException.class);
            assertThat(e.getCause()).isInstanceOf(ConnectionException.class);
        });
    }

    @RepeatedTest(50)
    public void updatePackages_InputDefaultConnManagerAnd5MaxAttemptsAndCallMethod100Times_ResultAtLeastOneThrowConnectionException() {
        // Given
        List<ConnectionException> exceptionList = new LinkedList<>();
        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(new DefaultConnectionManager(), 5);
        int countCalls = 100;

        // When
        for (int i = 0; i < countCalls; i++) {
            try {
                popularCommandExecutor.updatePackages();
            } catch (ConnectionException e) {
                exceptionList.add(e);
            } catch (Exception ignored) {}
        }

        // Then
        assertThat(exceptionList.size()).isGreaterThan(0);

        exceptionList.forEach((e) -> {
            assertThat(e).isInstanceOf(ConnectionException.class);
            assertThat(e.getCause()).isInstanceOf(ConnectionException.class);
        });
    }

    @ParameterizedTest
    @CsvSource(value = {
        "-5",
        "0",
        "-10"
    })
    public void updatePackages_InputMaxAttempts_ResultThrowIllegalException(int maxAttempts) {
        assertThatThrownBy(() -> {
            new PopularCommandExecutor(new DefaultConnectionManager(), maxAttempts);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Max attempts must be greater than 0");
    }
}
