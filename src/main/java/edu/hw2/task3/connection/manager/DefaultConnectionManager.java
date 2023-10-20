package edu.hw2.task3.connection.manager;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connection.FaultyConnection;
import edu.hw2.task3.connection.StableConnection;
import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private static final double CHANCE_RETURN_FAULTY_CONNECTION = 0.4;
    private final Random random = new Random();

    @Override
    public Connection getConnection() {
        if (random.nextDouble() <= CHANCE_RETURN_FAULTY_CONNECTION) {
            return new FaultyConnection();
        }

        return new StableConnection();
    }
}
