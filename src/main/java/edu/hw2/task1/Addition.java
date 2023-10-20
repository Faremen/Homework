package edu.hw2.task1;

public record Addition(Expr firstExpr, Expr secondExpr) implements Expr {
    @Override
    public double evaluate() {
        return firstExpr.evaluate() + secondExpr.evaluate();
    }
}
