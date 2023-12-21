package edu.hw10.task2.fib;

import edu.hw10.task2.Cache;

public interface FibCalculatorWithoutWritingDisk {

    @Cache
    long calcFibonacci(int n);
}
