package com.company.java2.homework3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        System.out.println("Task #1");
        task1();
        System.out.println("Task #2");
        task2();
    }

    /* 1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
    Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
    Посчитать сколько раз встречается каждое слово.
     */
    public static void task1() {
        ArrayList<String> arrayList = getListOfWord();
        Map<String, Integer> map = new HashMap<String, Integer>();
        Integer count;
        for(String word : arrayList) {
            count = 0;
            if (map.containsKey(word)) {
                count = map.get(word);
            }
            map.put(word, ++count);
        }
        System.out.println(map);
    }

    /* 2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
    В этот телефонный справочник с помощью метода add() можно добавлять записи.
    С помощью метода get() искать номер телефона по фамилии.
    Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
    тогда при запросе такой фамилии должны выводиться все телефоны.
     */
    public static void task2() {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Петров", "89115554466");
        phoneBook.add("Петров", "89217884545");
        phoneBook.add("Иванов", "89217811212");
        phoneBook.add("Сидоров", "89097784512");
        phoneBook.get("Сидоров");
        phoneBook.get("Петров");
        phoneBook.get("Кузьмичев");
    }

    public static ArrayList<String> getListOfWord() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("Ностальгия");
        arrayList.add("Отчаяние");
        arrayList.add("Ностальгия");
        arrayList.add("Азарт");
        arrayList.add("Грусть");
        arrayList.add("Веселье");
        arrayList.add("Равнодушие");
        arrayList.add("Грусть");
        arrayList.add("Азарт");
        arrayList.add("Привязанность");
        arrayList.add("Желание");
        arrayList.add("Счастье");
        arrayList.add("Равнодушие");
        arrayList.add("Благодарность");
        arrayList.add("Искушение");
        arrayList.add("Счастье");
        arrayList.add("Сострадание");
        arrayList.add("Благодарность");
        arrayList.add("Счастье");
        return arrayList;
    }

}
