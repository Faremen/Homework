package edu.project1;

public record GuessResult(boolean isSymbolAdded, boolean isContainedInWord) {
    public boolean isHit() {
        return isSymbolAdded && isContainedInWord;
    }
}
