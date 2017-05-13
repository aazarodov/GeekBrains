package com.company.java1.lesson6;

public class Homework {
    public static void main(String[] args) {
        Cat barsik = new Cat("Barsik");
        System.out.println(barsik);
        barsik.run(150);
        barsik.swim(100);
        barsik.jump(2);

        Cat vaska = new Cat("Vaska");
        System.out.println(vaska);
        vaska.run(300);
        vaska.swim(1);
        vaska.jump(3);
        
        Dog jack = new Dog("Jack", 400, 10, 0.5);
        System.out.println(jack);
        jack.run(300);
        jack.run(500);
        jack.swim(10);
        jack.swim(11);
        jack.jump(0.5);
        jack.jump(0.6);
    }
}
