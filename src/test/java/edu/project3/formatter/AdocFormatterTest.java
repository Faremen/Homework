package edu.project3.formatter;

import edu.project3.model.metrics.Metric;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AdocFormatterTest {

    @Test
    public void format_InputMetric_ResultFormat() {
        // Given
        Formatter formatter = new AdocFormatter();
        Metric metric = new Metric("Test", List.of("Test column|Number", "first|1", "second|2"));
        String expected = """
            ==== Test

            |==================
            |Test column|Number

            |      first|     1
            |     second|     2
            |==================
            """;

        // When
        String actual = formatter.format(metric);

        // Then
        assertThat(actual).isEqualTo(expected);
    }
}
