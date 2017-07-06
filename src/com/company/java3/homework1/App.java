package com.company.java3.homework1;

import java.util.ArrayList;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        // Задание 1
        System.out.println("-- Task 1");
        System.out.println("---- Array of Integer");
        Integer[] arrayOfInteger = {1, 2, 3, 4, 5, 6};
        System.out.println(Arrays.toString(arrayOfInteger));
        swap(arrayOfInteger, 0, 5);
        System.out.println(Arrays.toString(arrayOfInteger));
        System.out.println("---- Array of String");
        String[] arrayOfString = {"One", "Two", "Three", "Four", "Five", "Six"};
        System.out.println(Arrays.toString(arrayOfString));
        swap(arrayOfString, 0, 5);
        System.out.println(Arrays.toString(arrayOfString));
        // Задание 2
        System.out.println("-- Task 2");
        System.out.println("---- ArrayList of Integer");
        System.out.println(convertToArrayList(arrayOfInteger));
        System.out.println("---- ArrayList of String");
        System.out.println(convertToArrayList(arrayOfString));
        // Задание 3
        System.out.println("-- Task 3");
        Box<Orange> boxOrange = new Box<>();
        boxOrange.addFruit(new Orange());
        boxOrange.addFruit(new Orange());
        Box<Apple> boxApple = new Box<>();
        boxApple.addFruit(new Apple());
        boxApple.addFruit(new Apple());
        Box<Apple> boxApple2 = new Box<>();
        boxApple2.addFruit(new Apple());
        boxApple2.addFruit(new Apple());
        Box.pourFruits(boxApple, boxApple2);
        System.out.println(boxApple);
        System.out.println(boxApple2);
    }

    /**
     * 1. Написать метод, который меняет два элемента массива местами. (массив может быть любого ссылочного типа).
     * @param array
     * @param elemOne
     * @param elemTwo
     * @param <T>
     */
    public static <T> void swap(T[] array, int elemOne, int elemTwo) {
        if (array == null || array.length == 0) {
            System.err.println("Array is empty!");
        } else if (elemOne < 0 || elemOne > array.length - 1
                    || elemTwo < 0 || elemTwo > array.length - 1) {
            System.err.println("One of the elements is incorrect!!");
        } else {
            T temp = array[elemOne];
            array[elemOne] = array[elemTwo];
            array[elemTwo] = temp;
        }
    }

    /**
     * 2. Написать метод, который преобразует массив в ArrayList.
     * @param array
     * @param <T>
     * @return
     */
    public static <T> ArrayList<T> convertToArrayList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }

}
