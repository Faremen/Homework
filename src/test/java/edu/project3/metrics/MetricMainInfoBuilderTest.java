package edu.project3.metrics;

import edu.project3.model.Log;
import edu.project3.model.metrics.Metric;
import edu.project3.model.metrics.MetricMainInfoBuilder;
import edu.project3.parser.logparser.LogParser;
import edu.project3.parser.logparser.NginxLogParser;
import edu.project3.parser.pathparser.PathParser;
import edu.project3.receiver.Receiver;
import edu.project3.receiver.path.PathLogReceiver;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import org.junit.jupiter.api.Test;
import static edu.project3.TestUtils.LOGS_FOR_TESTS;
import static org.assertj.core.api.Assertions.assertThat;

public class MetricMainInfoBuilderTest {
    private final OffsetDateTime fromDateOffset = OffsetDateTime.of(2023, 10, 5, 0, 0, 0, 0, ZoneOffset.UTC);
    private final OffsetDateTime toDateOffset = OffsetDateTime.of(2023, 10, 7, 0, 0, 0, 0, ZoneOffset.UTC);

    @Test
    public void build_InputOneFile_ResultMetricForMainInfo() {
        // Given
        MetricMainInfoBuilder metricBuilder = new MetricMainInfoBuilder(
            OffsetDateTime.MIN, OffsetDateTime.MAX,
            List.of(
                "src/test/java/edu/project3/Test logs/log1.txt"
            )
        );

        Metric expected = new Metric(
            "Общая информация", List.of
            (
                "Метрика|Значение",
                "Файл(ы)|`src/test/java/edu/project3/Test logs/log1.txt`",
                "Начальная дата|-",
                "Конечная дата|-",
                "Количество запросов|7",
                "Средний размер ответа|1779 byte"
            )
        );

        // When
        Metric actual = metricBuilder.build(LOGS_FOR_TESTS);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void build_InputTwoFiles_ResultMetricForMainInfoWithoutSomeLogs() {
        // Given
        List<Path> pathsToLogs = PathParser.getPaths("src/test/java/edu/project3/Test logs/*");
        Receiver receiver = new PathLogReceiver(pathsToLogs);
        LogParser logParser = new NginxLogParser();
        List<Log> logs = filterLogsByDate(logParser.parseLogs(receiver.receive()));
        MetricMainInfoBuilder metricBuilder = new MetricMainInfoBuilder(
            fromDateOffset,
            toDateOffset,
            List.of(
                "src/test/java/edu/project3/Test logs/log1.txt", "src/test/java/edu/project3/Test logs/log2.txt"
            )
        );

        Metric expected = new Metric(
            "Общая информация", List.of
            (
                "Метрика|Значение",
                "Файл(ы)|`src/test/java/edu/project3/Test logs/log1.txt`, `src/test/java/edu/project3/Test logs/log2.txt`",
                "Начальная дата|05.10.2023",
                "Конечная дата|07.10.2023",
                "Количество запросов|4",
                "Средний размер ответа|1479 byte"
            )
        );

        // When
        Metric actual = metricBuilder.build(logs);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    private List<Log> filterLogsByDate(List<Log> logs) {
        return logs.stream()
            .filter(log -> (log.date().isBefore(toDateOffset) && log.date().isAfter(fromDateOffset)))
            .toList();
    }
}
