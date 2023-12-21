package edu.hw10.task2;

import edu.hw10.task2.fib.FibCalculatorWithWritingDisk;
import edu.hw10.task2.fib.FibCalculatorWithWritingDiskImpl;
import edu.hw10.task2.fib.FibCalculatorWithoutWritingDisk;
import edu.hw10.task2.fib.FibCalculatorWithoutWritingDiskImpl;
import edu.util.FileUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.assertj.core.api.Assertions.assertThat;

public class CacheProxyTest {

    private final Path cacheFile = Paths.get("src/test/java/edu/hw10/task2/cacheFile.txt");

    @BeforeEach
    public void setUp() {
        FileUtil.createFile(cacheFile);
    }

    @AfterEach
    public void tearDown() {
        FileUtil.deleteFile(cacheFile);
    }

    @Test
    public void create_InputFibCalculatorWithoutWritingDisk_ResultFibNumber() {
        // Given
        int fibNum = 20;
        long expected = 6765L;
        FibCalculatorWithoutWritingDisk fibCalculator = CacheProxy.create(
            new FibCalculatorWithoutWritingDiskImpl(),
            FibCalculatorWithoutWritingDiskImpl.class,
            cacheFile
        );

        // When
        var actual = fibCalculator.calcFibonacci(fibNum);
        var actualCache = fibCalculator.calcFibonacci(fibNum);

        // Then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualCache).isEqualTo(expected);
    }

    @Test
    public void create_InputFibCalculatorWithWritingDisk_ResultFibNumber() throws IOException {
        // Given
        int fibNum = 20;
        long expected = 6765L;
        FibCalculatorWithWritingDisk fibCalculator = CacheProxy.create(
            new FibCalculatorWithWritingDiskImpl(),
            FibCalculatorWithWritingDiskImpl.class,
            cacheFile
        );

        // When
        var actual = fibCalculator.calcFibonacci(fibNum);
        var actualCache = fibCalculator.calcFibonacci(fibNum);
        var actualCacheFileSize = Files.size(cacheFile);

        // Then
        assertThat(actual).isEqualTo(expected);
        assertThat(actualCache).isEqualTo(expected);
        assertThat(actualCacheFileSize).isGreaterThan(0);
    }
}
