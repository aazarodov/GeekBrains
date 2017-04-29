package com.company.java1.lesson3;

import java.util.Arrays;

/**
 * Created by Baloo on 29.04.2017.
 */
public class Lesson3 {
    public static void fillArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length - i - 1; j++) {
                array[i][j] = 1;
                System.out.print(array[i][j]);
                System.out.print(" ");
            }
            for (int j = array[i].length - i - 1; j < array[i].length; j++) {
                array[i][j] = 2;
                System.out.print(array[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void drawFlag(String[][] array) {
        for (int i = 0; i < array.length / 2; i++) {
            for (int j = 0; j < i + 1; j++) {
                array[i][j] = "b";
            }
            for (int j = i + 1; j < array[i].length; j++) {
                array[i][j] = "w";
            }
        }
        for (int i = array.length / 2; i < array.length; i++) {
            for (int j = 0; j < (array.length / 2) - (i - array.length / 2); j++) {
                array[i][j] = "b";
            }
            for (int j = (array.length / 2) - (i - array.length / 2); j < array[i].length; j++) {
                array[i][j] = "r";
            }

        }

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String[][] array = new String[8][11];
        //fillArray(array);
        drawFlag(array);
    }
}
