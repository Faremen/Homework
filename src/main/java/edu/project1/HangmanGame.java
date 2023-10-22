package edu.project1;

import java.util.Scanner;

public class HangmanGame {
    private final static int MIN_LENGTH_WORD = 3;
    private final static char MASKING_CHAR = '*';
    private final static int DEFAULT_COUNT_ATTEMPTS = 5;
    private final GameLogger gameLogger = new GameLogger();
    private final Session session;
    private final Scanner in = new Scanner(System.in);
    private final HiddenWord hiddenWord;
    private boolean isEndGame = false;

    public HangmanGame() {
        this(RandomWord.getRandomWord());
    }

    public HangmanGame(String word) {
        this(word, DEFAULT_COUNT_ATTEMPTS);
    }

    public HangmanGame(String word, int countAttempts) {
        String tempWord = checkWord(word);

        if (countAttempts < 1) {
            isEndGame = true;
        }

        this.hiddenWord = new HiddenWord(tempWord, MASKING_CHAR);
        this.session = new Session(countAttempts);
    }

    public void run() {
        while (!isEndGame) {
            gameLogger.logWord(hiddenWord.getMaskedWord());
            gameLogger.logInputSymbol();

            String inputStr;
            try {
                inputStr = in.nextLine();
            } catch (Exception e) {
                gameLogger.logExitGame();
                break;
            }

            if (inputStr.length() != 1) {
                continue;
            }

            handleInput(inputStr);
            checkGameEnd();
        }
    }

    private String checkWord(String word) {
        if (word.length() <= MIN_LENGTH_WORD) {
            isEndGame = true;
        }

        return word.toLowerCase();
    }

    private void handleInput(String inputStr) {
        GuessResult result = hiddenWord.guessSymbol(inputStr.toLowerCase().charAt(0));

        if (result.isHit()) {
            gameLogger.logHit();
        } else if (!result.isSymbolAdded()) {
            gameLogger.logRepeatSymbol();
        } else {
            session.decAttempts();
            gameLogger.logMistake();
            gameLogger.logAttemptsLeft(session.getAttempts());
        }
    }

    private void checkGameEnd() {
        if (session.isAttemptsLeft()) {
            gameLogger.logLose();
            isEndGame = true;
        }
        if (hiddenWord.isWordGuessed()) {
            gameLogger.logWin();
            isEndGame = true;
        }
    }

    public int getAttempts() {
        return session.getAttempts();
    }

    public boolean getIsAttemptLeft() {
        return session.isAttemptsLeft();
    }

    public String getMaskedWord() {
        return hiddenWord.getMaskedWord();
    }

    public boolean getIsWordGuessed() {
        return hiddenWord.isWordGuessed();
    }

    public boolean getIsEndGame() {
        return isEndGame;
    }
}
