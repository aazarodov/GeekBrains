package com.company.java3.homework5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private CountDownLatch countCarsReady;
    private CountDownLatch countCarsFinished;
    private CyclicBarrier startRace;

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed,
                    CountDownLatch countCarsReady,
                    CountDownLatch countCarsFinished,
                    CyclicBarrier startRace) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.countCarsReady = countCarsReady;
        this.countCarsFinished = countCarsFinished;
        this.startRace = startRace;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            // автомобиль готов, уменьшим счетчик ожидания автомобилей неготовых к гонке
            countCarsReady.countDown();
            // укажем барьеру, что еще один автомобиль готов
            startRace.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        // установим победителя
        race.setWinCar(this);
        // автомобиль финишировал, уменшим счетчик ожидания автомобилей на финише
        countCarsFinished.countDown();
    }
}

