package com.company.java2.lesson6;

import java.io.IOException;
import java.util.Scanner;

public class App {

    public static final String HOST = "localhost";
    public static final int PORT = 9999;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String buffer;
        System.out.println("Запускаемся как сервер (y / n - запустится клиент)?");
        buffer = scanner.nextLine();
        if (buffer == "y") {
            SimpleServer simpleServer = new SimpleServer(PORT);
            Thread serverThread = new Thread(simpleServer);
            serverThread.start();
        } else {
            SimpleClient simpleClient = new SimpleClient(HOST, PORT);
            while ((buffer = scanner.nextLine()) != "exit") {
                simpleClient.handle(buffer);
            }
        }
    }
}
