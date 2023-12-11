package edu.hw8.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import static org.assertj.core.api.Assertions.assertThat;

public class PasswordMinerTest {
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

    private PasswordMiner passwordMiner;

    @BeforeEach
    public void setUp() {
        passwordMiner = new PasswordMiner(
            MD5_HASH_LOGIN_DATABASE,
            ALPHABET,
            MAX_PASSWORD_LENGTH
        );
    }

    @Test
    public void singleThreadedPasswordMiner_MinePasswordsInOneThreads_ResultPasswordMap() {
        // Given
        Map<String, String> expected = new HashMap<>() {{
            put("fire", "fire");
            put("java", "java");
            put("hi", "hi");
            put("1234", "1234");
            put("4321", "4321");
            put("0000", "0000");
        }};

        // When
        passwordMiner.singleThreadedPasswordMiner();
        var actual = passwordMiner.getLoginPasswordDatabase();

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void multiThreadedPasswordMiner_MinePasswordsInFourThreads_ResultPasswordMap() {
        // Given
        int countThreads = 4;
        var executorService = Executors.newFixedThreadPool(countThreads);
        Map<String, String> expected = new HashMap<>() {{
            put("fire", "fire");
            put("java", "java");
            put("hi", "hi");
            put("1234", "1234");
            put("4321", "4321");
            put("0000", "0000");
        }};

        // When
        passwordMiner.multiThreadedPasswordMiner(
            executorService,
            countThreads
        );
        var actual = passwordMiner.getLoginPasswordDatabase();

        // Then
        assertThat(actual).isEqualTo(expected);
    }
}
