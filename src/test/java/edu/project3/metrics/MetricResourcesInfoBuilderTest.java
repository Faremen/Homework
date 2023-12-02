package edu.project3.metrics;

import edu.project3.TestUtils;
import edu.project3.model.Log;
import edu.project3.model.metrics.Metric;
import edu.project3.model.metrics.MetricBuilder;
import edu.project3.model.metrics.MetricResourcesInfoBuilder;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MetricResourcesInfoBuilderTest {
    @Test
    public void build_InputLogs_ResultMetricForResourcesInfo() {
        // Given
        MetricBuilder metricBuilder = new MetricResourcesInfoBuilder();
        List<Log> logs = TestUtils.LOGS_FOR_TESTS;
        Metric expected = new Metric(
            "Запрашиваемые ресурсы", List.of
            (
                "Ресурсы|Количество",
                "`/exuding-Secured/contingency%20Future-proofed.css`|2",
                "`/Focused-encoding.svg`|1",
                "`/info-mediaries.php`|1",
                "`/Future-proofed/Customer-focused/Upgradable/internet%20solution_Re-contextualized.css`|1",
                "`/architecture/attitude-oriented/success/Cross-platform-neutral.css`|1",
                "`/multi-state/orchestration.png`|1"
            )
        );

        // When
        Metric actual = metricBuilder.build(logs);

        // Then
        assertThat(actual).isEqualTo(expected);
    }
}
