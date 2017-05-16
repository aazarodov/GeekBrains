package com.company.java1.lesson8.tictactoe;

import javax.swing.*;
import java.awt.*;

public class GameCell extends JPanel {
    private char symbol;
    private int width, height;
    public GameCell() {

    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
        repaint();
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        width = getWidth();
        height = getHeight();
        if (symbol == 'X') {
            g.drawLine(10, 10, width - 10, height - 10);
            g.drawLine(10, height - 10, width - 10, 10);
        } else if (symbol == 'O') {
            g.drawOval(10, 10, width - 20, height - 20);
        }
    }
}
