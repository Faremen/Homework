package edu.project4.transformation;

import edu.project4.date.Point;

public class SphereTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double hypothesis = Math.pow(x, 2) + Math.pow(y, 2);
        return new Point(
            x / hypothesis,
            y / hypothesis
        );
    }
}
