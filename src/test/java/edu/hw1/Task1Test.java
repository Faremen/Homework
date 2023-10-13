package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @Test
    public void minutesToSecondsValidInput() {
        // Given
        String inputStr = "20:30";
        int expectedSeconds = 1230;

        // When
        int actualMinutes = Task1.minutesToSeconds(inputStr);

        // Then
        assertThat(actualMinutes).isEqualTo(expectedSeconds);
    }

    @Test
    public void minutesToSecondsInvalidSeparator() {
        // Given
        String inputStr = "32>43";
        int expectedSeconds = -1;

        // When
        int actualMinutes = Task1.minutesToSeconds(inputStr);

        // Then
        assertThat(actualMinutes).isEqualTo(expectedSeconds);
    }

    @Test
    public void minutesToSecondsMoreSeparatorsThanOne() {
        // Given
        String inputStr = "32:43:32";
        int expectedSeconds = -1;

        // When
        int actualMinutes = Task1.minutesToSeconds(inputStr);

        // Then
        assertThat(actualMinutes).isEqualTo(expectedSeconds);
    }

    @Test
    public void minutesToSecondsNegativeSecondsValue() {
        // Given
        String inputStr = "10:-45";
        int expectedSeconds = -1;

        // When
        int actualMinutes = Task1.minutesToSeconds(inputStr);

        // Then
        assertThat(actualMinutes).isEqualTo(expectedSeconds);
    }

    @Test
    public void minutesToSecondsSecondsValueGreaterThanSixty() {
        // Given
        String inputStr = "10:9999";
        int expectedSeconds = -1;

        // When
        int actualMinutes = Task1.minutesToSeconds(inputStr);

        // Then
        assertThat(actualMinutes).isEqualTo(expectedSeconds);
    }

    @Test
    public void minutesToSecondsSecondsValueEqualsSixty() {
        // Given
        String inputStr = "10:60";
        int expectedSeconds = -1;

        // When
        int actualMinutes = Task1.minutesToSeconds(inputStr);

        // Then
        assertThat(actualMinutes).isEqualTo(expectedSeconds);
    }

    @Test
    public void minutesToSecondsNoSeparator() {
        // Given
        String inputStr = "9999";
        int expectedSeconds = -1;

        // When
        int actualMinutes = Task1.minutesToSeconds(inputStr);

        // Then
        assertThat(actualMinutes).isEqualTo(expectedSeconds);
    }

    @Test
    public void minutesToSecondsNoLimitMinutes() {
        // Given
        String inputStr = "99999:00";
        int expectedSeconds = 99999 * 60;

        // When
        int actualMinutes = Task1.minutesToSeconds(inputStr);

        // Then
        assertThat(actualMinutes).isEqualTo(expectedSeconds);
    }

    @Test
    public void minutesToSecondsMinutesAndSecondsIsNotNumber() {
        // Given
        String inputStr = "mmm:ss";
        int expectedSeconds = -1;

        // When
        int actualMinutes = Task1.minutesToSeconds(inputStr);

        // Then
        assertThat(actualMinutes).isEqualTo(expectedSeconds);
    }

    @Test
    public void minutesToSecondsHaveMinutesButNoSeconds() {
        // Given
        String inputStr = "30:";
        int expectedSeconds = -1;

        // When
        int actualMinutes = Task1.minutesToSeconds(inputStr);

        // Then
        assertThat(actualMinutes).isEqualTo(expectedSeconds);
    }

    @Test
    public void minutesToSecondsNoMinutesButHaveSeconds() {
        // Given
        String inputStr = ":30";
        int expectedSeconds = -1;

        // When
        int actualMinutes = Task1.minutesToSeconds(inputStr);

        // Then
        assertThat(actualMinutes).isEqualTo(expectedSeconds);
    }
}
