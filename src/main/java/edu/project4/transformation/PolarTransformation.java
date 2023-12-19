package edu.project4.transformation;

import edu.project4.date.Point;

public class PolarTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double y = point.y();
        double x = point.x();
        return new Point(
            Math.atan(y / x) / Math.PI,
            Math.sqrt(x * x + y * y) - 1
        );
    }
}
