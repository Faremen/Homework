package edu.hw3.task6;

public record Stock(int price) {

    public Stock {
        if (price < 0) {
            throw new IllegalArgumentException("prise must be greater than 0");
        }
    }
}
