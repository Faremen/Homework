package edu.hw10.task2.fib;

public class FibCalculatorWithWritingDiskImpl implements FibCalculatorWithWritingDisk {
    @Override
    public long calcFibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }

        long[] arr = new long[n + 1];

        arr[0] = 0;
        arr[1] = 1;

        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }

        return arr[n];
    }
}
