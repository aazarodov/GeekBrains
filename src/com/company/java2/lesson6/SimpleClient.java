package com.company.java2.lesson6;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by aazar on 05.07.2017.
 */
public class SimpleClient {
    private Socket socket;

    public SimpleClient(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
    }

    public void handle(String message) throws IOException {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))){
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            System.out.println(String.format("Server: %s", message, bufferedReader.readLine()));
        };
    }
}
