package com.company.java3.homework4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task3 {

    public static void main(String[] args) {
        MFU mfu = new MFU();
        mfu.printDocument(50);
        mfu.scanDocument(50);
        mfu.printDocument(50);
        mfu.scanDocument(50);
    }

}
