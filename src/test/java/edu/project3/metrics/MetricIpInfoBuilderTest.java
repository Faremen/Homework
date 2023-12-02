package edu.project3.metrics;

import edu.project3.TestUtils;
import edu.project3.model.Log;
import edu.project3.model.metrics.Metric;
import edu.project3.model.metrics.MetricBuilder;
import edu.project3.model.metrics.MetricIpInfoBuilder;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MetricIpInfoBuilderTest {
    @Test
    public void build_InputLogs_ResultMetricIpInfoBuilder() {
        // Given
        MetricBuilder metricBuilder = new MetricIpInfoBuilder();
        List<Log> logs = TestUtils.LOGS_FOR_TESTS;
        Metric expected = new Metric(
            "IP адреса", List.of
            (
                "Часто встречающийся IP адрес|Количество запросов|Время последнего запроса",
                "141.96.175.104|1|25.09.2023 06:10:36",
                "165.138.198.30|1|27.09.2023 06:10:36",
                "72.153.133.97|1|07.10.2023 06:10:36",
                "72.153.133.99|1|05.10.2023 06:10:36",
                "11.71.87.42|1|23.09.2023 06:10:36",
                "185.253.246.248|1|30.09.2023 06:10:36",
                "204.196.83.88|1|02.10.2023 06:10:36"
            )
        );

        // When
        Metric actual = metricBuilder.build(logs);

        // Then
        assertThat(actual).isEqualTo(expected);
    }
}
