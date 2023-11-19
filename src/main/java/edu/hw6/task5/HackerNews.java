package edu.hw6.task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews implements AutoCloseable {

    private static final URI BASE_URI = URI.create("https://hacker-news.firebaseio.com/v0/");
    private static final URI TOP_STORIES_URI = BASE_URI.resolve("topstories.json");
    private static final URI NEWS_URI = BASE_URI.resolve("item/");
    private static final Pattern TITLE_PATTERN = Pattern.compile("\"title\":\"([^\"]+)\"");

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public long[] hackerNewsTopStories() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(TOP_STORIES_URI).build();

        return parseJSONTopStoriesToLong(getStringHttpResponse(request).body());
    }

    public String news(long id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(NEWS_URI.resolve(id + ".json")).build();

        return getNewsTitle(getStringHttpResponse(request).body());
    }

    @Override
    public void close() {
        httpClient.close();
    }

    private HttpResponse<String> getStringHttpResponse(HttpRequest request) throws IOException, InterruptedException {
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private long[] parseJSONTopStoriesToLong(String json) {
        String[] idNews = json.substring(1, json.length() - 1).split(",");

        return Arrays.stream(idNews)
            .mapToLong(Long::parseLong)
            .toArray();
    }

    private String getNewsTitle(String json) {
        Matcher matcher = TITLE_PATTERN.matcher(json);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }
}
