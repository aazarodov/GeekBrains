package com.company.java1.lesson1;

public class Homework {
    public static void main(String[] args) {
        // 2. Создать переменные всех пройденных типов данных, и инициализировать их значения;
        byte b = 127;
        short s = 32767;
        int i = 2147483647;
        long l = 9223372036854775807L;
        float f = Float.MAX_VALUE;
        double d = 0.01;
        boolean boo = true;
        char c = 'c';
        String str = "Java";

        System.out.println("1 * (2 + (3 / 4) = " + calc(1, 2, 3, 4));
        System.out.println("1.0 * (2.0 + (3.0 / 4.0) = " + calc(1.0, 2.0, 3.0, 3.0));
        System.out.println("10 <= (8 + 12) <= 20 : " + checkSumInRange10To20(8, 12));
        System.out.println("10 <= (8 + 12) <= 1 : " + checkSumInRange10To20(8, 1));
        checkNumberPositive(1);
        checkNumberPositive(-1);
        System.out.println("1 < 0 : " + checkNumberNegative(1));
        System.out.println("-1 < 0 : " + checkNumberNegative(-1));

        printGreeting("Alexey");

        System.out.println("Год 2019 високосный? Ответ: " + checkLeapYear(2019));
        System.out.println("Год 2020 високосный? Ответ: " + checkLeapYear(2020));
    }

    // 3. Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат,
    // где a, b, c, d – входные параметры этого метода;
    public static int calc(int a, int b, int c, int d) {
       return a * (b + (c / d));
    }

    public static double calc(double a, double b, double c, double d) {
        return a * (b + (c / d));
    }

    // 4. Написать метод, принимающий на вход два числа,
    // и проверяющий что их сумма лежит в пределах от 10 до 20(включительно),
    // если да – вернуть true, в противном случае – false;
    public static boolean checkSumInRange10To20(int a, int b) {
        int sum = a + b;
        return (sum >= 10 && sum <= 20);
    }

    // 5. Написать метод, которому в качестве параметра передается целое число,
    // метод должен напечатать в консоль положительное ли число передали,
    // или отрицательное; Замечание: ноль считаем положительным числом.
    public static void checkNumberPositive(int a) {
        if (a < 0)
            System.out.println("Число " + a + " отрицательное.");
        else
            System.out.println("Число " + a + " положительное.");
    }

    // 6. Написать метод, которому в качестве параметра передается целое число,
    // метод должен вернуть true, если число отрицательное;
    public static boolean checkNumberNegative(int a) {
        return (a < 0);
    }

    // 7. Написать метод, которому в качестве параметра передается строка,
    // обозначающая имя, метод должен вывести в консоль сообщение «Привет, указанное_имя!»;
    public static void printGreeting(String name) {
        System.out.println("Привет, " + name + "!");
    }

    // 8. * Написать метод, который определяет является ли год високосным,
    // и выводит сообщение в консоль. Каждый 4-й год является високосным,
    // кроме каждого 100-го, при этом каждый 400-й – високосный.
    public static boolean checkLeapYear(int year) {
        return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
    }
}
