package edu.project4.transformation;

import edu.project4.date.Point;

public class NotTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        return point;
    }
}
