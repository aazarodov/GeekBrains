package com.company.java1.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by aazar on 15.05.2017.
 */
public class GameField extends JPanel {
    private int height;
    private int width;

    public final int SIZE = 3;
    private int cellWidth;
    private int cellHeight;
    private char[][] map;

    public GameField() {
        map = new char[SIZE][SIZE];
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println(e.getX() + " " + e.getY());
                int clX = e.getX() / cellWidth;
                int clY = e.getY() / cellHeight;
                System.out.println(clX + " " + clY);
                map[clX][clY] = 'X';
                repaint();
            }
        });

    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        width = getWidth();
        height = getHeight();
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);
        cellWidth = width/SIZE;
        cellHeight = height/SIZE;
        for (int i = 0; i < SIZE + 1; i++) {
            g.drawLine(0, i * cellHeight, width, i * cellHeight);
            g.drawLine(i * cellWidth, 0, i * cellWidth, height);
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == 'X') {
                    g.setColor(Color.RED);
                    g.fillOval(i * cellWidth + 10, j * cellHeight + 10, cellWidth - 20, cellHeight - 20);
                }
            }
        }
    }
}
