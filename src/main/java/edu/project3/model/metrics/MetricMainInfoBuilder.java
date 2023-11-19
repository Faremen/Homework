package edu.project3.model.metrics;

import edu.project3.model.Log;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MetricMainInfoBuilder implements MetricBuilder {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final OffsetDateTime startDate;
    private final OffsetDateTime endDate;
    private final List<String> paths;

    public MetricMainInfoBuilder(OffsetDateTime startDate, OffsetDateTime endDate, List<String> paths) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.paths = paths;
    }

    @Override
    public Metric build(List<Log> logs) {
        return buildMainMetricInfo(logs, startDate, endDate, paths);
    }

    private Metric buildMainMetricInfo(
        List<Log> logs,
        OffsetDateTime startDate,
        OffsetDateTime endDate,
        List<String> paths
    ) {
        List<String> metricList = new ArrayList<>();

        metricList.add("Метрика|Значение");

        String logNames = String.join("`, `", paths);
        metricList.add("Файл(ы)|" + "`" + logNames + "`");
        metricList.add("Начальная дата|" + (startDate.equals(OffsetDateTime.MIN) ? "-" : startDate.format(FORMATTER)));
        metricList.add("Конечная дата|" + (endDate.equals(OffsetDateTime.MAX) ? "-" : endDate.format(FORMATTER)));
        metricList.add("Количество запросов|" + logs.size());

        long averageBytesAnswer = (long) logs.stream()
            .mapToInt(log -> log.response().bytesSend())
            .average()
            .orElse(-1);

        metricList.add("Средний размер ответа|" + averageBytesAnswer + " byte");

        return new Metric("Общая информация", metricList);
    }
}
