package com.company.java2.lesson1;

import java.util.ArrayList;

public class Team {
    private String name;
    private Player[] arrayOfPlayers = new Player[4];
    private ArrayList<Course> arrayListOfCourse = new ArrayList<Course>();

    public Team() {
        for (int i = 0; i < arrayOfPlayers.length; i++) {
            arrayOfPlayers[i] = new Player("Player #" + (i + 1), (byte)(Math.random() * 100));
        }
    }

    public Player[] getArrayOfPlayers() {
        return arrayOfPlayers;
    }

    public void getPlayersInfo() {
        for (Player player : arrayOfPlayers) {
            System.out.println(player);
        }
    }

    public void showResults() {
        for(Course course :  arrayListOfCourse) {
            for (Player player : arrayOfPlayers) {
                System.out.println(player);
            }
        }
    }

    public void addCourseToArrayList(Course course) {
        this.arrayListOfCourse.add(course);
    }
}
