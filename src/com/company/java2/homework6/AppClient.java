package com.company.java2.homework6;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class AppClient {
    public static final String HOST = "localhost";
    public static final int PORT = 9999;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String message;
        Socket socket = new Socket(HOST, PORT);
        ClientHandler clientHandler = new ClientHandler(socket, "Server");
        Thread thread = new Thread(clientHandler);
        thread.start();

        do  {
            message = scanner.nextLine();
            clientHandler.handle(message);
        } while (!message.equals("exit"));
        clientHandler.close();
    }
}

