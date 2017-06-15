package com.company.java2.homework1;

public class Course {
    public String name;
    private Barrier[] arrayOfBarriers;

    public Course(String name) {
        this.name = name;
        int count = (int) (Math.random() * 20);
        arrayOfBarriers = new Barrier[count];
        for (int i = 0; i < arrayOfBarriers.length; i++) {
            arrayOfBarriers[i] = new Barrier("Barrier #" + (i + 1), (byte)(Math.random() * 80));
        }
    }

    @Override
    public String toString() {
        return "Полоса препятствий " + name;
    }

    public void doIt(Team team) {
        team.addCourseToListOfAchievements(this);
        boolean isFinish;
        // перебор участников команды
        for(Player player : team.getArrayOfPlayers()) {
            isFinish = true;
            // каждый участник проходит препятствия
            for(Barrier barrier : arrayOfBarriers) {
                // если участник не способен пройти препятствие,
                // значит он не может пройти всю полосу
                if (barrier.getPower() > player.getPower()) {
                    isFinish = false;
                    break;
                }
            }
            // если полоса пройдена, добавим ее в список достижений
            if (isFinish) {
                player.addCourseToListOfAchievements(this);
            }
        }
    }
}
