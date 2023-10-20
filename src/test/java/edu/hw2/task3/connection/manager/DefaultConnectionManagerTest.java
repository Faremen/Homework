package edu.hw2.task3.connection.manager;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connection.FaultyConnection;
import edu.hw2.task3.connection.StableConnection;
import org.junit.jupiter.api.RepeatedTest;
import java.util.LinkedList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DefaultConnectionManagerTest {

    @RepeatedTest(50)
    public void getConnection_CallMethod150Times_ResultAtLeastOneFaultyConnection() {
        // Given
        List<FaultyConnection> connectionList = new LinkedList<>();
        DefaultConnectionManager manager = new DefaultConnectionManager();
        int countCalls = 150;

        // When
        for (int i = 0; i < countCalls; i++) {
            Connection connection = manager.getConnection();
            if (connection instanceof FaultyConnection faultyConnection) {
                connectionList.add(faultyConnection);
            }
        }

        // Then
        assertThat(connectionList.size()).isGreaterThan(0);

        connectionList.forEach((e) -> {
            assertThat(e).isInstanceOf(FaultyConnection.class);
        });
    }

    @RepeatedTest(50)
    public void getConnection_CallMethod150Times_ResultAtLeastOneStableConnection() {
        // Given
        List<StableConnection> connectionList = new LinkedList<>();
        DefaultConnectionManager manager = new DefaultConnectionManager();
        int countCalls = 150;

        // When
        for (int i = 0; i < countCalls; i++) {
            Connection connection = manager.getConnection();
            if (connection instanceof StableConnection stableConnection) {
                connectionList.add(stableConnection);
            }
        }

        // Then
        assertThat(connectionList.size()).isGreaterThan(0);

        connectionList.forEach((e) -> {
            assertThat(e).isInstanceOf(StableConnection.class);
        });
    }
}
