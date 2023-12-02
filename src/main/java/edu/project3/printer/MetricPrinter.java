package edu.project3.printer;

import edu.project3.formatter.AdocFormatter;
import edu.project3.formatter.Formatter;
import edu.project3.formatter.MarkdownFormatter;
import edu.project3.model.Report;

public class MetricPrinter {
    private Formatter getFormatPrinter(String format) {
        return switch (format) {
            case "markdown" -> new MarkdownFormatter();
            case "adoc" -> new AdocFormatter();
            default -> throw new IllegalArgumentException("Unexpected value: " + format);
        };
    }

    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    public void printMetrics(Report report) {
        Formatter printer = getFormatPrinter(report.format());
        report.metrics().forEach(metric -> System.out.println(printer.format(metric)));
    }
}
