package edu.project3.parser.argsparser;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ArgsParserTest {
    private static Stream<Arguments> getParseFormat_ProvideParameters() {
        return Stream.of(
            Arguments.of(
                "--path src/test/java/edu/project3/Test logs/* --from 2023-09-27 --to 2023-10-07 --format markdown",
                new ParseFormat(
                    "src/test/java/edu/project3/Test logs/*",
                    "2023-09-27",
                    "2023-10-07",
                    "markdown"
                )
            ),
            Arguments.of(
                "--path src/test/java/edu/project3/Test logs/* --to 2023-10-07 --format markdown",
                new ParseFormat(
                    "src/test/java/edu/project3/Test logs/*",
                    "",
                    "2023-10-07",
                    "markdown"
                )
            ),
            Arguments.of(
                "--path src/test/java/edu/project3/Test logs/* --from 2023-09-27 --format markdown",
                new ParseFormat(
                    "src/test/java/edu/project3/Test logs/*",
                    "2023-09-27",
                    "",
                    "markdown"
                )
            ),
            Arguments.of(
                "--path src/test/java/edu/project3/Test logs/* --format markdown",
                new ParseFormat(
                    "src/test/java/edu/project3/Test logs/*",
                    "",
                    "",
                    "markdown"
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("getParseFormat_ProvideParameters")
    public void getParseFormat_InputCommand_ResultParseFormat(String command, ParseFormat expectedFormat) {
        // When
        ParseFormat actual = ArgsParser.getParseFormat(command);

        // Then
        assertThat(actual).isEqualTo(expectedFormat);
    }

    @Test
    public void getParseFormat_InputCommandWithoutPass_ResultIllegalArgumentException() {
        String args = "--from 2023-09-27 --format markdown";

        assertThatThrownBy(() -> ArgsParser.getParseFormat(args))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Path shouldn't be empty");
    }
}
