package edu.hw8.task3;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Disabled
public class PerformanceTest {
    private static final Map<String, String> MD5_HASH_LOGIN_DATABASE = new HashMap<>() {{
        put("015f28b9df1bdd36427dd976fb73b29d", "fire");
        put("93f725a07423fe1c889f448b33d21f46", "java");
        put("49f68a5c8493ec2c0bf489821c21fc3b", "hi");
        put("81dc9bdb52d04dc20036dbd8313ed055", "1234");
        put("d93591bdf7860e1e4ee2fca799911215", "4321");
        put("4a7d1ed414474e4033ac29ccb8653d9b", "0000");
    }};
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final int MAX_PASSWORD_LENGTH = 4;

    // Обнаружил, что в начале при запуске тестов у меня происходит высокая нагрузка на процессор (90+%)
    // и из-за этого первое нахождение пароля в одном потоке сильно дольше чем все следующие (где-то на 0.2-0.3 секунды)
    // Поэтому тест с первым параметр можно считать как заглушку и показатель найденной проблемы (возможно эта проблема только с моим железом)
    // Вот пример разницы:
    //      Один поток: 1,069 сек
    //      Потоков 1: 0,876 сек
    @ParameterizedTest
    @ValueSource(ints = {
        1, 2, 3, 4, 6, 8
    })
    public void performance(int countThreads) {
        double timeSingleThread = getTimeSingleThread();
        double timeMultiThread = getTimeMultiThread(countThreads);
        printResult(timeSingleThread, timeMultiThread, countThreads);
    }

    private double getTimeSingleThread() {
        var passwordMiner = new PasswordMiner(
            MD5_HASH_LOGIN_DATABASE,
            ALPHABET,
            MAX_PASSWORD_LENGTH
        );

        var start = System.currentTimeMillis();
        passwordMiner.singleThreadedPasswordMiner();
        var end = System.currentTimeMillis();

        return (double) (end - start) / 1e3;
    }

    private double getTimeMultiThread(int countThreads) {
        var passwordMiner = new PasswordMiner(
            MD5_HASH_LOGIN_DATABASE,
            ALPHABET,
            MAX_PASSWORD_LENGTH
        );
        var executorService = Executors.newFixedThreadPool(countThreads);

        var start = System.currentTimeMillis();
        passwordMiner.multiThreadedPasswordMiner(
            executorService,
            countThreads
        );
        var end = System.currentTimeMillis();

        return (double) (end - start) / 1e3;
    }

    private void printResult(
        double timeSingleThread,
        double timeMultiThread,
        int countThreads
    ) {
        System.out.printf("Потоков %d:%n", countThreads);
        System.out.printf("Один поток: %.3f сек%n", timeSingleThread);
        System.out.printf("Потоков %d: %.3f сек%n", countThreads, timeMultiThread);
        System.out.printf("Разница: %.3f сек%n", Math.abs(timeMultiThread - timeSingleThread));
        System.out.printf("Ускорение в %.2fx раз%n", timeSingleThread / timeMultiThread);
    }
}
