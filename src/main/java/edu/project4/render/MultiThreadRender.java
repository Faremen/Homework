package edu.project4.render;

import edu.project4.date.AffineSpace;
import edu.project4.date.FractalImage;
import edu.project4.transformation.Transformation;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class MultiThreadRender extends Renderer {

    private final int countThreads;

    public MultiThreadRender(int countThreads) {
        this.countThreads = countThreads;
    }

    @Override
    public void render(
        FractalImage fractalImage,
        List<AffineSpace> affineSpaces,
        List<Transformation> transformationList,
        int numberSamples,
        int iterationPerSample,
        ConfigRender configRender
    ) {
        try (ExecutorService executorService = Executors.newFixedThreadPool(countThreads)) {
            for (int i = 0; i < numberSamples; i++) {
                executorService.execute(() -> {
                    pickPointAndTransform(
                        fractalImage,
                        affineSpaces.get(ThreadLocalRandom.current().nextInt(0, affineSpaces.size())),
                        transformationList.get(ThreadLocalRandom.current().nextInt(0, transformationList.size())),
                        iterationPerSample,
                        configRender
                    );
                });
            }
        }
    }
}
