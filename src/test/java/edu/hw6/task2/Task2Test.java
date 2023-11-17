package edu.hw6.task2;

import edu.hw6.FileUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    private static Stream<Arguments> cloneFile_ProvideParameters() {
        return Stream.of(
            Arguments.of(
                Paths.get("src/test/java/edu/hw6/task2/test.txt"),
                3,
                List.of(
                    Paths.get("src/test/java/edu/hw6/task2/test — копия.txt"),
                    Paths.get("src/test/java/edu/hw6/task2/test — копия (2).txt"),
                    Paths.get("src/test/java/edu/hw6/task2/test — копия (3).txt")
                )
            ),
            Arguments.of(
                Paths.get("src/test/java/edu/hw6/task2/test.test.txt"),
                3,
                List.of(
                    Paths.get("src/test/java/edu/hw6/task2/test.test — копия.txt"),
                    Paths.get("src/test/java/edu/hw6/task2/test.test — копия (2).txt"),
                    Paths.get("src/test/java/edu/hw6/task2/test.test — копия (3).txt")
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("cloneFile_ProvideParameters")
    public void cloneFile_CreateNewFileAndNTimesClone_ResulClones(Path fileFroCloning, int countClone, List<Path> expectedFiles) {
        // When
        FileUtil.createFile(fileFroCloning);

        List<Path> actualFiles = new ArrayList<>();
        for (int i = 0; i < countClone; i++) {
            actualFiles.add(Task2.cloneFile(fileFroCloning));
        }

        FileUtil.deleteFile(fileFroCloning);
        FileUtil.deleteFiles(actualFiles);

        // Then

        assertThat(actualFiles).isEqualTo(expectedFiles);
    }

    private static Stream<Arguments> cloneFile_directory_ProvideParameters() {
        return Stream.of(
            Arguments.of(
                Paths.get("src/test/java/edu/hw6/task2/test"),
                3,
                List.of(
                    Paths.get("src/test/java/edu/hw6/task2/test — копия"),
                    Paths.get("src/test/java/edu/hw6/task2/test — копия (2)"),
                    Paths.get("src/test/java/edu/hw6/task2/test — копия (3)")
                )
            ),
            Arguments.of(
                Paths.get("src/test/java/edu/hw6/task2/test.test"),
                3,
                List.of(
                    Paths.get("src/test/java/edu/hw6/task2/test.test — копия"),
                    Paths.get("src/test/java/edu/hw6/task2/test.test — копия (2)"),
                    Paths.get("src/test/java/edu/hw6/task2/test.test — копия (3)")
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("cloneFile_directory_ProvideParameters")
    public void cloneFile_CreateNewDirectoryAndNTimesClone_ResulClones(Path directoryFroCloning, int countClone, List<Path> expectedDirectories) {
        // When
        FileUtil.createDirectories(directoryFroCloning);

        List<Path> actualDirectories = new ArrayList<>();
        for (int i = 0; i < countClone; i++) {
            actualDirectories.add(Task2.cloneFile(directoryFroCloning));
        }

        FileUtil.deleteFile(directoryFroCloning);
        FileUtil.deleteFiles(actualDirectories);

        // Then
        assertThat(actualDirectories).isEqualTo(expectedDirectories);
    }

    @Test
    public void cloneFile_CloneNonexistentFile_ResulNull() {
        // Given
        Path nonexistentFile = Paths.get("src/test/java/edu/hw6/task2/nonexistentFile.txt");
        Path expected = null;

        // When
        var actual = Task2.cloneFile(nonexistentFile);

        // Then
        assertThat(actual).isEqualTo(expected);
    }
}
