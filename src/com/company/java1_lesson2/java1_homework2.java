package com.company.java1_lesson2;

import java.util.Arrays;

public class java1_homework2 {
    // 1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1,
    // 0, 0 ]. С помощью цикла и условия заменить 0 на 1, 1 на 0;
    public static void exercise1() {
        System.out.println("Exercise #1");
        int[] arrayInt =  {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.println(Arrays.toString(arrayInt));
        for(int i = 0; i < arrayInt.length; i++) {
            if (arrayInt[i] == 0) {
                arrayInt[i] = 1;
            } else {
                arrayInt[i] = 0;
            }
        }
        System.out.println(Arrays.toString(arrayInt));
        System.out.println("-----------");
    }

    // 2. Задать  пустой  целочисленный  массив  размером  8.  С  помощью  цикла  заполнить  его
    // значениями 0 3 6 9 12 15 18 21;
    public static void exercise2() {
        System.out.println("Exercise #2");
        int[] arrayInt = new int[8];
        for (int i = 0; i < arrayInt.length; i++) {
            arrayInt[i] = i * 3;
        }
        System.out.println(Arrays.toString(arrayInt));
        System.out.println("-----------");
    }

    // 3. Задать массив  [  1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1  ]  пройти по нему циклом, и числа  меньшие 6
    // умножить на 2;
    public static void exercise3() {
        System.out.println("Exercise #3");
        int[] arrayInt = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println(Arrays.toString(arrayInt));
        for (int i = 0; i < arrayInt.length; i++) {
            if (arrayInt[i] < 6) {
                arrayInt[i] = arrayInt[i] * 2;
            }
        }
        System.out.println(Arrays.toString(arrayInt));
        System.out.println("-----------");
    }

    // 4. Создать  квадратный  двумерный  целочисленный  массив  (количество  строк  и  столбцов
    // одинаковое), и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
    public static void exercise4() {
        System.out.println("Exercise #4");
        int[][] arrayInt = new int[5][5];
        for (int i = 0; i < arrayInt.length; i++) {
            arrayInt[i][i] = 1;
            System.out.println(Arrays.toString(arrayInt[i]));
        }
        System.out.println("-----------");
    }

    // 5  **  Задать  одномерный  массив  и  найти  в  нем  минимальный  и  максимальный  элементы  (без
    // помощи интернета);
    public static void exercise5() {
        System.out.println("Exercise #4");
        int[] arrayInt = new int[10];
        int min = 0, max = 0;
        for (int i = 0; i < arrayInt.length; i++) {
            arrayInt[i] = (int) (Math.random() * 100);
            if (i == 0) {
                min = arrayInt[0];
                max = arrayInt[0];
            }
            if (min > arrayInt[i]) {
                min = arrayInt[i];
            }
            if (max < arrayInt[i]) {
                max = arrayInt[i];
            }
        }
        System.out.println(Arrays.toString(arrayInt));
        System.out.println("min: " + min);
        System.out.println("max: " + max);
        System.out.println("-----------");
    }

    // 6  **  Написать  метод,  в  который  передается  не  пустой  одномерный  целочисленный  массив,
    // метод должен вернуть true если в массиве есть место, в котором сумма левой и правой части
    // массива равны. Примеры:  checkBalance([1, 1, 1,  ||  2, 1]) →  true,  checkBalance ([2, 1, 1, 2, 1]) →
    // false,  checkBalance  ([10,  ||  10])  →  true, граница показана символами  ||, эти символы в массив
    // не входят.
    public static void exercise6() {

    }

    // 7  ****  Написать метод, которому на вход подается одномерный массив и число  n  (может быть
    // положительным,  или  отрицательным),  при  этом  метод  должен  сместить  все  элементы
    // массива  на  n  позиций.  Для  усложнения  задачи  нельзя  пользоваться  вспомогательными
    // массивами.
    public static void offsetArray(int[] arrayInt, int offset) {
        int sign = -1 * offset / Math.abs(offset);
        System.out.println(Arrays.toString(arrayInt));
        int length = arrayInt.length;

        for (int i = 0; i < length; i++) {
            if (i >= (length + sign * offset)) {
                arrayInt[0 + sign * i - 1] = 0;
            } else if ((i + offset) >= 0) {
                arrayInt[i + offset] = arrayInt[i];
            }
        }

        if (offset < 0) {
            for (int i = 0; i < length; i++) {
                if (i >= (length + offset)) {
                    arrayInt[i] = 0;
                } else if ((i + offset) >= 0) {
                    arrayInt[i + offset] = arrayInt[i];
                }
            }
        } else {
            for (int i = 0; i < length; i++) {
                if (i >= (length - offset)) { // (arrayInt.length - (i + 1)
                    arrayInt[length - i - 1] = 0;
                } else if (i >= (offset - 2)) {
                    arrayInt[length - i - 3 + offset] = arrayInt[length - i - 3];
                }
            }
        }
        System.out.println(Arrays.toString(arrayInt));
    }

    public static void exercise7() {
        int[] arrayInt = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        offsetArray(arrayInt, 2);
    }

    public static void main(String[] args) {
//        exercise1();
//        exercise2();
//        exercise3();
//        exercise4();
//        exercise5();
//        exercise6();
        exercise7();
    }
}
