package com.company.java3.lesson3;

import java.io.File;

/**
 * Created by aazar on 12.07.2017.
 */
public class App {
    public static void main(String[] args) {
        File file = new File("123.txt");
        System.out.println(file.exists());
        System.out.println(file.canExecute());
    }
}
