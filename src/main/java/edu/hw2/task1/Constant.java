package edu.hw2.task1;

public record Constant(double num) implements Expr {
    @Override
    public double evaluate() {
        return num;
    }
}
