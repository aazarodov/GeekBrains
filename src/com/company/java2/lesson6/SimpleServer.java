package com.company.java2.lesson6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by aazar on 05.07.2017.
 */
public class SimpleServer implements Runnable {
    private ServerSocket serverSocket;

    public SimpleServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    public void handle() throws IOException {
        try (Socket clienSocket = this.serverSocket.accept();
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clienSocket.getOutputStream()))) {
            bufferedWriter.write("HELLO USER");
            bufferedWriter.flush();
        }

    }

    @Override
    public void run() {
        while (true) {
            try (Socket clientSocket = this.serverSocket.accept();
                 BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                bufferedWriter.write("HELLO USER");
                bufferedWriter.flush();
            } catch (IOException ex) {

            }
        }
    }
}
