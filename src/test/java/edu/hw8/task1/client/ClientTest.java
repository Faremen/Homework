package edu.hw8.task1.client;

import edu.hw8.task1.server.ToxicServer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class ClientTest {
    private static final int MAX_PROCESSED_CONNECTIONS = 2;
    private static final String HOST = "localhost";
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

    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
        "''| Я знаю, что ничего не знаю.",
        "встать| Легко вставать, когда ты не ложился.",
        "молчание| Если волк молчит то лучше его не перебивать.",
        "dsdsd| Я знаю, что ничего не знаю."
    })
    public void sendMessage_OneClient_ResultExpectedMessage(String message, String expected) throws IOException {
        // When
        Client client = new Client(HOST, PORT);
        var actual = client.sendMessage(message);

        client.close();

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> sendMessage_MultipleThreads_ProvideParameters() {
        return Stream.of(
            Arguments.of(
                List.of(
                    "sdsd",
                    "оскорбления",
                    "личности",
                    "ГлУпЫЙ"
                ),
                List.of(
                    "Я знаю, что ничего не знаю.",
                    "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами.",
                    "Не переходи на личности там, где их нет.",
                    "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма."
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("sendMessage_MultipleThreads_ProvideParameters")
    public void sendMessage_MultipleThreadsClient_ResultExpectedMessage(List<String> messages, List<String> expected)
        throws InterruptedException {
        // Given
        // Для ожидания завершения выполнения потоков
        CountDownLatch joinLatch = new CountDownLatch(messages.size());

        // Для исключения времени создания и запуска потоков
        CountDownLatch readyLatch = new CountDownLatch(messages.size());
        CountDownLatch startLatch = new CountDownLatch(1);

        // When

        List<String> actual = new ArrayList<>(messages.size());

        for (int i = 0; i < messages.size(); i++) {
            actual.add(null);
            int finalI = i;

            new Thread(() -> {
                try {
                    readyLatch.countDown();
                    startLatch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                try {
                    Client client = new Client(HOST, PORT);

                    actual.set(finalI, client.sendMessage(messages.get(finalI)));

                    client.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                joinLatch.countDown();
            }).start();
        }

        // Ожидание запуска потоков, чтобы одновременно запустить выполнение нужного кода
        readyLatch.await();
        startLatch.countDown();

        joinLatch.await();

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void close_CreateClientAndClose_ResultClosedClient() throws IOException {
        // Given
        boolean expectedClosedBefore = false;
        boolean expectedClosedAfter = true;

        // When
        Client client = new Client(HOST, PORT);
        var actualClosedBefore = client.isClosed();

        client.close();
        var actualClosedAfter = client.isClosed();

        // Then
        assertThat(actualClosedBefore)
            .withFailMessage("\nisClosed before closing\nexpected: %b\nbut was: %b", expectedClosedBefore, actualClosedBefore)
            .isEqualTo(expectedClosedBefore);
        assertThat(actualClosedAfter)
            .withFailMessage("\nisClosed after closing\nexpected: %b\nbut was: %b", expectedClosedAfter, actualClosedAfter)
            .isEqualTo(expectedClosedAfter);
    }
}
