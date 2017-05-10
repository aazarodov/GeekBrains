package com.company.java1.lesson6;

public class Animal {
    private String name;
    private double max_run;
    private double max_swim;
    private double max_jump;

    Animal(String name) {
        this.name = name;
    }

    public Animal(String name, double max_run, double max_swim, double max_jump) {
        this.name = name;
        this.max_run = max_run;
        this.max_swim = max_swim;
        this.max_jump = max_jump;
    }

    public void run(double distance) {
        System.out.println("run: " + (distance <= max_run));
    }
    public void swim(double distance) {
        System.out.println("swim: " + (distance <= max_swim));
    }
    public void jump(double distance) {
        System.out.println("jump: " + (distance <= max_jump));
    }

    @Override
    public String toString() {
        return "{name='" + name + '\'' +
                ((max_run > 0) ? (", max_run='" + max_run + '\'') : "") +
                ((max_swim > 0) ? (", max_swim='" + max_swim + '\'') : "") +
                ((max_jump > 0) ? (", max_jump='" + max_jump + '\'') : "") +
                '}';
    }

    public void setMax_run(double max_run) {
        this.max_run = max_run;
    }

    public void setMax_swim(double max_swim) {
        this.max_swim = max_swim;
    }

    public void setMax_jump(double max_jump) {
        this.max_jump = max_jump;
    }

    public double getMax_run() {
        return max_run;
    }

    public double getMax_swim() {
        return max_swim;
    }

    public double getMax_jump() {
        return max_jump;
    }
}
