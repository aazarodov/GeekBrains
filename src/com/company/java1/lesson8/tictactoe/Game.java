package com.company.java1.lesson8.tictactoe;

import com.company.java1.lesson8.tictactoe.GameInterface;

public class Game {

    public static void main(String[] args) {
        GameInterface ticTacToe = new GameInterface();
        // стартовый экран игры
        ticTacToe.init();
    }
}
