package com.company.java2.homework1;

import java.util.ArrayList;

public class Team {
    private String name;
    private Player[] arrayOfPlayers = new Player[4];
    private ArrayList<Course> listOfAchievements = new ArrayList<Course>();

    public Team(String name) {
        this.name = name;
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
        System.out.println(this);
        if (listOfAchievements.size() > 0) {
            System.out.println("Принимала участие");
            for (Course course : listOfAchievements) {
                System.out.println(course);
                System.out.println("Финишировали участники");
                boolean thereAreFinishers = false;
                for (Player player : arrayOfPlayers) {
                    if (player.isFinishCourse(course)) {
                        System.out.println(player);
                        thereAreFinishers = true;
                    }
                }
                if (!thereAreFinishers) {
                    System.out.println("Отсутствуют");
                }
            }
        }
        System.out.println("----------------------------");
    }

    public void addCourseToListOfAchievements(Course course) {
        this.listOfAchievements.add(course);
    }

    @Override
    public String toString() {
        return "Команда " + name ;
    }
}
