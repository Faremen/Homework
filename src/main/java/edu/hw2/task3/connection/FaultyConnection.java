package edu.hw2.task3.connection;


import edu.hw2.task3.connection.exception.ConnectionException;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final double CHANCE_CONNECTION_PROBLEMS = 0.3;
    private final Random random = new Random();

    @Override
    public void execute(String command) throws ConnectionException {
        if (random.nextDouble() <= CHANCE_CONNECTION_PROBLEMS) {
            throw new ConnectionException("Command '" + command + "' failed to execute");
        }

        LOGGER.info("Command '" + command + "' is executed");
    }

    @Override
    public void close() {
        LOGGER.info("Faulty connection closed");
    }
}
