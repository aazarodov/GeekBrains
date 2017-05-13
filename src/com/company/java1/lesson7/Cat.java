package com.company.java1.lesson7;

public class Cat {
    private String name;
    private int appetite;
    private boolean satiety = false;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public void eat(Bowl bowl) {
        if (!satiety) {
            satiety = bowl.decreaseFood(appetite);
        }
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Кот{" +
                "Имя='" + name + '\'' +
                ", Аппетит=" + appetite +
                ", " + ((satiety) ? "Я сыт!" : "Я голоден!") +
                '}';
    }
}
