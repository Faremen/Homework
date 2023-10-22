package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameLogger {
    private final Logger logger = LogManager.getLogger();

    public GameLogger() {}

    public void logWord(String word) {
        logger.info("Слово: " + word);
    }

    public void logInputSymbol() {
        logger.info("Введите символ");
    }

    public void logExitGame() {
        logger.info("Вы вышли из игры!");
    }

    public void logHit() {
        logger.info("Вы угадали! :)");
    }

    public void logMistake() {
        logger.info("Вы не угадали! :(");
    }

    public void logRepeatSymbol() {
        logger.info("Такой символ уже был!");
    }

    public void logAttemptsLeft(int attempts) {
        logger.info(String.format("Осталось %d попыток", attempts));
    }

    public void logWin() {
        logger.info("Вы выиграли! :)");
    }

    public void logLose() {
        logger.info("Вы проиграли! :(");
    }
}
