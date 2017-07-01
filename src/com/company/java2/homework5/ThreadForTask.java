package com.company.java2.homework5;

public class ThreadForTask extends Thread {

    private float[] arrayOfFloat;

    public ThreadForTask(float[] arrayOfFloat) {
        this.arrayOfFloat = arrayOfFloat;
    }

    public static void fillArray(float[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    @Override
    public void run() {
        fillArray(arrayOfFloat);
    }
}
