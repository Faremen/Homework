package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @Test
    public void testValidMinutesAndSeconds() {
        // Given
        String inputStr = "20:30";

        // When
        int minutes = Task1.minutesToSeconds(inputStr);

        // Then
        assertThat(minutes).isEqualTo(1230);
    }

    @Test
    public void testInvalidSeparator() {
        // Given
        String inputStr = "32>43";

        // When
        int minutes = Task1.minutesToSeconds(inputStr);

        // Then
        assertThat(minutes).isEqualTo(-1);
    }

    @Test
    public void testMoreSeparatorsThanOne() {
        // Given
        String inputStr = "32:43:32";

        // When
        int minutes = Task1.minutesToSeconds(inputStr);

        // Then
        assertThat(minutes).isEqualTo(-1);
    }

    @Test
    public void testNegativeSecondsValue() {
        // Given
        String inputStr = "10:-45";

        // When
        int minutes = Task1.minutesToSeconds(inputStr);

        // Then
        assertThat(minutes).isEqualTo(-1);
    }

    @Test
    public void testSecondsValueGreaterThanSixty() {
        // Given
        String inputStr = "10:9999";

        // When
        int minutes = Task1.minutesToSeconds(inputStr);

        // Then
        assertThat(minutes).isEqualTo(-1);
    }

    @Test
    public void testNoSeparator() {
        // Given
        String inputStr = "9999";

        // When
        int minutes = Task1.minutesToSeconds(inputStr);

        // Then
        assertThat(minutes).isEqualTo(-1);
    }

    @Test
    public void testNoLimitMinutes() {
        // Given
        String inputStr = "99999:00";

        // When
        int minutes = Task1.minutesToSeconds(inputStr);

        // Then
        assertThat(minutes).isEqualTo(99999 * 60);
    }

    @Test
    public void testMinutesAndSecondsIsNotNumber() {
        // Given
        String inputStr = "mmm:ss";

        // When
        int minutes = Task1.minutesToSeconds(inputStr);

        // Then
        assertThat(minutes).isEqualTo(-1);
    }
}
