package edu.project4.date;

import java.awt.Color;
import java.util.Objects;

public class Pixel {

    public static final Color DEFAUT_PIXEL_COLOR = new Color(0);
    private Color color;
    private int hitCount;
    private double normal;


    public Pixel() {
        color = DEFAUT_PIXEL_COLOR;
    }

    public Pixel(Color color, int hitCount) {
        this.color = color;
        this.hitCount = hitCount;
    }

    public void incHitCount() {
        hitCount++;
    }

    public Color getColor() {
        return color;
    }

    public int getHitCount() {
        return hitCount;
    }

    public int getRGB() {
        return color.getRGB();
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

    public double getNormal() {
        return normal;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pixel pixel = (Pixel) o;

        if (hitCount != pixel.hitCount) return false;
        if (Double.compare(normal, pixel.normal) != 0) return false;
        return Objects.equals(color, pixel.color);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = color != null ? color.hashCode() : 0;
        result = 31 * result + hitCount;
        temp = Double.doubleToLongBits(normal);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
