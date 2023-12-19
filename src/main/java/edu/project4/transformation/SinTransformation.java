package edu.project4.transformation;

import edu.project4.date.Point;

public class SinTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        return new Point(
            Math.sin(point.x()),
            Math.sin(point.y())
        );
    }
}
