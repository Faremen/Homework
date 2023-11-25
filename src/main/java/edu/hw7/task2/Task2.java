package edu.hw7.task2;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.IntStream;

public class Task2 {

    private Task2() {}

    public static BigInteger factorialInParallel(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("num must be > 0");
        }
        if (num == 0 || num == 1) {
            return BigInteger.valueOf(1);
        }

        List<BigInteger> numList = IntStream.rangeClosed(1, num)
            .boxed()
            .map((e) -> new BigInteger(String.valueOf(e)))
            .toList();

        return numList.parallelStream()
            .reduce(BigInteger::multiply)
            .orElse(BigInteger.valueOf(-1));
    }
}
