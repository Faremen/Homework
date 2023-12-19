package edu.project4.transformation;

import edu.project4.date.Point;

public class HandkerchiefTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = Math.sqrt(x * x + y * y);
        double atan = Math.atan(x / y);
        return new Point(
            r * Math.sin(atan + r),
            r * Math.cos(atan - r)
        );
    }
}
