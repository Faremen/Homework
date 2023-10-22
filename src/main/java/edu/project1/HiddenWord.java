package edu.project1;

import java.util.HashSet;

public class HiddenWord {
    private final char maskingChar;
    private final String hiddenWord;
    private final HashSet<Character> inputtedChars = new HashSet<>();

    public HiddenWord(String hiddenWord, char maskingChar) {
        this.hiddenWord = hiddenWord;
        this.maskingChar = maskingChar;
    }

    public GuessResult guessSymbol(char symbol) {
        boolean isSymbolAdded = inputtedChars.add(symbol);
        boolean isContainedInWord = isContainedInWord(symbol);

        return new GuessResult(isSymbolAdded, isContainedInWord);
    }

    private boolean isContainedInWord(char symbol) {
        return hiddenWord.chars().anyMatch((ch) -> ch == symbol);
    }

    public String getMaskedWord() {
        StringBuilder sb = new StringBuilder();

        hiddenWord.chars().forEach((c) -> {
            if (inputtedChars.contains((char) c)) {
                sb.append((char) c);
            } else {
                sb.append(maskingChar);
            }
        });

        return sb.toString();
    }

    public boolean isWordGuessed() {
        for (int i = 0; i < hiddenWord.length(); i++) {
            if (!inputtedChars.contains(hiddenWord.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}
