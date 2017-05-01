package com.company.java1.lesson4;

import java.util.Random;
import java.util.Scanner;

public class Homework {
    public static char[][] map;
    public static final int SIZE = 3;
    public static final int DOTS_TO_WIN = 3;

    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';

    public static char humanDot, iaDot;
    public static byte countEmpty = SIZE * SIZE;

    public static Scanner sc = new Scanner(System.in);
    public static Random random = new Random();

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean isWin(int x, int y, int dX, int dY, char symb) {
        if (x == SIZE || y == SIZE) {
            return true;
        } else {
            boolean nextDot = isWin((x + dX), (y + dY), dX, dY, symb);
            return (map[y][x] == symb) && nextDot;
        }
    }

    public static boolean checkWin(char symb) {
        boolean win = false;
        // строки и столбцы
        for (int i = 0; !win && i < SIZE ; i++) {
            win = win || isWin(i, 0, 0, 1, symb)
                        || isWin(0, i, 1, 0, symb);
        }
        // диагонали
        win = win || isWin(0, 0, 1, 1, symb)
                || isWin(SIZE - 1, 0, -1, 1, symb);
        return win;
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }

    public static void aiTurn() {
        int x, y;
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер сходил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
        countEmpty--;
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
        countEmpty--;
    }

    public static void main(String[] args) {
        initMap();
        printMap();
        boolean stepHuman = true;
        while (true) {
            if (stepHuman) {
                humanTurn();
            } else {
                aiTurn();
            }
            printMap();
            if (countEmpty == 0) {
                System.out.println("Ничья");
                break;
            } else if (checkWin(DOT_X)) {
                    System.out.println("Победил человек");
                    break;
            } else if (checkWin(DOT_O)) {
                System.out.println("Победил Искуственный Интеллект");
                break;
            }
            stepHuman = !stepHuman;
        }
    }
}
