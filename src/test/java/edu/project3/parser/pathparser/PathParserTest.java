package edu.project3.parser.pathparser;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static edu.project3.parser.pathparser.PathParser.getPaths;
import static org.assertj.core.api.Assertions.assertThat;

public class PathParserTest {

    private static final Path FIRST_LOG = Paths.get("src/test/java/edu/project3/Test logs/log1.txt");
    private static final Path SECOND_LOG = Paths.get("src/test/java/edu/project3/Test logs/log2.txt");
    private static final Path THIRD_LOG = Paths.get("src/test/java/edu/project3/Test logs/dir/log3.txt");

    private static Stream<Arguments> getPaths_ProvideParameters() {
        return Stream.of(
            Arguments.of(
                "src/test/java/edu/project3/Test logs/**",
                List.of(
                    FIRST_LOG,
                    SECOND_LOG,
                    THIRD_LOG
                )
            ),
            Arguments.of(
                "src/test/java/edu/project3/Test logs/*",
                List.of(
                    FIRST_LOG,
                    SECOND_LOG
                )
            ),
            Arguments.of(
                "src/test/java/edu/project3/Test logs/log1.txt",
                List.of(
                    FIRST_LOG
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("getPaths_ProvideParameters")
    public void getPaths_InputPath_ResultListOfPaths(String path, List<Path> expectedPaths) {
        // When
        List<Path> actual = getPaths(path);

        // Then
        assertThat(actual).containsAll(expectedPaths);
    }
}
