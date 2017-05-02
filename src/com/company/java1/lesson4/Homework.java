package com.company.java1.lesson4;

import java.util.Random;
import java.util.Scanner;

/** Консольная игра "Крестики-нолики"
 * @author Зародов Алексей
 * @version 1.0
 */
public class Homework {
    // данные поля
    public static char[][] map;
    public static final int SIZE = 4;
    public static final int DOTS_TO_WIN = 3;
    // возможные символы
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    // данные игры
    public static char humanSymb, aiSymb;
    public static boolean stepHuman = false;
    public static byte countEmpty = SIZE * SIZE;
    public static int[][][] arrayPossibleCell;
    // вспомогательные данные
    public static Scanner sc = new Scanner(System.in);
    public static Random random = new Random();

    /** Метод, отвечающий за инициализациу игрового поля символами DOT_EMPTY */
    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    /** Метод, отвечающий за отрисовку игрового поля */
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

    /** Метод, отвечающий за проверку ячейки на возможность установки символа
     * @param x координата ячейки
     * @param y координата ячейки
     * @return возвращает true, если возможно установить символ, иначе false
     */
    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }

    /** Метод, отвечающий за рекурсивную проверку последовательности символов на победную ситуацию
     * @param x координата ячейки
     * @param y координата ячейки
     * @param dX приращение координаты x
     * @param dY приращение координаты y
     * @param symb проверяемый символ
     * @return возвращает true, если есть победная ситуация, иначе false
     */
    public static boolean isWin(int x, int y, int dX, int dY, char symb) {
        if (x == SIZE || y == SIZE) {
            return true;
        } else {
            boolean nextDot = isWin((x + dX), (y + dY), dX, dY, symb);
            return (map[y][x] == symb) && nextDot;
        }
    }

    /** Метод, отвечающий за проверку последовательностей символов на победную ситуацию
     * @param symb проверяемый символ
     * @return возвращает true, если есть победная ситуация, иначе false
     */
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

    /** Метод, отвечающий за проверку окончания игры
     * @return возвращает true, если игра окончена, иначе false
     */
    public static boolean isGameEnd() {
        if (countEmpty == 0) {
            System.out.println("Ничья.");
            return true;
        } else if (checkWin(humanSymb)) {
            System.out.println("Поздравляем! Вы победили.");
            return true;
        } else if (checkWin(aiSymb)) {
            System.out.println("Победил компьютер!");
            return true;
        }
        return false;
    }

    /** Метод, отвечающий за выбор компьютером одного из углов */
    public static boolean aiTrySelectAngle(int[] cellXY) {
        int[][] arrayCell = new int[4][2];
        int length = 0;
        for (int i = 0; i < SIZE; i = i + (SIZE - 1)) {
            for (int j = 0; j < SIZE; j = j + (SIZE - 1)) {
                if (map[i][j] == DOT_EMPTY) {
                    arrayCell[length][0] = i;
                    arrayCell[length++][1] = j;
                }
            }
        }
        if (length > 0) {
            int cell = random.nextInt(length);
            cellXY[0] = arrayCell[cell][0];
            cellXY[1] = arrayCell[cell][1];
            return true;
        }
        return false;
    }

    /** Метод, отвечающий за выбор следующей ячейки в последовательности */
    public static boolean aiCheckNextCell(int x, int y, int dX, int dY, int count) {
        if (x == SIZE || y == SIZE) {
            if (arrayPossibleCell[count] == null) {
                arrayPossibleCell[count] = new int[SIZE * SIZE][2];
                for (int i = 0; i < arrayPossibleCell[count].length; i++) {
                    arrayPossibleCell[count][i][0] = -1;
                    arrayPossibleCell[count][i][1] = -1;
                }
            }
            return true;
        } else {
            if (map[y][x] == aiSymb || map[y][x] == DOT_EMPTY) {
                if (map[y][x] == aiSymb) {
                    count++;
                }
                if (aiCheckNextCell((x + dX), (y + dY), dX, dY, count)) {
                    if (map[y][x] == DOT_EMPTY) {
                        for (int i = 0; i < arrayPossibleCell[count].length; i++) {
                            if (arrayPossibleCell[count][i][0] == -1) {
                                arrayPossibleCell[count][i][0] = x;
                                arrayPossibleCell[count][i][1] = y;
                                break;
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /** Метод, отвечающий за выбор следующей ячейки в последовательности */
    public static boolean aiSelectNextCell(int[] cell) {
        arrayPossibleCell = new int[SIZE - 1][][];
        Integer count = 0;
        // строки и столбцы
        for (int i = 0; i < SIZE ; i++) {
            aiCheckNextCell(i, 0, 0, 1, count);
            count = 0;
            aiCheckNextCell(0, i, 1, 0, count);
        }
        // диагонали
        count = 0;
        aiCheckNextCell(0, 0, 1, 1, count);
        count = 0;
        aiCheckNextCell(SIZE - 1, 0, -1, 1, count);

        for (int i = arrayPossibleCell.length - 1; i >= 0; i--) {
            if (arrayPossibleCell[i] != null) {
                int length = 0;
                for (int j = 0; j < arrayPossibleCell[i].length; j++) {
                    if (arrayPossibleCell[i][j][0] > -1) {
                        length++;
                    }
                }
                if (length > 0) {
                    int index = random.nextInt(length);
                    cell[0] = arrayPossibleCell[i][index][0];
                    cell[1] = arrayPossibleCell[i][index][1];
                    break;
                }
            }
        }

        return true;
    }

    /** Метод, отвечающий за выбор случайной ячейки */
    public static boolean aiSelectRandomCell(int[] cell) {
        do {
            cell[0] = random.nextInt(SIZE);
            cell[1] = random.nextInt(SIZE);
        } while (!isCellValid(cell[0], cell[1]));
        return true;
    }

    /** Метод, отвечающий за выбор компьютером ячейки */
    public static boolean aiSelectCell(int[] cell) {
        if (countEmpty == SIZE * SIZE) {
            if (SIZE % 2 > 0) {
                cell[0] = SIZE % 2;
                cell[1] = cell[0];
            } else {
                if (!aiTrySelectAngle(cell)) {
                    aiSelectRandomCell(cell);
                }
            }
        } else if (SIZE % 2 > 0 && map[SIZE % 2][SIZE % 2] == aiSymb) {
            if (!aiTrySelectAngle(cell)) {
                aiSelectRandomCell(cell);
            }
        } else {
            aiSelectNextCell(cell);
        }
        return true;
    }

    /** Метод, отвечающий за ход компьютера */
    public static void aiTurn() {
        int x, y;
        int[] cell = new int[2];
        aiSelectCell(cell);
        x = cell[0];
        y = cell[1];
        System.out.println("Хожу в (" + (x + 1) + ", " + (y + 1)  + ")");
        map[x][y] = aiSymb;
        countEmpty--;
    }

    /** Метод, отвечающий за ход пользователя */
    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = humanSymb;
        countEmpty--;
    }

    /** Метод, отвечающий за выбор пользователем своего символа в игре */
    public static void selectSymbolUser() {
        String answer = "";
        String dot_x = String.valueOf(DOT_X), dot_o = String.valueOf(DOT_O);
        while (!answer.equals(dot_x) && !answer.equals(dot_o)) {
            System.out.println("Крестик или нолик (ответ: X или O - англ. буквы)?");
            answer = sc.nextLine();
        }
        humanSymb = answer.charAt(0);
        if (humanSymb == DOT_O) {
            aiSymb = DOT_X;
        } else {
            aiSymb = DOT_O;
            stepHuman = true;
        }

    }

    /** Точка входа в класс и приложение
     * @param args Массив строковых аргументов
     */
    public static void main(String[] args) {
        // инициализируем и отрисуем карту
        initMap();
        printMap();
        // попросим пользователя выбрать себе символ
        selectSymbolUser();
        // игра
        while (true) {
            if (stepHuman) {
                humanTurn();
            } else {
                aiTurn();
            }
            printMap();
            if (isGameEnd()) {
                break;
            }
            stepHuman = !stepHuman;
        }
    }
}
