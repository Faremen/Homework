package edu.hw10.task1.generator.random;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import edu.hw10.task1.generator.Generator;
import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.util.concurrent.ThreadLocalRandom;

public class RandomStringGenerator implements Generator {

    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @SuppressWarnings("MagicNumber")
    @Override
    public Object generate(Parameter parameter) {
        int minLength = 1;
        int maxLength = 50;
        boolean isNotNull = false;

        for (Annotation annotation : parameter.getAnnotations()) {
            if (annotation instanceof Min minAnn) {
                minLength = (int) minAnn.value();
            }
            if (annotation instanceof Max maxAnn) {
                maxLength = (int) maxAnn.value();
            }
            if (annotation instanceof NotNull notNullAnn) {
                isNotNull = true;
            }
        }

        if (!isNotNull) {
            if (ThreadLocalRandom.current().nextBoolean()) {
                return null;
            } else {
                return getRandomString(minLength, maxLength);
            }
        }

        return getRandomString(minLength, maxLength);
    }

    private String getRandomString(int minLength, int maxLength) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = minLength; i < maxLength; i++) {
            stringBuilder.append(ALPHABET.charAt(ThreadLocalRandom.current().nextInt(ALPHABET.length())));
        }

        return stringBuilder.toString();
    }
}
