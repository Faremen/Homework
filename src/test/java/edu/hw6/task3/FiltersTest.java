package edu.hw6.task3;

import edu.hw6.FileUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class FiltersTest {

    private static final Path TEST_DIR = Paths.get("src/test/java/edu/hw6/task3/Test Files");

    @BeforeAll
    public static void setUp() {
        // Создаю пустые директории, потому что гит не добавит их
        FileUtil.createDirectories(TEST_DIR.resolve("Empty dir"));
        FileUtil.createDirectories(TEST_DIR.resolve("read dir"));

        TEST_DIR.resolve("read only.txt").toFile().setReadOnly();
    }

    @AfterAll
    public static void tearDown() {
        FileUtil.deleteFile(TEST_DIR.resolve("Empty dir"));
        FileUtil.deleteFile(TEST_DIR.resolve("read dir"));
    }

    @Test
    public void readable_FilteredTestDir_ResultReadableFiles() throws IOException {
        // Given
        var filter = Filters.readable();
        List<Path> expected = List.of(
            TEST_DIR.resolve("read and write.txt"),
            TEST_DIR.resolve("read only.txt"),
            TEST_DIR.resolve("empty file.txt"),
            TEST_DIR.resolve("Test1.png"),
            TEST_DIR.resolve("Test2.png"),
            TEST_DIR.resolve("Empty dir"),
            TEST_DIR.resolve("Not empty dir"),
            TEST_DIR.resolve("read dir")
        );

        // When
        List<Path> actual = new ArrayList<>();

        try (var entries = Files.newDirectoryStream(TEST_DIR, filter)) {
            entries.forEach((actual::add));
        }

        // Then
        assertThat(actual).hasSameElementsAs(expected);
    }

    @Test
    public void writable_FilteredTestDir_ResultWritableFiles() throws IOException {
        // Given
        var filter = Filters.writable();
        List<Path> expected = List.of(
            TEST_DIR.resolve("read and write.txt"),
            TEST_DIR.resolve("empty file.txt"),
            TEST_DIR.resolve("Test1.png"),
            TEST_DIR.resolve("Test2.png"),
            TEST_DIR.resolve("Empty dir"),
            TEST_DIR.resolve("Not empty dir"),
            TEST_DIR.resolve("read dir")
        );

        // When
        List<Path> actual = new ArrayList<>();

        try (var entries = Files.newDirectoryStream(TEST_DIR, filter)) {
            entries.forEach((actual::add));
        }

        // Then
        assertThat(actual).hasSameElementsAs(expected);
    }

    @Test
    public void regularFile_FilteredTestDir_ResultRegularFile() throws IOException {
        // Given
        var filter = Filters.regularFile();
        List<Path> expected = List.of(
            TEST_DIR.resolve("read and write.txt"),
            TEST_DIR.resolve("read only.txt"),
            TEST_DIR.resolve("empty file.txt"),
            TEST_DIR.resolve("Test1.png"),
            TEST_DIR.resolve("Test2.png")
        );

        // When
        List<Path> actual = new ArrayList<>();

        try (var entries = Files.newDirectoryStream(TEST_DIR, filter)) {
            entries.forEach((actual::add));
        }

        // Then
        assertThat(actual).hasSameElementsAs(expected);
    }

    @Test
    public void largerThan_FilteredTestDir_ResultFilesWithSizeLargerThan100() throws IOException {
        // Given
        long sizeIsBytes = 100;
        var filter = Filters.largerThan(sizeIsBytes);
        List<Path> expected = List.of(
            TEST_DIR.resolve("Test1.png"),
            TEST_DIR.resolve("Test2.png")
        );

        // When
        List<Path> actual = new ArrayList<>();

        try (var entries = Files.newDirectoryStream(TEST_DIR, filter)) {
            entries.forEach((actual::add));
        }

        // Then
        assertThat(actual).hasSameElementsAs(expected);
    }

    @Test
    public void globMatches_FilteredTestDir_ResultTxtFiles() throws IOException {
        // Given
        String pattern = "*.txt";
        var filter = Filters.globMatches(pattern);
        List<Path> expected = List.of(
            TEST_DIR.resolve("read and write.txt"),
            TEST_DIR.resolve("read only.txt"),
            TEST_DIR.resolve("empty file.txt")
        );

        // When
        List<Path> actual = new ArrayList<>();

        try (var entries = Files.newDirectoryStream(TEST_DIR, filter)) {
            entries.forEach((actual::add));
        }

        // Then
        assertThat(actual).hasSameElementsAs(expected);
    }

    @Test
    public void regexContains_FilteredTestDir_ResultFilesStartWithRead() throws IOException {
        // Given
        String regex = "^read.*";

        var filter = Filters.regexContains(regex);
        List<Path> expected = List.of(
            TEST_DIR.resolve("read and write.txt"),
            TEST_DIR.resolve("read only.txt"),
            TEST_DIR.resolve("read dir")
        );

        // When
        List<Path> actual = new ArrayList<>();

        try (var entries = Files.newDirectoryStream(TEST_DIR, filter)) {
            entries.forEach((actual::add));
        }

        // Then
        assertThat(actual).hasSameElementsAs(expected);
    }

    @Test
    public void magicNumber_FilteredTestDir_ResultFilesWithMagicNumber() throws IOException {
        // Given
        byte[] magicNumber = {(byte) 0x89, 'P', 'N', 'G'};

        var filter = Filters.magicNumber(magicNumber);
        List<Path> expected = List.of(
            TEST_DIR.resolve("Test1.png"),
            TEST_DIR.resolve("Test2.png")
        );

        // When
        List<Path> actual = new ArrayList<>();

        try (var entries = Files.newDirectoryStream(TEST_DIR, filter)) {
            entries.forEach((actual::add));
        }

        // Then
        assertThat(actual).hasSameElementsAs(expected);
    }

    @Test
    public void add_ChainFiltersFilteredTestDir_ResultFilteredFiles() throws IOException {
        // Given
        byte[] magicNumber = {(byte) 0x89, 'P', 'N', 'G'};
        long sizeIsBytes = 10_000;

        var filter = Filters.magicNumber(magicNumber)
            .and(Filters.readable())
            .and(Filters.regularFile())
            .and(Filters.largerThan(sizeIsBytes));

        List<Path> expected = List.of(
            TEST_DIR.resolve("Test2.png")
        );

        // When
        List<Path> actual = new ArrayList<>();

        try (var entries = Files.newDirectoryStream(TEST_DIR, filter)) {
            entries.forEach((actual::add));
        }

        // Then
        assertThat(actual).hasSameElementsAs(expected);
    }

}
