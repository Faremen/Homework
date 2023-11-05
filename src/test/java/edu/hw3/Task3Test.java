package edu.hw3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class Task3Test {

    private static Stream<Arguments> freqDict_ProvideParameters() {
        return Stream.of(
            Arguments.of(
                new ArrayList<String>(
                    Arrays.asList("a", "bb", "a", "bb")
                ),
                new HashMap<String, Integer>() {{
                    put("a", 2);
                    put("bb", 2);
                }}
            ),
            Arguments.of(
                new ArrayList<String>(
                    Arrays.asList("this", "and", "that", "and")
                ),
                new HashMap<String, Integer>() {{
                    put("this", 1);
                    put("and", 2);
                    put("that", 1);
                }}
            ),
            Arguments.of(
                new ArrayList<String>(
                    Arrays.asList("код", "код", "код", "bug")
                ),
                new HashMap<String, Integer>() {{
                    put("код", 3);
                    put("bug", 1);
                }}
            ),
            Arguments.of(
                new ArrayList<Integer>(
                    Arrays.asList(1, 1, 2, 2)
                ),
                new HashMap<Integer, Integer>() {{
                    put(1, 2);
                    put(2, 2);
                }}
            ),
            Arguments.of(
                new ArrayList<Integer>(),
                new HashMap<Integer, Integer>()
            )
        );
    }

    @ParameterizedTest
    @MethodSource("freqDict_ProvideParameters")
    public void freqDict_InputList_ResultFrequencyDictionary(List<?> inputList, HashMap<?, Integer> expected) {
        // When
        var actual = Task3.freqDict(inputList);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public <T> void freqDict_InputNullList_ResultNullPointerException() {
        assertThatThrownBy(() -> {
            Task3.freqDict(null);
        }).isInstanceOf(NullPointerException.class);
    }
}
