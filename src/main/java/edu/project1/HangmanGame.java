package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HangmanGame {
    private final static int MIN_LENGTH_WORD = 3;
    private final static char MASKING_CHAR = '*';
    private final Logger logger = LogManager.getLogger();
    private final Session session = new Session(5);
    private final Scanner in = new Scanner(System.in);
    private final HiddenWord hiddenWord;
    private boolean isEndGame = false;

    public HangmanGame() {
        String randomWord = RandomWord.getRandomWord().toLowerCase();
        if (randomWord.length() <= MIN_LENGTH_WORD) {
            isEndGame = true;
        }

        this.hiddenWord = new HiddenWord(randomWord, MASKING_CHAR);
    }

    public void run() {
        while (!isEndGame) {
            logger.info("Слово: " + hiddenWord.getMaskedWord());
            logger.info("Введите символ");

            String inputStr;
            try {
                inputStr = in.next();
            } catch (Exception e) {
                logger.info("Вы вышли из игры!");
                break;
            }

            if (inputStr.length() > 1) {
                continue;
            }

            handleInput(inputStr);
            checkGameEnd();
        }
    }

    private void handleInput(String inputStr) {
        GuessResult result = hiddenWord.guessSymbol(inputStr.toLowerCase().charAt(0));

        if (result.isHit()) {
            logger.info("Вы угадали! :)");
        } else if (!result.isSymbolAdded()) {
            logger.info("Такой символ уже был!");
        } else {
            session.decAttempts();
            logger.info("Вы не угадали! :(");
            logger.info(String.format("Осталось %d попыток", session.getAttempts()));
        }
    }

    private void checkGameEnd() {
        if (session.isAttemptsLeft()) {
            logger.info("Вы проиграли! :(");
            isEndGame = true;
        }
        if (hiddenWord.isWordGuessed()) {
            logger.info("Вы выиграли! :)");
            isEndGame = true;
        }
    }
}
