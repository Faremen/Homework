package edu.project2;

import java.util.Objects;

public class Cell {

    private Type type;

    public Cell(Type type) {
        setType(type);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        Objects.requireNonNull(type);

        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Cell cell = (Cell) o;

        return type == cell.type;
    }

    @Override
    public int hashCode() {
        return type.hashCode();
    }

    public enum Type {
        WALL, SPACE
    }
}
