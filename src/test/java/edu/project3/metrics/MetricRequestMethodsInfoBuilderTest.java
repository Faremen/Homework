package edu.project3.metrics;

import edu.project3.TestUtils;
import edu.project3.model.Log;
import edu.project3.model.metrics.Metric;
import edu.project3.model.metrics.MetricBuilder;
import edu.project3.model.metrics.MetricRequestMethodsInfoBuilder;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MetricRequestMethodsInfoBuilderTest {
    @Test
    public void build_InputLogs_ResultMetricForResourcesInfo() {
        // Given
        MetricBuilder metricBuilder = new MetricRequestMethodsInfoBuilder();
        List<Log> logs = TestUtils.LOGS_FOR_TESTS;
        Metric expected = new Metric(
            "HTTP запросы", List.of
            (
                "HTTP запрос|Количество",
                "GET|6",
                "PUT|1"
            )
        );

        // When
        Metric actual = metricBuilder.build(logs);

        // Then
        assertThat(actual).isEqualTo(expected);
    }
}
