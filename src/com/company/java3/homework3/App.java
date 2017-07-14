package com.company.java3.homework3;

import java.io.*;
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
            task3();
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
        System.out.println("Задание №2");
        String path = "Java3Homework3Task2";
        String fileResult = "Result.txt";
        ArrayList<FileInputStream> arrayListOfIS = new ArrayList<>();
        FileOutputStream fileOutputStream = new FileOutputStream(path + "\\" + fileResult);
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File file) {
                return !file.getName().equals(fileResult);
            }
        };
        File fileCatalog = new File(path);
        for (File file : fileCatalog.listFiles(fileFilter)) {
            arrayListOfIS.add(new FileInputStream(file.getAbsoluteFile()));
        }
        Enumeration<FileInputStream> e = Collections.enumeration(arrayListOfIS);
        SequenceInputStream sequenceIS = new SequenceInputStream(e);
        byte[] arrayOfByte = new byte[8192];
        int length;
        while ((length = sequenceIS.read(arrayOfByte)) > 0) {
            fileOutputStream.write(arrayOfByte, 0, length);
        }
        fileOutputStream.close();
        sequenceIS.close();
    }

    /**
     * Задание 3. Написать консольное приложение, которое умеет постранично читать текстовые файлы (размером > 10 mb),
     * вводим страницу, программа выводит ее в консоль (за страницу можно принять 1800 символов).
     * Время чтения файла должно находится в разумных пределах (программа не должна загружаться дольше 10 секунд),
     * ну и чтение тоже не должно занимать > 5 секунд.
     * @throws IOException
     */
    public static void task3() throws IOException {
        System.out.println("Задание №3");
        // переменный с подсказками
        String tipOpenFile = "Для открытия файла введите '/readfile <путь к файлу>'";
        String tipViewPage = "Для просмотра страницы файла введите '/page <номер страницы>' (%s - %s)";
        // переменные с командами
        String readFile = "/readfile", viewPage = "/page";
        // переменные с потоками
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fileInputStream;
        // временные переменные
        String buffer, fileName = "", emptyString = "";
        int page, length, countPage = 0;
        long available = 0;
        // массив для чтения
        byte[] arrayOfByte = new byte[1800];
        System.out.println(tipOpenFile);
        // ожидаем ввода пользователя, пока не введено exit
        while (!(buffer = bufferedReader.readLine()).equals("exit")) {
            // если читаем новый файл
            if (buffer.startsWith(readFile)) {
                fileName = buffer.replace(readFile, emptyString).trim();
                fileInputStream = new FileInputStream(fileName);
                available = fileInputStream.available();
                // всего страниц в файле
                countPage = (int)((available + (1800 - available % 1800)) / 1800);
                fileInputStream.close();
                System.out.println(String.format(tipViewPage, 1, countPage));
            // если читаем страницу открытого файла
            } else if (!fileName.trim().equals(emptyString) && buffer.startsWith(viewPage)) {
                fileInputStream = new FileInputStream(fileName);
                page = Integer.parseInt(buffer.replace(viewPage, "").trim());
                // если ввели существующую страницу, выведем ее в консоль
                if (countPage >= page) {
                    // пропустим страницы предшествующие введенной
                    fileInputStream.skip((page - 1) * 1800);
                    if ((length = fileInputStream.read(arrayOfByte)) > 0) {
                        System.out.println(new String(Arrays.copyOfRange(arrayOfByte, 0, length)));
                    }
                    System.out.println(String.format(tipViewPage, 1, countPage));
                }
                fileInputStream.close();
            }
        }
    }
}