package edu.hw2.task3.connection;


import edu.hw2.task3.connection.exception.ConnectionException;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final double CHANCE_CONNECTION_PROBLEMS = 0.3;
    private static final String MESSAGE_FAILED_EXECUTE = "Command '%s' failed to execute";
    private static final String MESSAGE_SUCCESSFUL_EXECUTE = "Command '%s' is executed";

    private final Random random = new Random();

    @Override
    public void execute(String command) throws ConnectionException {
        if (random.nextDouble() <= CHANCE_CONNECTION_PROBLEMS) {
            throw new ConnectionException(String.format(MESSAGE_FAILED_EXECUTE, command));
        }

        LOGGER.info(String.format(MESSAGE_SUCCESSFUL_EXECUTE, command));
    }

    @Override
    public void close() {
        LOGGER.info("Faulty connection closed");
    }
}
