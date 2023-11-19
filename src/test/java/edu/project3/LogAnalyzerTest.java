package edu.project3;

import edu.project3.model.Report;
import edu.project3.printer.MetricPrinter;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class LogAnalyzerTest {
    private static Stream<Arguments> analyze_ProvideParameters() {
        return Stream.of(
            Arguments.of((Object) new String[] {"--path", "src/test/java/edu/project3/Test logs/log1.txt", "--format", "adoc"}),
            Arguments.of((Object) new String[] {"--path", "src/test/java/edu/project3/Test logs/log1.txt", "--format", "markdown"})
        );
    }

    @ParameterizedTest
    @MethodSource("analyze_ProvideParameters")
    public void analyze_CallAndPrint_ResultNotThrowException(String[] args) {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        MetricPrinter printer = new MetricPrinter();

        assertDoesNotThrow(() -> {
            Report report = logAnalyzer.analyze(args);
            printer.printMetrics(report);
        });
    }
}
