package edu.project3.metrics;

import edu.project3.TestUtils;
import edu.project3.model.Log;
import edu.project3.model.metrics.Metric;
import edu.project3.model.metrics.MetricBuilder;
import edu.project3.model.metrics.MetricResponseCodesInfoBuilder;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MetricResponseCodesInfoBuilderTest {
    @Test
    public void build_InputLogs_ResultMetricForResponseInfo() {
        // Given
        MetricBuilder metricBuilder = new MetricResponseCodesInfoBuilder();
        List<Log> logs = TestUtils.LOGS_FOR_TESTS;
        Metric expected = new Metric(
            "Коды ответа", List.of
            (
                "Код|Имя|Количество",
                "200|OK|6",
                "400|Bad Request|1"
            )
        );

        // When
        Metric actual = metricBuilder.build(logs);

        // Then
        assertThat(actual).isEqualTo(expected);
    }
}
