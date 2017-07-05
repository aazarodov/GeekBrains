package com.company.java2.lesson6;

import java.io.*;
import java.net.Socket;

/**
 * Created by aazar on 05.07.2017.
 */
public class SimpleClient {
    private Socket socket;

    public SimpleClient(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
    }

    private void handle(String message) throws IOException {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))){
            bufferedWriter.write(String.format("from client=[%s", message));
            bufferedWriter.flush();
            System.out.println(String.format("from client=[%s", message, bufferedReader.readLine()));
        };
    }
}
