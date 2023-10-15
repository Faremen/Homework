package edu.project1;

public class Session {
    private int attempts;

    public Session(int maxAttempts) {
        this.attempts = maxAttempts;
    }

    public void decAttempts() {
        if (attempts > 0) {
            attempts--;
        }
    }

    public boolean isAttemptsLeft() {
        return attempts <= 0;
    }

    public int getAttempts() {
        return attempts;
    }
}
