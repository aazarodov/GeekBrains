package com.company.java1.lesson8.tictactoe;

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
    private boolean stepHuman;
    private int countEmpty;
    private int[][][] arrayPossibleCell;
    private String textFinish = "";
    private boolean GameOver = false;
    // Вспомогательные данные
    public static Random random = new Random();

    public AgentAI(int size, char humanSymb) {
        this.size = size;
        this.humanSymb = humanSymb;
        if (humanSymb == DOT_X) {
            this.aiSymb = DOT_O;
            this.stepHuman = true;
        } else {
            this.aiSymb = DOT_X;
            this.stepHuman = false;
        }
        this.countEmpty = size * size;
    }

    public boolean isStepHuman() {
        return stepHuman;
    }

    public char getAiSymb() {
        return aiSymb;
    }

    public boolean isGameOver() {
        return GameOver;
    }

    public String getTextFinish() {
        return textFinish;
    }

    /** Метод, отвечающий за проверку ячейки на возможность установки символа
     * @param x координата ячейки
     * @param y координата ячейки
     * @return возвращает true, если возможно установить символ, иначе false
     */
    public boolean isCellValid(char[][] map, int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size) return false;
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
    public boolean isWin(char[][] map, int x, int y, int dX, int dY, char symb) {
        // если достигли конца последовательности
        if (x == size || y == size) {
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
    public boolean checkWin(char[][] map, char symb) {
        boolean win = false;
        // проверим строки и столбцы на победную ситуацию
        for (int i = 0; !win && i < size ; i++) {
            win = win || isWin(map, i, 0, 0, 1, symb)
                    || isWin(map, 0, i, 1, 0, symb);
        }
        // проверим диагонали на победную ситуацию
        win = win || isWin(map, 0, 0, 1, 1, symb)
                || isWin(map, size - 1, 0, -1, 1, symb);
        return win;
    }

    /** Метод, отвечающий за проверку окончания игры
     * @return возвращает true, если игра окончена, иначе false
     */
    public boolean isGameEnd(char[][] map) {
        if (countEmpty == 0) {
            textFinish = "Ничья.";
            GameOver = true;
        } else if (checkWin(map, humanSymb)) {
            textFinish = "Поздравляем! Вы победили.";
            GameOver = true;
        } else if (checkWin(map, aiSymb)) {
            textFinish = "Победил компьютер!";
            GameOver = true;
        }
        return GameOver;
    }

    /** Метод, отвечающий за выбор компьютером одного из углов
     * @param cell массив для координат X, Y
     */
    public boolean aiTrySelectAngle(char[][] map, int[] cell) {
        int[][] arrayCell = new int[4][2];
        int length = 0;
        for (int i = 0; i < size; i = i + (size - 1)) {
            for (int j = 0; j < size; j = j + (size - 1)) {
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
    public int aiCheckWinHuman(char[][] map, int x, int y, int dX, int dY, int count, int[] cell) {
        // если не достигли конца последовательности
        if (!(x == size || y == size)) {
            if (map[y][x] == humanSymb || map[y][x] == DOT_EMPTY) {
                if (map[y][x] == humanSymb) {
                    count++;
                }
                count = aiCheckWinHuman(map, (x + dX), (y + dY), dX, dY, count, cell);
                if (count == (size - 1)) {
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
    public int aiCheckNextCell(char[][] map, int x, int y, int dX, int dY, int count) {
        // если достигли конца последовательности
        if (x == size || y == size) {
            // если массив с количеством символом компьютера еще не инициализирован
            if (arrayPossibleCell[count] == null) {
                // инициализируем массив
                arrayPossibleCell[count] = new int[size * size][2];
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
                count = aiCheckNextCell(map, (x + dX), (y + dY), dX, dY, count);
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
    public boolean aiSelectNextCell(char[][] map, int[] cell) {
        boolean stopWinHuman = false;
        // проверим строки и столбцы на победу человека при след. ходе
        for (int i = 0; !stopWinHuman && i < size ; i++) {
            stopWinHuman = aiCheckWinHuman(map, i, 0, 0, 1, 0, cell) == (size - 1);
            stopWinHuman = stopWinHuman || aiCheckWinHuman(map, 0, i, 1, 0, 0, cell) == (size - 1);
        }
        // проверим диагонали на победу человека при след. ходе
        if (!stopWinHuman) {
            stopWinHuman = aiCheckWinHuman(map, 0, 0, 1, 1, 0, cell) == (size - 1);
        }
        if (!stopWinHuman) {
            stopWinHuman = aiCheckWinHuman(map, size - 1, 0, -1, 1, 0, cell) == (size - 1);
        }
        arrayPossibleCell = new int[size][][];
        // проверим строки и столбцы на победу компьютера при тек. ходе
        for (int i = 0; i < size ; i++) {
            aiCheckNextCell(map, i, 0, 0, 1, 0);
            aiCheckNextCell(map, 0, i, 1, 0, 0);
        }
        // проверим диагонали на победу компьютера при тек. ходе
        aiCheckNextCell(map, 0, 0, 1, 1, 0);
        aiCheckNextCell(map, size - 1, 0, -1, 1, 0);
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
                    // если есть победная комбинация компьютера текущим ходом
                    // или не победная ситуация человека в следующем ходе
                    if (stopWinHuman && (i == arrayPossibleCell.length - 1) || !stopWinHuman) {
                            cell[0] = arrayPossibleCell[i][index][0];
                            cell[1] = arrayPossibleCell[i][index][1];
                    }
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
    public int[] aiSelectRandomCell(char[][] map) {
        int[] cell = new int[2];
        do {
            cell[0] = random.nextInt(size);
            cell[1] = random.nextInt(size);
        } while (!isCellValid(map, cell[0], cell[1]));
        return cell;
    }

    /** Метод, отвечающий за выбор компьютером ячейки
     * @return массив координат X, Y
     */
    public int[] aiSelectCell(char[][] map) {
        int[] cell = new int[2];
        // это начало игры
        if (countEmpty == size * size) {
            // игровая карта нечетная, выберем центр в качестве первого хода
            if (size % 2 > 0) {
                cell[0] = size / 2;
                cell[1] = cell[0];
                // игровая карта четная, попробуем выбрать угол, иначе случайную ячейку
            } else {
                if (!aiTrySelectAngle(map, cell)) {
                    cell = aiSelectRandomCell(map);
                }
            }
        } else {
            if (!aiSelectNextCell(map, cell)) {
                cell = aiSelectRandomCell(map);
            }
        }
        return cell;
    }

    /** Метод, отвечающий за ход компьютера */
    public int[] aiTurn(char[][] map) {
        int x, y;
        int[] cell = aiSelectCell(map);
        x = cell[0];
        y = cell[1];
        System.out.println("Хожу в (" + (x + 1) + ", " + (y + 1)  + ")");
        map[y][x] = aiSymb;
        countEmpty--;
        stepHuman = !stepHuman;
        return  cell;
    }

    /** Метод, отвечающий за ход пользователя */
    public void humanTurn(char[][] map, int[] cell) {
        map[cell[1]][cell[0]] = humanSymb;
        countEmpty--;
    }

}
