package com.company.java2.homework1;

public class App {
    public static void main(String[] args) {
        Course course = new Course("Course #1");
        Team team1 = new Team("Team #1");
        Team team2 = new Team("Team #2");
        course.doIt(team1);
        course.doIt(team2);
        team1.showResults();
        team2.showResults();
    }
}
