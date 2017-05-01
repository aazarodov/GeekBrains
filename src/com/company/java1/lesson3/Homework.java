package com.company.java1.lesson3;

import java.util.Random;
import java.util.Scanner;

public class Homework {
    // 1. Написать программу, которая загадывает случайное число от 0 до 9, и пользователю дается 3
    // попытки угадать это число. При каждой попытке компьютер должен сообщить больше ли
    // указанное пользователем число чем загаданное, или меньше. После победы или проигрыша
    // выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).
    public static void  exercise1() {
        int theNumber, answer = 0, continueGame = 1;
        Scanner sc = new Scanner(System.in);
        while (continueGame == 1) {
            theNumber = new Random().nextInt(10);
            System.out.println("Угадай число!");
            for (int i = 0; i < 3; i++) {
                answer = sc.nextInt();
                if (answer > theNumber) {
                    System.out.println("Загаданное число больше.");
                } else if (answer < theNumber) {
                    System.out.println("Загаданное число меньше.");
                } else {
                    System.out.println("Поздравляем! Вы угадали число.");
                    break;
                }
            }
            if (answer != theNumber) {
                System.out.println("Вы проиграли!");
            }
            System.out.println("Повторить игру еще раз? (1 – да / 0 – нет)!");
            continueGame = sc.nextInt();
        }
    }

    // 2 * Создать массив из слов
    // String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
    //  "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea",
    //  "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
    // При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя,
    // сравнивает его с загаданным словом и сообщает правильно ли ответил пользователь. Если
    // слово не угадано, компьютер показывает буквы которые стоят на своих местах.
    // apple – загаданное
    // apricot - ответ игрока
    // ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
    // Для сравнения двух слов посимвольно, можно пользоваться:
    // String str = "apple";
    // str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции
    // Играем до тех пор, пока игрок не отгадает слово
    // Используем только маленькие буквы
    public static void  exercise2() {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli",
                            "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango",
                            "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple",
                            "pumpkin", "potato"};
        String theWord = words[new Random().nextInt(words.length)];
        Scanner sc = new Scanner(System.in);
        System.out.println("Угадай слово!");
        String answer = "", hint = "###############";
        boolean win = false;
        while (!win) {
            answer = sc.nextLine();
            if (answer.equals(theWord)) {
                System.out.println("Поздравляем! Вы угадали слово.");
                win = true;
            } else {
                for (int i = 0; i < Math.min(answer.length(), theWord.length()); i++) {
                    if (answer.charAt(i) == theWord.charAt(i)) {
                        hint = hint.substring(0, i) + answer.charAt(i) + hint.substring(i + 1);
                    }
                }
                System.out.println(hint);
            }
        }
    }

    public static void main(String[] args) {
        exercise2();
    }
}
