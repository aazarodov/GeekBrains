package com.company.java2.homework1;

public class Barrier {
    private String name;
    private byte power;

    public Barrier(String name, byte power) {
        this.name = name;
        this.power = power;
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
        return "Препятствие ";
    }
}
