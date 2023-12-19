package edu.hw10.task2.fib;

import edu.hw10.task2.Cache;

public interface FibCalculatorWithWritingDisk {
    @Cache(persist = true)
    long calcFibonacci(int n);
}
