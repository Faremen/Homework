package edu.hw1;

public class Task3 {

    private Task3() {}

    public static boolean isNestable(int[] firstArray, int[] secondArray) {
        int minInFirstArray = findMin(firstArray);
        int minInSecondArray = findMin(secondArray);
        int maxInFirstArray = findMax(firstArray);
        int maxInSecondArray = findMax(secondArray);

        return (minInFirstArray > minInSecondArray) && (maxInFirstArray < maxInSecondArray);
    }

    private static int findMin(int[] arr) {
        int min = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }

        return min;
    }

    private static int findMax(int[] arr) {
        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        return max;
    }
}
