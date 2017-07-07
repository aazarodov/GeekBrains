package com.company.java2.homework6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class AppServer {
    public static final String HOST = "localhost";
    public static final int PORT = 9999;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String message;
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Сервер запущен. Ожидание клиентов...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Клиент подключился");
            ClientHandler clientHandler = new ClientHandler(clientSocket, "Client");
            new Thread(clientHandler).start();
            while ((message = scanner.nextLine()) != "exit") {
                clientHandler.handle(message);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
