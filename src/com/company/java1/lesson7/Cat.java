package com.company.java1.lesson7;

public class Cat {
    private String name;
    private int appetite;
    private boolean satiety = false;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public boolean eat(Bowl bowl) {
        if (!satiety) {
            if (bowl.decreaseFood(appetite)) {
                System.out.println(name + ": " + "я наелся!");
            } else {
                System.out.println(name + ": " + "мне этого мало!");
                return false;
            }
        } else {
            System.out.println(name + ": " + "я не хочу есть!");
        }
        return true;
    }

    public boolean isSatiety() {
        return satiety;
    }

    @Override
    public String toString() {
        return "Кот{" +
                "Имя='" + name + '\'' +
                ", Аппетит=" + appetite +
                ", Сытость=" + satiety +
                '}';
    }
}
