package com.company.java3.homework4;

import java.io.*;

public class Task2 {
    private FileOutputStream file;

    public static void main(String[] args) {
        try {
            Task2 task2 = new Task2();
            Thread thread1 = new Thread(() -> task2.writeToFile("Thread 1"));
            thread1.start();
            Thread thread2 = new Thread(() -> task2.writeToFile("Thread 2"));
            thread2.start();
            Thread thread3 = new Thread(() -> task2.writeToFile("Thread 3"));
            thread3.start();
            thread1.join();
            thread2.join();
            thread3.join();
            task2.closeFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Task2() throws FileNotFoundException {
        file = new FileOutputStream("Homework4Task2.txt");
    }

    public void writeToFile(String text) {
        try {
            for (int i = 1; i < 11; i++) {
                file.write((text + ": record " + i + '\n').getBytes());
                Thread.sleep(20);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeFile() throws IOException {
        file.close();
    }
}
