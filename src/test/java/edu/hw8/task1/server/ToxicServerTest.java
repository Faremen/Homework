package edu.hw8.task1.server;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;

public class ToxicServerTest {

    private static final int MAX_PROCESSED_CONNECTIONS = 1;
    private static final int PORT = 8080;

    private ToxicServer server;

    @BeforeEach
    public void setUp() throws IOException {
        server = new ToxicServer(MAX_PROCESSED_CONNECTIONS, PORT);
        server.start();
    }

    @AfterEach
    public void tearDown() throws IOException {
        if (!server.isClosed()) {
            server.close();
        }
    }

    @Test
    public void start_StartServer_ResultServerNotClosedAndStarted() {
        // Given
        boolean expectedClosed = false;
        boolean expectedStarted = true;

        // When
        var actualClosed = server.isClosed();
        var actualStarted = server.isStarted();

        // Then
        assertThat(actualClosed)
            .withFailMessage("\nisClosed\nexpected: %b\nbut was: %b", expectedClosed, actualClosed)
            .isEqualTo(expectedClosed);
        assertThat(actualStarted)
            .withFailMessage("\nisStarted\nexpected: %b\nbut was: %b", expectedStarted, actualStarted)
            .isEqualTo(expectedStarted);
    }

    @Test
    public void close_CloseServer_ResultServerClosedAndNotStarted() throws IOException {
        // Given
        boolean expectedClosed = true;
        boolean expectedStarted = false;

        // When
        server.close();
        var actualClosed = server.isClosed();
        var actualStarted = server.isStarted();

        // Then
        assertThat(actualClosed)
            .withFailMessage("\nisClosed\nexpected: %b\nbut was: %b", expectedClosed, actualClosed)
            .isEqualTo(expectedClosed);
        assertThat(actualStarted)
            .withFailMessage("\nisStarted\nExpected: %b\nbut was: %b", expectedStarted, actualStarted)
            .isEqualTo(expectedStarted);
    }
}
