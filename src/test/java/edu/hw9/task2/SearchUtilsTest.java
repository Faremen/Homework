package edu.hw9.task2;

import edu.util.FileUtil;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

final class SearchUtilsTest {
    private static final Path ROOT = Paths.get("src/test/java/edu/hw9/task2/TestFiles/");
    private static final int DEPTH = 2;
    private static final int DIRECTORY_COUNT = 5;
    private static final int FILES_COUNT = 220;
    private static final String EXTENSION = "txt";

    @BeforeAll
    static void beforeAll() throws IOException {
        FileUtil.recursiveCreate(ROOT, DEPTH, DIRECTORY_COUNT, FILES_COUNT, EXTENSION);

        FileUtil.createFile(ROOT.resolve("0.txt"));
        FileUtil.appendInFile(ROOT.resolve("0.txt"), "123456789");

        FileUtil.createFile(ROOT.resolve("0.png"));
        FileUtil.appendInFile(ROOT.resolve("0.png"), "PNG");

        FileUtil.createFile(ROOT.resolve("1/0.txt"));
        FileUtil.appendInFile(ROOT.resolve("1/0.txt"), "Hello!");

        FileUtil.createFile(ROOT.resolve("1/0/0.txt"));
        FileUtil.appendInFile(ROOT.resolve("1/0/0.txt"), "I am file!");

        FileUtil.createFile(ROOT.resolve("0/0/00.txt"));
        FileUtil.appendInFile(ROOT.resolve("0/0/00.txt"), "000000000000000000000000000");
    }

    @AfterAll
    static void afterAll() throws IOException {
        FileUtil.recursiveDelete(ROOT);
    }

    private static Stream<Arguments> findDirectories_ProvideParameters() {
        return Stream.of(
            Arguments.of(
                ROOT,
                1000,
                List.of(
                    ROOT,
                    ROOT.resolve("0/"),
                    ROOT.resolve("1/"),
                    ROOT.resolve("2/"),
                    ROOT.resolve("3/"),
                    ROOT.resolve("4/")
                )
            ),
            Arguments.of(
                ROOT.resolve("0"),
                1000,
                List.of(
                    ROOT.resolve("0")
                )
            ),
            Arguments.of(
                ROOT,
                2000,
                List.of(
                    ROOT
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("findDirectories_ProvideParameters")
    public void findDirectories_InputDirectoryAndFilesCount_ResultListDirectories(
        Path root,
        int filesCount,
        List<Path> expected
    ) {
        // When
        var actual = SearchUtils.findDirectories(root, filesCount);

        // Then
        assertThat(actual).hasSameSizeAs(expected);
        assertThat(actual).containsAll(expected);
    }

    private static Stream<Arguments> findFiles_ProvideParameters() {
        return Stream.of(
            Arguments.of(
                ROOT,
                (Predicate<Path>) path -> {
                    long size;
                    try {
                        size = Files.size(path);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    return size >= 20
                        && size <= 100;
                },
                List.of(
                    ROOT.resolve("0/0/00.txt")
                )
            ),
            Arguments.of(
                ROOT,
                (Predicate<Path>) path -> path.getFileName().toString().endsWith("png"),
                List.of(
                    ROOT.resolve("0.png")
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("findFiles_ProvideParameters")
    public void findFiles_InputDirectoryAndPredicate_ResultListFiles(
        Path root,
        Predicate<Path> predicate,
        List<Path> expected
    ) {
        // When
        var actual = SearchUtils.findFiles(root, predicate);

        // Then
        assertThat(actual).hasSameSizeAs(expected);
        assertThat(actual).containsAll(expected);
    }
}
