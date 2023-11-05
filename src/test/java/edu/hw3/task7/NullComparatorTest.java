package edu.hw3.task7;

import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.assertj.core.api.Assertions.assertThat;

public class NullComparatorTest {

    @Test
    public void test_CreateTreeMapWithNullComparator_ResultTreeMapWithNullKey() {
        TreeMap<String, String> tree = new TreeMap<>(new NullComparator<String>(String::compareTo));
        tree.put("13", "dsd");
        tree.put(null, "test");
        tree.put("dsds", "dsds");

        assertThat(tree.containsKey(null)).isTrue();
    }
}
