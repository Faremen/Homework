package edu.project3.receiver.path;

import edu.project3.parser.pathparser.PathParser;
import edu.project3.receiver.Receiver;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PathLogReceiverTest {
    @Test
    public void receive_InputLogFilePath_ResultListOfStringsFromPathsToFiles() {
        // Given
        List<Path> paths = PathParser.getPaths("src/test/java/edu/project3/Test logs/**");
        Receiver receiver = new PathLogReceiver(paths);
        int expected = 171;

        // When
        int actual = receiver.receive().size();

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void receive_InputIncorrectPath_ResultRuntimeException() {
        Path wrongPath = Paths.get("dsdsfdss");
        Receiver receiver = new PathLogReceiver(List.of(wrongPath));

        assertThatThrownBy(receiver::receive)
            .isInstanceOf(RuntimeException.class);
    }
}
