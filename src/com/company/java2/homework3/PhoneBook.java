package com.company.java2.homework3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PhoneBook {
    Map<String, ArrayList<String>> mapPhoneBook;
    public PhoneBook() {
        mapPhoneBook = new HashMap<String, ArrayList<String>>();
    }

    /**
     * Метод добавляет номер телефона по фамилии
     * @param surname
     * @param phone
     */
    public void add(String surname, String phone) {
        if (mapPhoneBook.containsKey(surname)) {
            if (!mapPhoneBook.get(surname).contains(phone)) {
                mapPhoneBook.get(surname).add(phone);
            }
        } else {
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add(phone);
            mapPhoneBook.put(surname, arrayList);
        }
    }

    /**
     * Метод выводи номера телефонов по фамилии
     * @param surname
     */
    public void get(String surname) {
        if (mapPhoneBook.containsKey(surname)) {
            System.out.println(surname + ":");
            for(String phone : mapPhoneBook.get(surname)) {
                System.out.println(phone);
            }
        } else {
            System.out.println("'" + surname + "' в книге не найден!");
        }
    }
}
