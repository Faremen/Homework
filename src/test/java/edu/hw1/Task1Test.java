package edu.hw1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @ParameterizedTest
    @CsvSource(value = {
        "20:30,     1230",
        "1:00,      60",
        "00:00,     0",
        "99999:00,  5999940",
        "32>43,     -1",
        "32:43:32,  -1",
        "10:-45,    -1",
        "10:9999,   -1",
        "10:60,     -1",
        "9999,      -1",
        "mmm:ss,    -1",
        "30:,       -1",
        ":30,       -1",
        "'',        -1",
        " ,         -1"
    })
    public void minutesToSeconds_InputVideoLength_ExpectedSeconds(String videoLength, int expectedSeconds) {
        // When
        int actualSeconds = Task1.minutesToSeconds(videoLength);

        // Then
        assertThat(actualSeconds).isEqualTo(expectedSeconds);
    }
}
