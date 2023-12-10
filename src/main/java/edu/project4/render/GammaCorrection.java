package edu.project4.render;

import edu.project4.date.FractalImage;
import edu.project4.date.Pixel;
import java.awt.Color;

public class GammaCorrection {

    private GammaCorrection() {
    }

    public static void correct(FractalImage fractalImage, Double gamma) {
        double maxNormal = -1;

        for (int x = 0; x < fractalImage.getWidth(); x++) {
            for (int y = 0; y < fractalImage.getHeight(); y++) {
                Pixel pixel = fractalImage.getPixel(x, y);

                if (pixel.getHitCount() != 0) {
                    pixel.setNormal(Math.log10(pixel.getHitCount()));
                }
                if (maxNormal < pixel.getNormal()) {
                    maxNormal = pixel.getNormal();
                }
            }
        }

        for (int x = 0; x < fractalImage.getWidth(); x++) {
            for (int y = 0; y < fractalImage.getHeight(); y++) {
                Pixel pixel = fractalImage.getPixel(x, y);

                pixel.setNormal(pixel.getNormal() / maxNormal);

                pixel.setColor(new Color(
                        (int) (pixel.getRed() * Math.pow(pixel.getNormal(), 1d / gamma)),
                        (int) (pixel.getGreen() * Math.pow(pixel.getNormal(), 1d / gamma)),
                        (int) (pixel.getBlue() * Math.pow(pixel.getNormal(), 1d / gamma))
                    )
                );
            }
        }
    }
}
