package com.company.java3.homework4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by aazar on 17.07.2017.
 */
public class MFU {
    private Object printer = new Object();
    private Object scanner = new Object();
    ExecutorService service = Executors.newFixedThreadPool(2);

    public MFU() {}

    public void printDocument(int countPage) {
        service.execute(() -> print(countPage));
    }

    public void scanDocument(int countPage) {
        service.execute(() -> scan(countPage));
    }

    private void print(int countPage) {
        synchronized (printer) {
            try {
                for (int i = 1; i <= countPage; i++) {
                    System.out.println("Отпечатано " + i + "...");
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void scan(int countPage) {
        synchronized (scanner) {
            try {
                for (int i = 1; i <= countPage; i++) {
                    System.out.println("Отсканировано " + i + "...");
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

