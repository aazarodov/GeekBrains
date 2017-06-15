package com.company.java2.lesson1;

import java.util.ArrayList;

public class Player {
    private String name;
    private byte power;

    private ArrayList<Barrier> arrayListOfBarrier = new ArrayList<Barrier>();

    public Player(String name, byte power) {
        this.name = name;
        this.power = power;
    }

    public void doIt() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getPower() {
        return power;
    }

    public void setPower(byte power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                '}';
    }

    public void addBarrierToArrayList(Barrier barrier) {
        this.arrayListOfBarrier.add(barrier);
    }
}
