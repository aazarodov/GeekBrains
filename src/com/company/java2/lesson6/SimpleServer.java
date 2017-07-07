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

    @Override
    public void run() {
        //while (true) {
            try (Socket clientSocket = this.serverSocket.accept();
                 BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
            {
                System.out.println(String.format("Client: %s", bufferedReader.readLine()));

                bufferedWriter.write("HELLO CLIENT");
                bufferedWriter.newLine();
                bufferedWriter.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        //}
    }
}
