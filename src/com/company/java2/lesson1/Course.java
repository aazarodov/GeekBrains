package com.company.java2.lesson1;

public class Course {
    private Barrier[] arrayOfBarriers;

    public Course() {
        int count = (int) (Math.random() * 20);
        arrayOfBarriers = new Barrier[count];
        for (int i = 0; i < arrayOfBarriers.length; i++) {
            arrayOfBarriers[i] = new Barrier("Barrier #" + (i + 1), (byte)(Math.random() * 100));
        }
    }

    public void doIt(Team team) {
        for(Barrier barrier : arrayOfBarriers) {
            team.addBarrierToArrayList(barrier);
            for(Player player : team.getArrayOfPlayers()) {
                if (barrier.getPower() <= player.getPower()) {
                    player.addBarrierToArrayList(barrier);
                }
            }
        }
    }
}
