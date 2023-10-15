package edu.project1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SessionTest {
    private Session session;

    @BeforeEach
    public void setUp() {
        session = new Session(5);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "0,     5",
        "1,     4",
        "2,     3",
        "5,     0",
        "1000,  0"
    })
    public void decAttempts_InputCountCalls_ResultAttempts(int countCalls, int expectedResult) {
        // When
        for (int i = 0; i < countCalls; i++) {
            session.decAttempts();
        }

        // Then
        assertThat(session.getAttempts()).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource(value = {
        "0,     false",
        "1,     false",
        "2,     false",
        "5,     true",
        "1000,  true"
    })
    public void isAttemptsLeft_InputCountCalls_ResultIsAttemptsLeft(int countCalls, boolean expectedResult) {
        // When
        for (int i = 0; i < countCalls; i++) {
            session.decAttempts();
        }

        // Then
        assertThat(session.isAttemptsLeft()).isEqualTo(expectedResult);

    }
}
