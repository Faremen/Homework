package edu.hw6.task5;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.net.http.HttpClient;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class HackerNewsTest {

    private static final HackerNews hackerNews = new HackerNews();

    @AfterAll
    public static void tearDown() {
        hackerNews.close();
    }

    @Test
    public void hackerNewsTopStories_Call_ResultIsNotEmptyLongArray() throws IOException, InterruptedException {
        // When
        var actual = hackerNews.hackerNewsTopStories();

        // Then
        assertThat(actual).isNotEmpty();
    }

    @ParameterizedTest
    @CsvSource(value = {
        "42, An alternative to VC: &#34;Selling In&#34;",
        "1, Y Combinator",
        "3232323, ",
        "-10, "
    })
    public void news_GetNewsById_ResultTitle(long idNews, String expectedTitle) throws IOException, InterruptedException {
        // When
        var actual = hackerNews.news(idNews);

        // Then
        assertThat(actual).isEqualTo(expectedTitle);
    }
}
