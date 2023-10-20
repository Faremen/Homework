package edu.hw2.task3.connection.manager;

import edu.hw2.task3.connection.FaultyConnection;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FaultyConnectionManagerTest {

    @Test
    void getConnection_CallMethod100Times_ResultFaultyConnection() {
        FaultyConnectionManager manager = new FaultyConnectionManager();
        int countCalls = 100;

        for (int i = 0; i < countCalls; i++) {
            assertThat(manager.getConnection()).isInstanceOf(FaultyConnection.class);
        }
    }
}
