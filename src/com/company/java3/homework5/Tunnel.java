package com.company.java3.homework5;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    // семафор, пропускающий определенное количество автомобилей
    private final Semaphore SEMAPHORE;

    public Tunnel(int throughputTunnel) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        // инициализируем семафор
        // тут можно два варианта инициализации, все зависит от того, как мы представляем туннель
        // 1. Если количество полос к туннелю равно его пропускной способности.
        this.SEMAPHORE = new Semaphore(throughputTunnel);
        // 2. Если полоса одна, то инициализируем с параметром fair = true.
        // В данном случае, автомобили в туннель будут заезжать в порядке очереди.
        // this.SEMAPHORE = new Semaphore(throughputTunnel, true);
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                this.SEMAPHORE.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                this.SEMAPHORE.release();
                System.out.println(c.getName() + " закончил этап: " + description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
