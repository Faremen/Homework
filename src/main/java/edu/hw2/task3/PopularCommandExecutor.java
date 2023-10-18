package edu.hw2.task3;

import edu.hw2.task3.connection.Connection;
import edu.hw2.task3.connection.exception.ConnectionException;
import edu.hw2.task3.connection.manager.ConnectionManager;
import java.util.Objects;

public final class PopularCommandExecutor {
    private final ConnectionManager connectionManager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager connectionManager, int maxAttempts) {
        Objects.requireNonNull(connectionManager);

        if (maxAttempts <= 0) {
            throw new IllegalArgumentException("Max attempts must be greater than 0");
        }

        this.connectionManager = connectionManager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() throws Exception {
        tryExecute("apt update && apt upgrade -y");
    }

    private void tryExecute(String command) throws Exception {
        for (int i = 0; i < maxAttempts; i++) {
            try (Connection connection = connectionManager.getConnection()) {
                connection.execute(command);
                return;
            } catch (ConnectionException e) {
                throw new ConnectionException(maxAttempts + " attempts were not enough "
                    + "to execute the command '" + command + "'", e);
            }
        }
    }
}
