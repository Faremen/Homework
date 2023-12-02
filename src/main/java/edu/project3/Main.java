package edu.project3;

import edu.project3.printer.MetricPrinter;

public final class Main {
    private Main() {}

    // --path src/main/resources/project3/mini_nginx_logs.txt --format adoc
    // --path src/main/resources/project3/mini_nginx_logs.txt --format markdown
    // --path src/main/resources/project3/nginx_logs.txt --format adoc
    // --path src/main/resources/project3/nginx_logs.txt --format markdown
    // --path https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs --format adoc
    // --path https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs --format markdown
    public static void main(String[] args) {
        var report = new LogAnalyzer().analyze(args);
        MetricPrinter printer = new MetricPrinter();
        printer.printMetrics(report);
    }
}
