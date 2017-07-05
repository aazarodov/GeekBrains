package com.company.java2.lesson6;

import java.io.IOException;

/**
 * Created by aazar on 05.07.2017.
 */
public class App {
    public static void main(String[] args) throws IOException {
        SimpleServer simpleServer = new SimpleServer(9999);
        while (true)
            simpleServer.handle();
    }
}
