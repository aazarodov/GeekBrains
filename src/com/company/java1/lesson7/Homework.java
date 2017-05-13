package com.company.java1.lesson7;

public class Homework {
    public static void main(String[] args) {
        Bowl bowl = new Bowl((int) (Math.random() * 500));
        System.out.println(bowl);
        Cat[] arrayOfCat = new Cat[5];
        arrayOfCat[0] = new Cat("Kuzya", (int) (Math.random() * 100));
        arrayOfCat[1] = new Cat("Barsik", (int) (Math.random() * 100));
        arrayOfCat[2] = new Cat("Vaska", (int) (Math.random() * 100));
        arrayOfCat[3] = new Cat("Tima", (int) (Math.random() * 100));
        arrayOfCat[4] = new Cat("Matroskin", (int) (Math.random() * 100));

        for (int i = 0; i < arrayOfCat.length; i++) {
            arrayOfCat[i].eat(bowl);
        }
        System.out.println(bowl);
    }
}
