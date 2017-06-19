package com.company.java2.homework2;

public class App {
    public final static int LENGTH = 4;

    public static void main(String[] args) {
        String str = "1 3 1 2\n2 3 2 2\n5 6 7 1\n3 3 1 0";
        // преобразуем строку к массиву 4x4
        String[][] arrayOfString = stringToArray(str);
        int sum = 0;
        try {
            // посчитаем сумму значений ячеек массива
            sum = sumOfCellsArray(arrayOfString);
        } catch (MySizeArrayException e) {
            System.err.println(e.getMessage());
        } catch (MyArrayDataException e) {
            System.err.println(e.getMessage());
        } finally {
            System.out.println("Результат расчета: " + sum);
        }
    }

    /**
     * Функция преобразует строку в массив 4x4
     * @param str строка для разложения
     * @return - массив 4x4
     */
    public static String[][] stringToArray(String str) {
        String[] array1 = str.split("\n");
        String[][] arrayOfString = new String[array1.length][];
        for (int i = 0; i < array1.length; i++) {
            arrayOfString[i] = array1[i].split(" ");
        }
        return arrayOfString;
    }

    /**
     * Функция пытается просуммировать значение в массиве 4x4
     * @param arrayOfString
     * @return сумма массива
     */
    public static int sumOfCellsArray(String[][] arrayOfString) {
        int sum = 0;
        // если длина внешнего массива отличается, тогда исключение
        if (arrayOfString.length != LENGTH) {
            throw new MySizeArrayException("Размер массива не соответствует условиям задачи!");
        } else {
            // обход внешнего массива
            for (int i = 0; i < LENGTH; i++) {
                // если длина вложенного массива отличается, тогда исключение
                if (arrayOfString[i].length != LENGTH) {
                    throw new MySizeArrayException("Размер массива не соответствует условиям задачи!");
                } else {
                    // обход вложенного массива
                    for (int j = 0; j < arrayOfString[i].length; j++) {
                        // пытаемся преобразовать значение в яейке в число
                        try {
                            sum += Integer.parseInt(arrayOfString[i][j]);
                        } catch (NumberFormatException e) {
                            throw new MyArrayDataException("Не удалось преобразовать значение к числу в ячейке {" + i + ", " + j + "}!");
                        }
                    }
                }
            }
        }
        return sum;
    }
}
