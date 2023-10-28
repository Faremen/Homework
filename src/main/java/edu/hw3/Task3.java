package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Task3 {

    private Task3() {}

    public static <T> Map<T, Integer> freqDict(List<? extends T> list) {
        Objects.requireNonNull(list);

        Map<T, Integer> result = new HashMap<>();

//        list.forEach((i) -> {
//            if (!result.containsKey(i)) {
//                result.put(i, 1);
//            } else {
//                int count = result.get(i);
//                result.put(i, ++count);
//            }
//        });

        list.forEach((i) -> result.merge(i, 1, Integer::sum));

        return result;
    }
}
