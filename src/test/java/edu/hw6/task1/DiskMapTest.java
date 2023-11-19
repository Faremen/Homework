package edu.hw6.task1;

import edu.hw6.FileUtil;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DiskMapTest {
    private static final String TEST_FILE = "src/test/java/edu/hw6/task1/test.txt";
    private static final Path PATH_TEST_FILE = Paths.get(TEST_FILE);
    private DiskMap diskMap;

    @BeforeEach
    public void setUp() {
        diskMap = new DiskMap(TEST_FILE);
    }

    @AfterEach
    public void tearDown() {
        FileUtil.deleteFile(PATH_TEST_FILE);
    }

    @Test
    public void createFile_CreateNewDiskMap_ResultExistsNewFile() {
        // Given
        boolean expected = true;

        // When
        var actual = Files.exists(PATH_TEST_FILE);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> put_ProvideParameters() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Pair<>("k1", "val1")
                ),
                List.of(
                    "k1:val1"
                )
            ),
            Arguments.of(
                List.of(
                    new Pair<>("k1", "val1"),
                    new Pair<>("k2", "val2")
                ),
                List.of(
                    "k1:val1",
                    "k2:val2"
                )
            ),
            Arguments.of(
                List.of(
                    new Pair<>("k1", "val1"),
                    new Pair<>("k1", "val2")
                ),
                List.of(
                    "k1:val2"
                )
            ),
            Arguments.of(
                List.of(
                    new Pair<>("k1", "val1\nnew val1"),
                    new Pair<>("k2", "val2")
                ),
                List.of(
                    "k1:val1\nnew val1",
                    "k2:val2"
                )
            ),
            Arguments.of(
                List.of(
                    new Pair<>("k1", "val1:new val1"),
                    new Pair<>("k2", "val2")
                ),
                List.of(
                    "k1:val1:new val1",
                    "k2:val2"
                )
            ),
            Arguments.of(
                List.of(
                    new Pair<>("k1", ""),
                    new Pair<>("k2", "  ")
                ),
                List.of(
                    "k1:",
                    "k2:  "
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("put_ProvideParameters")
    public void put_InputKeyAndValue_ResultEntryInFileAndInDiskMap(List<Pair<String, String>> inputPair, List<String> expected) {
        // When
        inputPair.forEach((pair) -> diskMap.put(pair.key, pair.value));

        var actualInFile = FileUtil.readAllLinesBreak(PATH_TEST_FILE);
        var actualInDiskMap = diskMap.entrySet()
            .stream()
            .map((entry) -> entry.getKey() + ":" + entry.getValue())
            .toList();

        // Then
        assertThat(actualInFile).isEqualTo(expected);
        assertThat(actualInDiskMap).isEqualTo(expected);
    }

    private static Stream<Arguments> remove_ProvideParameters() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Pair<>("k1", "val1")
                ),
                List.of(
                    "k1"
                ),
                new ArrayList<>()
            ),
            Arguments.of(
                List.of(
                    new Pair<>("k1", "val1"),
                    new Pair<>("k2", "val2")
                ),
                List.of(
                    "k1"
                ),
                List.of(
                    "k2:val2"
                )
            ),
            Arguments.of(
                List.of(
                    new Pair<>("k1", "val1\nnew val1"),
                    new Pair<>("k2", "val2")
                ),
                List.of(
                    "k1"
                ),
                List.of(
                    "k2:val2"
                )
            ),
            Arguments.of(
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
            )
        );
    }

    @ParameterizedTest
    @MethodSource("remove_ProvideParameters")
    public void remove_InputKeyAndValue_ResultEntryInFileAndInDiskMap(List<Pair<String, String>> inputPair, List<String> deletedKeys, List<String> expected) {
        // When
        inputPair.forEach((pair) -> diskMap.put(pair.key, pair.value));
        deletedKeys.forEach((key) -> diskMap.remove(key));

        var actualInFile = FileUtil.readAllLinesBreak(PATH_TEST_FILE);
        var actualInDiskMap = diskMap.entrySet()
            .stream()
            .map((entry) -> entry.getKey() + ":" + entry.getValue())
            .toList();

        // Then
        assertThat(actualInFile).isEqualTo(expected);
        assertThat(actualInDiskMap).isEqualTo(expected);
    }

    private static Stream<Arguments> size_ProvideParameters() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Pair<>("k1", "val1")
                ),
               1
            ),
            Arguments.of(
                List.of(
                    new Pair<>("k1", "val1"),
                    new Pair<>("k2", "val2"),
                    new Pair<>("k3", "val3"),
                    new Pair<>("k4", "val4")
                ),
                4
            ),
            Arguments.of(
                new ArrayList<>(),
                0
            )
        );
    }

    @ParameterizedTest
    @MethodSource("size_ProvideParameters")
    public void size_InputKeyAndValue_ResultSize(List<Pair<String, String>> inputPair, int expected) {
        // When
        inputPair.forEach((pair) -> diskMap.put(pair.key, pair.value));

        var actual = diskMap.size();

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> isEmpty_ProvideParameters() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Pair<>("k1", "val1")
                ),
                false
            ),
            Arguments.of(
                List.of(
                    new Pair<>("k1", "val1"),
                    new Pair<>("k2", "val2"),
                    new Pair<>("k3", "val3"),
                    new Pair<>("k4", "val4")
                ),
                false
            ),
            Arguments.of(
                new ArrayList<>(),
                true
            )
        );
    }

    @ParameterizedTest
    @MethodSource("isEmpty_ProvideParameters")
    public void isEmpty_InputKeyAndValue_ResultIsEmpty(List<Pair<String, String>> inputPair, boolean expected) {
        // When
        inputPair.forEach((pair) -> diskMap.put(pair.key, pair.value));

        var actual = diskMap.isEmpty();


        // Then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> clear_ProvideParameters() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new Pair<>("k1", "val1")
                )
            ),
            Arguments.of(
                List.of(
                    new Pair<>("k1", "val1"),
                    new Pair<>("k2", "val2"),
                    new Pair<>("k3", "val3"),
                    new Pair<>("k4", "val4")
                )
            ),
            Arguments.of(
                new ArrayList<>()
            )
        );
    }

    @ParameterizedTest
    @MethodSource("clear_ProvideParameters")
    public void clear_InputKeyAndValue_ResultIsEmpty(List<Pair<String, String>> inputPair) {
        // Given
        ArrayList<String> expected = new ArrayList<>();

        // When
        inputPair.forEach((pair) -> diskMap.put(pair.key, pair.value));
        diskMap.clear();
        var actualInFile = FileUtil.readAllLinesBreak(PATH_TEST_FILE);
        var actualInDiskMap = diskMap.entrySet()
            .stream()
            .map((entry) -> entry.getKey() + ":" + entry.getValue())
            .toList();


        // Then
        assertThat(actualInFile).isEqualTo(expected);
        assertThat(actualInDiskMap).isEqualTo(expected);
    }

    public record Pair<T, V>(T key, V value) {}
}
