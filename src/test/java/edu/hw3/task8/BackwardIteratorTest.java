package edu.hw3.task8;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class BackwardIteratorTest {

    private static Stream<Arguments> test_ProvideParameters() {
        return Stream.of(
            Arguments.of(
                new ArrayList<Integer>(
                    Arrays.asList(1, 2, 3, 4)
                ),
                new Integer[] {4, 3, 2, 1}
            ),
            Arguments.of(
                new ArrayList<String>(
                    Arrays.asList("1", "2", "3", "4")
                ),
                new String[] {"4", "3", "2", "1"}
            )
        );
    }

    @ParameterizedTest
    @MethodSource("test_ProvideParameters")
    public <T> void test_CreateBackwardIteratorWithList_ResultBackwardIteration(List<T> inputList, T[] expected) {
        BackwardIterator<T> iterator = new BackwardIterator<>(inputList);
        List<T> actual = new ArrayList<>();

        while (iterator.hasNext()) {
            actual.add(iterator.next());
        }

        assertThat(actual.toArray()).isEqualTo(expected);
    }

    @Test
    public void test_CreateBackwardIteratorWithEmptyList_ResultNoSuchElementException() {
        BackwardIterator<Integer> iterator = new BackwardIterator<>(new ArrayList<Integer>());

        assertThatThrownBy(iterator::next)
            .isInstanceOf(NoSuchElementException.class);
    }
}
