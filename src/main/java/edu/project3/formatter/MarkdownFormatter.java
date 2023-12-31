package edu.project3.formatter;

import edu.project3.model.metrics.Metric;

public class MarkdownFormatter extends AbstractFormatter {
    @Override
    public String format(Metric metric) {
        StringBuilder result = new StringBuilder();
        appendHeader(result, "#### ", metric.header());

        int[] maxSpaces = getMaxSpacesForEachColumn(metric.table());
        makeRow(metric.table().get(0), maxSpaces, result);

        for (int maxSpace : maxSpaces) {
            result.append(SPLITTER)
                .append(":")
                .append("-".repeat(maxSpace - 2))
                .append(":");
        }

        result.append(SPLITTER).append('\n');

        for (int i = 1; i < metric.table().size(); i++) {
            makeRow(metric.table().get(i), maxSpaces, result);
        }

        return result.toString();
    }

    private void makeRow(String row, int[] maxSpaces, StringBuilder metricSb) {
        String[] rowData = row.split(REGEX_SPLITTER);

        for (int i = 0; i < rowData.length; i++) {
            metricSb.append(SPLITTER)
                .append(" ".repeat(maxSpaces[i] - rowData[i].length()))
                .append(rowData[i]);
        }

        metricSb.append(SPLITTER).append('\n');
    }
}
