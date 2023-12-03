package edu.hw8.task1.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Closeable {
    private final Socket clientSocket;

    public Client(String host, int port) throws IOException {
        clientSocket = new Socket(host, port);
    }

    public String sendMessage(String message) throws IOException {
        var out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);
        var in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        out.println(message);
        return in.readLine();
    }

    public boolean isClosed() {
        return clientSocket.isClosed();
    }

    @Override
    public void close() throws IOException {
        clientSocket.close();
    }
}
