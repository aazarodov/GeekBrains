package com.company.java1.test;

import java.util.Random;
import java.util.Scanner;

/** Консольная игра "Крестики-нолики"
 * @author Зародов Алексей
 * @version 1.0
 */
public class AgentAI {
    /** Размер карты */
    private int size;
    /** Символ ячейки, доступной для хода */
    public static final char DOT_EMPTY = '•';
    /** Символ "крестик" */
    public static final char DOT_X = 'X';
    /** Символ "нолик" */
    public static final char DOT_O = 'O';
    // Данные игры
    private char humanSymb, aiSymb;
    private boolean stepHuman = false;
    private int countEmpty;
    private int[][][] arrayPossibleCell;
    // Вспомогательные данные
    public static Scanner sc = new Scanner(System.in);
    public static Random random = new Random();

    public AgentAI(int size, char aiSymb) {
        this.size = size;
        this.aiSymb = aiSymb;
        this.countEmpty = size * size;
    }

    /** Метод, отвечающий за проверку ячейки на возможность установки символа
     * @param x координата ячейки
     * @param y координата ячейки
     * @return возвращает true, если возможно установить символ, иначе false
     */
    public static boolean isCellValid(char[][] map, int x, int y) {
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
    public static boolean isWin(char[][] map, int x, int y, int dX, int dY, char symb) {
        // если достигли конца последовательности
        if (x == SIZE || y == SIZE) {
            return true;
        } else {
            boolean nextDot = isWin(map, (x + dX), (y + dY), dX, dY, symb);
            return (map[y][x] == symb) && nextDot;
        }
    }

    /** Метод, отвечающий за проверку последовательностей символов на победную ситуацию
     * @param symb проверяемый символ
     * @return возвращает true, если есть победная ситуация, иначе false
     */
    public static boolean checkWin(char[][] map, char symb) {
        boolean win = false;
        // проверим строки и столбцы на победную ситуацию
        for (int i = 0; !win && i < SIZE ; i++) {
            win = win || isWin(map, i, 0, 0, 1, symb)
                    || isWin(map, 0, i, 1, 0, symb);
        }
        // проверим диагонали на победную ситуацию
        win = win || isWin(map, 0, 0, 1, 1, symb)
                || isWin(map, SIZE - 1, 0, -1, 1, symb);
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

    /** Метод, отвечающий за выбор компьютером одного из углов
     * @param cell массив для координат X, Y
     */
    public static boolean aiTrySelectAngle(int[] cell) {
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
            int index = random.nextInt(length);
            cell[0] = arrayCell[index][0];
            cell[1] = arrayCell[index][1];
            return true;
        }
        return false;
    }

    /** Метод, отвечающий за предотвращении победы противника
     * @param x координата ячейки
     * @param y координата ячейки
     * @param dX приращение координаты x
     * @param dY приращение координаты y
     * @param count количество символов противника в последовательности
     * @param cell массив для координат X, Y
     * @return возвращает количество символов противника в последовательности (-1 - последовательность не рассматривается)
     */
    public static int aiCheckWinHuman(int x, int y, int dX, int dY, int count, int[] cell) {
        // если не достигли конца последовательности
        if (!(x == SIZE || y == SIZE)) {
            if (map[y][x] == humanSymb || map[y][x] == DOT_EMPTY) {
                if (map[y][x] == humanSymb) {
                    count++;
                }
                count = aiCheckWinHuman((x + dX), (y + dY), dX, dY, count, cell);
                if (count == (SIZE - 1)) {
                    if (map[y][x] == DOT_EMPTY) {
                        cell[0] = x;
                        cell[1] = y;
                    }
                }
            } else {
                return -1;
            }
        }
        return count;
    }

    /** Метод, отвечающий за выбор следующей ячейки в последовательности
     * @param x координата ячейки
     * @param y координата ячейки
     * @param dX приращение координаты x
     * @param dY приращение координаты y
     * @param count количество своих символов в последовательности
     * @return возвращает количество своих символов в последовательности (-1 - последовательность не рассматривается)
     */
    public static int aiCheckNextCell(int x, int y, int dX, int dY, int count) {
        // если достигли конца последовательности
        if (x == SIZE || y == SIZE) {
            // если массив с количеством символом компьютера еще не инициализирован
            if (arrayPossibleCell[count] == null) {
                // инициализируем массив
                arrayPossibleCell[count] = new int[SIZE * SIZE][2];
                for (int i = 0; i < arrayPossibleCell[count].length; i++) {
                    arrayPossibleCell[count][i][0] = -1;
                    arrayPossibleCell[count][i][1] = -1;
                }
            }
        } else {
            // текущий символ последовательности пустой или компьютера
            if (map[y][x] == aiSymb || map[y][x] == DOT_EMPTY) {
                if (map[y][x] == aiSymb) {
                    count++;
                }
                count = aiCheckNextCell((x + dX), (y + dY), dX, dY, count);
                // если в последовательности не встретились символы человека,
                // добавим текущую ячейку в массив к выбору
                if (count >= 0) {
                    if (map[y][x] == DOT_EMPTY) {
                        for (int i = 0; i < arrayPossibleCell[count].length; i++) {
                            if (arrayPossibleCell[count][i][0] == -1) {
                                arrayPossibleCell[count][i][0] = x;
                                arrayPossibleCell[count][i][1] = y;
                                break;
                            }
                        }
                    }
                }
            } else {
                return -1;
            }
        }
        return count;
    }

    /** Метод, отвечающий за выбор следующей ячейки в последовательности
     * @param cell массив для координат X, Y
     * @return возвращает true или false, в зависимости удалось определить след. ячейку или нет
     */
    public static boolean aiSelectNextCell(int[] cell) {
        boolean stopWinHuman = false;
        // проверим строки и столбцы на победу человека при след. ходе
        for (int i = 0; !stopWinHuman && i < SIZE ; i++) {
            stopWinHuman = aiCheckWinHuman(i, 0, 0, 1, 0, cell) == (SIZE - 1);
            stopWinHuman = stopWinHuman || aiCheckWinHuman(0, i, 1, 0, 0, cell) == (SIZE - 1);
        }
        // проверим диагонали на победу человека при след. ходе
        if (!stopWinHuman) {
            aiCheckWinHuman(0, 0, 1, 1, 0, cell);
        }
        if (!stopWinHuman) {
            aiCheckWinHuman(SIZE - 1, 0, -1, 1, 0, cell);
        }
        // если есть победная последовательности человека в след. ходе,
        // не ищем других ячеек, останавливаемся на найденной
        if (stopWinHuman) {
            return true;
        }
        arrayPossibleCell = new int[SIZE][][];
        // проверим строки и столбцы на победу компьютера при тек. ходе
        for (int i = 0; i < SIZE ; i++) {
            aiCheckNextCell(i, 0, 0, 1, 0);
            aiCheckNextCell(0, i, 1, 0, 0);
        }
        // проверим диагонали на победу компьютера при тек. ходе
        aiCheckNextCell(0, 0, 1, 1, 0);
        aiCheckNextCell(SIZE - 1, 0, -1, 1, 0);
        // если есть ячейки для выбора, то отберем с конца массива
        // т.к. в конце ячейки с бОльшим количеством своих символов в последовательностях
        for (int i = arrayPossibleCell.length - 1; i >= 0; i--) {
            if (arrayPossibleCell[i] != null) {
                int length = 0;
                // найдем количество доступных ячеек
                for (int j = 0; j < arrayPossibleCell[i].length; j++) {
                    if (arrayPossibleCell[i][j][0] > -1) {
                        length++;
                    }
                }
                // если доступные ячейки есть, берем случаную
                if (length > 0) {
                    int index = random.nextInt(length);
                    cell[0] = arrayPossibleCell[i][index][0];
                    cell[1] = arrayPossibleCell[i][index][1];
                    return true;
                }
            }
        }
        // если не удалось найти подходящих ячеек, вернем false
        return false;
    }

    /** Метод, отвечающий за выбор случайной ячейки
     * @rerurn массив координат X, Y
     */
    public static int[] aiSelectRandomCell() {
        int[] cell = new int[2];
        do {
            cell[0] = random.nextInt(SIZE);
            cell[1] = random.nextInt(SIZE);
        } while (!isCellValid(cell[0], cell[1]));
        return cell;
    }

    /** Метод, отвечающий за выбор компьютером ячейки
     * @return массив координат X, Y
     */
    public static int[] aiSelectCell() {
        int[] cell = new int[2];
        // это начало игры
        if (countEmpty == SIZE * SIZE) {
            // игровая карта нечетная, выберем центр в качестве первого хода
            if (SIZE % 2 > 0) {
                cell[0] = SIZE / 2;
                cell[1] = cell[0];
                // игровая карта четная, попробуем выбрать угол, иначе случайную ячейку
            } else {
                if (!aiTrySelectAngle(cell)) {
                    cell = aiSelectRandomCell();
                }
            }
        } else {
            if (!aiSelectNextCell(cell)) {
                cell = aiSelectRandomCell();
            }
        }
        return cell;
    }

    /** Метод, отвечающий за ход компьютера */
    public static void aiTurn() {
        int x, y;
        int[] cell = new int[2];
        cell = aiSelectCell();
        x = cell[0];
        y = cell[1];
        System.out.println("Хожу в (" + (x + 1) + ", " + (y + 1)  + ")");
        map[y][x] = aiSymb;
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
            System.out.println("Крестик или нолик (ответ: " + DOT_X + " или " + DOT_O + " - англ. буквы)?");
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
        // инициализируем
        initMap();
        // попросим пользователя выбрать себе символ
        selectSymbolUser();
        // отрисуем карту
        printMap();
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
