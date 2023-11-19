package edu.project3.print;

import edu.project3.model.Report;
import edu.project3.model.metrics.Metric;
import edu.project3.printer.MetricPrinter;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MetricPrinterTest {
    @Test
    public void printMetrics_InputIncorrectFormat_ResultIllegalArgumentException() {
        MetricPrinter printer = new MetricPrinter();
        Report report = new Report(
            "notmarkdown",
            List.of(
                new Metric("Test", List.of("dsds|213"))
            )
        );

        assertThatThrownBy(() -> printer.printMetrics(report))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Unexpected value: notmarkdown");
    }
}
