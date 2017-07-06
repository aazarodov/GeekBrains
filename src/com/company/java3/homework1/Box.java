package com.company.java3.homework1;

import java.util.ArrayList;

public class Box <T extends Fruit> {
    private ArrayList<T> arrayList = new ArrayList<>();
    protected float weightBox = 0;

    public Box() {

    }

    /**
     * Метод позволяет добавить фрукт в коробку
     * @param elem
     */
    public void addElem(T elem) {
        this.arrayList.add(elem);
        this.weightBox += elem.getWeiht();
    }

    /**
     * Метод позволяет получить вес коробки с фруктами.
     * @return
     */
    public float getWeight() {
        return weightBox;
    }

    /**
     * Метод позволяет сравнивать коробки фруктов по их весам.
     * @param anotherBox
     * @return
     */
    public boolean compare(Box<? extends Fruit> anotherBox) {
        return this.getWeight() == anotherBox.getWeight();
    }

    /**
     * Метод очищает коробку
     */
    public void clear() {
        this.arrayList.clear();
        this.weightBox = 0;
    }

    /**
     * Метод позволяет пересыпать фрукты в текущую коробку.
     * @param source
     */
    public void pourFruit(Box<T> source) {
        this.arrayList.addAll(source.arrayList);
        this.weightBox += source.weightBox;
        source.clear();
    }

    @Override
    public String toString() {
        return "Box{" +
                "arrayList=" + arrayList +
                ", weightBox=" + weightBox +
                '}';
    }
}
