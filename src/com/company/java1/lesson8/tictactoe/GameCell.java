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

    /** Метод отрисовывает крестик или нолик в поле
     * @param g
     */
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        width = getWidth();
        height = getHeight();
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(10.0f));
        if (symbol == AgentAI.DOT_X) {
            g2.drawLine(10, 10, width - 10, height - 10);
            g2.drawLine(10, height - 10, width - 10, 10);
        } else if (symbol == AgentAI.DOT_O) {
            g2.drawOval(10, 10, width - 20, height - 20);
        }
    }
}
