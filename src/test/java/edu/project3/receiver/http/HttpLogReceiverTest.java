package edu.project3.receiver.http;

import edu.project3.receiver.Receiver;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
public class HttpLogReceiverTest {
    private static final String TEST_URL =
        "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";

    @Test
    public void receive_InputURL_ResultListOfStringsFromHttp() {
        // Given
        Receiver receiver = new HttpLogReceiver(TEST_URL);
        int expected = 51462;

        // When
        int actual = receiver.receive().size();

        // Then
        assertThat(actual).isEqualTo(expected);
    }
}
