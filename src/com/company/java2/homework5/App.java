package com.company.java2.homework5;

public class App {
    static final int SIZE = 10000000;
    static final int H = SIZE / 2;

    public static void main(String[] args) {
        method1();
        method2();
    }

    /**
     * Метод заполняет массив единицами
     * @param array
     */
    public static void fillArrayOnes(float[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = 1;
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
        // заливаем массив обратно
        System.arraycopy(arrayPartOne, 0, arrayOfFloat, 0, H);
        System.arraycopy(arrayPartTwo, 0, arrayOfFloat, H, H);
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
