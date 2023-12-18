package edu.hw6.task4;

import edu.util.FileUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {

    private static final Path TEST_FILE = Paths.get("src/test/java/edu/hw6/task4/test.txt");

    @BeforeEach
    public void setUp() {
        FileUtil.createFile(TEST_FILE);
    }

    @AfterEach
    public void tearDown() {
        FileUtil.deleteFile(TEST_FILE);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "Programming is learned by writing programs. ― Brian Kernighan",
        "Programming is learned by writing programs.\n ― Brian Kernighan"
    })
    public void write_WriteMessageInFile_ResultMessageInFile(String message) throws IOException {
        // When
        Task4.write(TEST_FILE, message);

        List<String> lines = FileUtil.readAllLines(TEST_FILE);

        StringBuilder actual = new StringBuilder();
        for (int i = 0; i < lines.size(); i++) {
            if (i != lines.size() - 1) {
                actual.append(lines.get(i)).append("\n");
            } else {
                actual.append(lines.get(i));
            }
        }

        // Then
        assertThat(actual.toString()).isEqualTo(message);
    }
}
