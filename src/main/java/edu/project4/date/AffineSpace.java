package edu.project4.date;

import java.awt.Color;
import java.util.Random;

public class AffineSpace {

    private static final int COUNT_OF_COLORS = 256;
    private static final int MIN_VALUE = -1;
    private static final int MAX_VALUE = 1;

    private final Color color;
    private double a;
    private double b;
    private double c;
    private double d;
    private double e;
    private double f;

    public AffineSpace() {
        this(new Random());
    }

    public AffineSpace(int seed) {
        this(new Random(seed));
    }

    private AffineSpace(Random random) {
        do {
            do {
                a = random.nextDouble(MIN_VALUE, MAX_VALUE);
                d = random.nextDouble(MIN_VALUE, MAX_VALUE);
            } while ((a * a + d * d) < 1);

            do {
                b = random.nextDouble(MIN_VALUE, MAX_VALUE);
                e = random.nextDouble(MIN_VALUE, MAX_VALUE);
            } while ((b * b + e * e) < 1);
        } while ((a * a + b * b + d * d + e * e) < (1 + (a * e - d * b) * (a * e - d * b)));

        c = random.nextDouble(MIN_VALUE, MAX_VALUE);
        f = random.nextDouble(MIN_VALUE, MAX_VALUE);

        color = new Color(
            random.nextInt(0, COUNT_OF_COLORS),
            random.nextInt(0, COUNT_OF_COLORS),
            random.nextInt(0, COUNT_OF_COLORS)
        );
    }

    public Point calcPoint(Point point) {
        return new Point(
            a * point.x() + b * point.y() + c,
            d * point.x() + e * point.y() + f
        );
    }

    public Color getColor() {
        return color;
    }

    public int getRed() {
        return color.getRed();
    }

    public int getGreen() {
        return color.getGreen();
    }

    public int getBlue() {
        return color.getBlue();
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getD() {
        return d;
    }

    public double getE() {
        return e;
    }

    public double getF() {
        return f;
    }
}
