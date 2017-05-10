package com.company.java1.lesson6;

public class Dog extends Animal {
    Dog(String name) {
        super(name, 500, 10, 0.5);
    }

    public Dog(String name, double max_run, double max_swim, double max_jump) {
        super(name, max_run, max_swim, max_jump);
    }

    @Override
    public String toString() {
        return "Cat" + super.toString();
    }
}
