package edu.project4.date;

public class FractalImage {

    private final Pixel[][] pixels;

    private final int width;
    private final int height;

    public FractalImage(Pixel[][] pixels) {
        for (int i = 1; i < pixels.length; i++) {
            if (pixels[i].length != pixels[i - 1].length) {
                throw new IllegalArgumentException("Pixel array must be rectangular");
            }
        }

        this.pixels = pixels;
        this.width = pixels[0].length;
        this.height = pixels.length;
    }

    public FractalImage(int width, int height) {
        this.width = width;
        this.height = height;

        pixels = new Pixel[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixels[i][j] = new Pixel();
            }
        }
    }

    public boolean isContains(Point point) {
        return isContains((int) point.x(), (int) point.y());
    }

    public boolean isContains(int x, int y) {
        return x >= 0 && x < width
            && y >= 0 && y < height;
    }

    public Pixel[][] getPixels() {
        return pixels;
    }

    public Pixel getPixel(Point point) {
        return getPixel((int) point.x(), (int) point.y());
    }

    public Pixel getPixel(int x, int y) {
        return pixels[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
