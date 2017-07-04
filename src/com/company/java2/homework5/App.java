package com.company.java2.homework5;

import java.util.ArrayList;

public class App {
    static final int SIZE = 10000000;
    static final int H = SIZE / 2;

    public static void main(String[] args) throws InterruptedException {
        method1();
        method2();
        method2Refactor();
    }

    /**
     * Метод заполняет массив единицами
     * @param array
     */
    public static void fillArrayOnes(float[] array) {
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                array[i] = 1;
            }
        }
    }

    /**
     * Метод просто бежит по массиву и вычисляет значения
     */
    public static void method1() {
        float[] arrayOfFloat = new float[SIZE];
        fillArrayOnes(arrayOfFloat);
        long startTime = System.currentTimeMillis();
        ThreadForTask.fillArray(arrayOfFloat);
        System.out.println(System.currentTimeMillis() - startTime);
    }

    /**
     * Метод разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы обратно в один
     */
    public static void method2() {
        float[] arrayOfFloat = new float[SIZE];
        fillArrayOnes(arrayOfFloat);
        long startTime = System.currentTimeMillis();
        // первая часть массива и поток для его заполнения
        float[] arrayPartOne = new float[H];
        System.arraycopy(arrayOfFloat, 0, arrayPartOne, 0, H);
        ThreadForTask threadOne = new ThreadForTask(arrayPartOne);
        // вторая часть массива и поток для его заполнения
        float[] arrayPartTwo = new float[H];
        System.arraycopy(arrayOfFloat, H, arrayPartTwo, 0, H);
        ThreadForTask threadTwo = new ThreadForTask(arrayPartTwo);
        // запуск потоков
        threadOne.start();
        threadTwo.start();
        try {
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // заливаем части обратно в массив
        System.arraycopy(arrayPartOne, 0, arrayOfFloat, 0, H);
        System.arraycopy(arrayPartTwo, 0, arrayOfFloat, H, H);
        System.out.println(System.currentTimeMillis() - startTime);
    }

    /**
     * Метод разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы обратно в один
     * Отличие от предыдущего: операции выполняются в циклах
     */
    public static void method2Refactor() throws InterruptedException {
        float[] arrayOfFloat = new float[SIZE];
        ArrayList<ThreadForTask> arrayOfThread = new ArrayList<ThreadForTask>();
        fillArrayOnes(arrayOfFloat);
        long startTime = System.currentTimeMillis();
        // делим массив на две части и запускаем обработку каждой части в потоке
        for (int i = 0; i < 2; i++) {
            float[] arrayPart = new float[H];
            System.arraycopy(arrayOfFloat, i * H, arrayPart, 0, H);
            ThreadForTask threadForTask = new ThreadForTask(arrayPart);
            arrayOfThread.add(threadForTask);
            threadForTask.start();
        }
        // ждем пока не выполнятся оба потока
        for (ThreadForTask threadForTask : arrayOfThread) {
            threadForTask.join();
        }
        // заливаем части обратно в массив
        for (int i = 0; i < 2; i++) {
            System.arraycopy(arrayOfThread.get(i).getArrayOfFloat(), 0, arrayOfFloat, i * H, H);
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
