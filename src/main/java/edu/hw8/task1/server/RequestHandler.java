package edu.hw8.task1.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler implements Runnable {
    private static final Map<String, String> QUOTES = new HashMap<>() {{
        put(
            "личности",
            "Не переходи на личности там, где их нет."
        );
        put(
            "оскорбления",
            "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами."
        );
        put(
            "глупый",
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма."
        );
        put(
            "интеллект",
            "Чем ниже интеллект, тем громче оскорбления."
        );
        put(
            "молчание",
            "Если волк молчит то лучше его не перебивать."
        );
        put(
            "встать",
            "Легко вставать, когда ты не ложился."
        );
    }};

    private static final String DEFAULT_QUOTE = "Я знаю, что ничего не знаю.";

    private final Socket socket;

    public RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            var out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String response = getResponse(in.readLine().toLowerCase());

            out.println(response);

            out.close();
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {
            }
        }
    }

    private String getResponse(String request) {
        return QUOTES.getOrDefault(request, DEFAULT_QUOTE);
    }
}
