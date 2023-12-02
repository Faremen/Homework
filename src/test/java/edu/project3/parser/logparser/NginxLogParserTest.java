package edu.project3.parser.logparser;

import edu.project3.TestUtils;
import edu.project3.model.Log;
import edu.project3.parser.pathparser.PathParser;
import edu.project3.receiver.Receiver;
import edu.project3.receiver.http.HttpLogReceiver;
import edu.project3.receiver.path.PathLogReceiver;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class NginxLogParserTest {
    @Test
    public void parseLogs_InputLogsFile_ResultListOfAllParsedLogs() {
        // Given
        Receiver receiver = new PathLogReceiver(PathParser.getPaths("src/test/java/edu/project3/Test logs/log1.txt"));
        LogParser logParser = new NginxLogParser();
        List<Log> expected = TestUtils.LOGS_FOR_TESTS;

        // When
        List<Log> actual = logParser.parseLogs(receiver.receive());

        // Then
        assertThat(actual).containsAll(expected);
    }

    @Test
    public void parseLogs_InputIncorrectLogs_ResultIllegalArgumentException() {
        Receiver receiver = new HttpLogReceiver("https://hacker-news.firebaseio.com/v0/topstories.json");
        LogParser logParser = new NginxLogParser();

        assertThatThrownBy(() -> logParser.parseLogs(receiver.receive()))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
