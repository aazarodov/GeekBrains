package com.company.java2.homework6_1.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class ChatServer implements Runnable {
    public static final String HOST = "localhost";
    public static final int PORT = 9999;
    private ServerSocket serverSocket;
    //private ArrayList<ClientHandler> arrayOfClientHandler = new ArrayList<>();

    public ChatServer(int port) {
        // запусти серверную часть
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Сервер запущен. Ожидание клиентов...");
    }

    public void handle(String message) {
//        for(ClientHandler clientHandler : arrayOfClientHandler) {
//            try {
//                clientHandler.handle(message);
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
    }

    @Override
    public void run() {
//        while (true) {
//            try {
//                ClientHandler clientHandler = new ClientHandler(serverSocket.accept(), "Client " , this);
//                synchronized (this) {
//                    arrayOfClientHandler.add(clientHandler);
//                    System.out.println("Клиент подключился");
//                    new Thread(clientHandler).start();
//                }
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
    }

//    public ArrayList<ClientHandler> getArrayOfClientHandler() {
//        return arrayOfClientHandler;
//    }
}
