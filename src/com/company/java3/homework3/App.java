package com.company.java3.homework3;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;

/**
 * Created by zarodov on 13.07.2017.
 */
public class App {
    public static void main(String[] args) {
        try {
            task2();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Задание 1. Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль.
     * @throws IOException
     */
    public static void task1() throws IOException {
        System.out.println("Задание №1");
        FileInputStream fileInputStream = new FileInputStream("Java3Homework3Task1.txt");
        byte[] arrayOfByte = new byte[fileInputStream.available()];
        fileInputStream.read(arrayOfByte);
        fileInputStream.close();
        System.out.println(Arrays.toString(arrayOfByte));
        System.out.println(new String(arrayOfByte));
    }

    /**
     * Задание 2. Последовательно сшить 5 файлов в один (файлы также ~100 байт).
     * @throws IOException
     */
    public static void task2() throws IOException {
        ArrayList<FileInputStream> arrayListOfIS = new ArrayList<>();
        FileOutputStream fileOutputStream = new FileOutputStream("Java3Homework3Task2\\Result.txt");
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File file) {
                return !file.getName().equals("Result.txt");
            }
        };
        File fileCatalog = new File("Java3Homework3Task2");
        for (File file : fileCatalog.listFiles(fileFilter)) {
            arrayListOfIS.add(new FileInputStream(file.getAbsoluteFile()));
        }
        Enumeration<FileInputStream> e = Collections.enumeration(arrayListOfIS);
        SequenceInputStream sequenceIS = new SequenceInputStream(e);
        byte[] arrayOfByte = new byte[sequenceIS.available()];
        while ((sequenceIS.read(arrayOfByte)) > 0) {
            fileOutputStream.write(arrayOfByte);
        }
        sequenceIS.close();
        fileOutputStream.close();
    }

    /**
     *
     */
    public static void task3() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String buffer;
        Integer page;
        while (!(buffer = bufferedReader.readLine()).equals("exit")) {
            Integer.parseInt(buffer);

        }
    }
}