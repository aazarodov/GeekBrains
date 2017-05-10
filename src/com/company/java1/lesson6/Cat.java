package com.company.java1.lesson6;

public class Cat extends Animal {

    private final double max_swim = 0;

    Cat(String name) {
        super(name, 200, 0, 2);
    }

    public Cat(String name, double max_run, double max_jump) {
        super(name, max_run, 0, max_jump);
    }

    @Override
    public void swim(double distance) {
        System.out.println("swim: false");
    }

    @Override
    public String toString() {
        return "Cat" + super.toString();
    }

    @Override
    public void setMax_swim(double max_swim) {
        System.out.println("Cat can not swim!");
    }

    @Override
    public double getMax_swim() {
        System.out.println("Cat can not swim!");
        return max_swim;
    }
}
