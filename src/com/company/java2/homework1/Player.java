package com.company.java2.homework1;

import java.util.ArrayList;

public class Player {
    private String name;
    private byte power;

    private ArrayList<Course> listOfAchievements = new ArrayList<Course>();

    public Player(String name, byte power) {
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
        return "Участник " + name;
    }

    public void addCourseToListOfAchievements(Course course) {
        this.listOfAchievements.add(course);
    }

    public boolean isFinishCourse(Course course) {
        return listOfAchievements.contains(course);
    }
}
