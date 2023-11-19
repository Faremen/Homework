package edu.hw6.task6;

public enum Status {
    FREE ("Свободен"),
    OCCUPIED ("Занят");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
