package com.company.java1.lesson7;

public class Bowl {
    private int food;

    public Bowl(int food) {
        this.food = food;
    }

    public boolean decreaseFood(int amountFood) {
        if (food >= amountFood) {
            food -= amountFood;
            return true;
        } else {
            return false;
        }
    }

    public void addFood(int amountFood) {
        food += amountFood;
    }

    @Override
    public String toString() {
        return "Миска{" +
                "Еды в миске=" + food +
                '}';
    }
}
