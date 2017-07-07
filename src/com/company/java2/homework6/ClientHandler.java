package com.company.java2.homework6;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private String name;
    private boolean isStop = false;

    public ClientHandler(Socket socket, String name) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.name = name;
        } catch (IOException e) {
            System.out.println("4");
            e.printStackTrace();
        }
    }

    public void handle(String message) throws IOException {
        bufferedWriter.write(message);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }

    public void close() throws IOException {
        this.isStop = true;
    }

    @Override
    public void run() {
        String message;
        Boolean exit = false;
        while (!this.isStop) {
            try {
                while ((message = bufferedReader.readLine()) != null) {
                    System.out.println(name + ": " + message);
                    if (message.equalsIgnoreCase("exit")) {
                        exit = true;
                        break;
                    }
                }
                if (exit) {
                    break;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (exit) {
            System.out.println(this.name + " отключился");
        }
        try {
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
