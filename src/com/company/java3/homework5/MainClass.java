package com.company.java3.homework5;

// Домашнее задание
// Организуем гонки
// Есть набор правил:
// Все участники должны стартовать одновременно, несмотря на то что на подготовку у каждого уходит разное время
// В туннель не может заехать одновременно больше половины участников(условность)
// Попробуйте все это синхронизировать
// Как только первая машина пересекает финиш, необходимо ее объявить победителем(победитель
// должен быть только один, и вообще должен быть)
// Только после того как все завершат гонку нужно выдать объявление об окончании
// Можете корректировать классы(в т.ч. конструктор машин)
// и добавлять объекты классов из пакета util.concurrent

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class MainClass {
    public static final int CARS_COUNT = 4;
    // счетчик автомобилей, неготовых к гонке
    private static final CountDownLatch COUNT_CARS_READY = new CountDownLatch(CARS_COUNT);
    // счетчик автомобилей, незакончивших гонку
    private static final CountDownLatch COUNT_CARS_FINISHED = new CountDownLatch(CARS_COUNT);
    // барьер, после которого начинается гонка (барьер ждет все автомобили, а также основной поток,
    // чтобы вывести сообщение о начале гонки)
    private static final CyclicBarrier START_RACE = new CyclicBarrier(CARS_COUNT + 1);

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(CARS_COUNT / 2), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race,
                                20 + (int) (Math.random() * 10),
                                COUNT_CARS_READY,
                                COUNT_CARS_FINISHED,
                                START_RACE);
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        try {
            // ждем пока все автомобили подготовятся к гонке
            COUNT_CARS_READY.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            // снимаем барьем (все автомобили готовы, значит барьер ждет только основной поток)
            START_RACE.await();
            // ждем пока все автомобили не финишируют
            COUNT_CARS_FINISHED.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}