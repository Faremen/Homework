package edu.project3.model;

import edu.project3.model.metrics.Metric;
import java.util.List;

public record Report(String format, List<Metric> metrics) {
}
