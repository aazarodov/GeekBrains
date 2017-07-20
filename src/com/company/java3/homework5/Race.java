package com.company.java3.homework5;

import java.util.ArrayList;
import java.util.Arrays;

public class Race {
    private ArrayList<Stage> stages;
    private Car winCar;

    public ArrayList<Stage> getStages() { return stages; }

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public synchronized void setWinCar(Car winCar) {
        if (this.winCar == null) {
            this.winCar = winCar;
            System.out.println(winCar.getName() + " - WIN!");
        }
    }
}