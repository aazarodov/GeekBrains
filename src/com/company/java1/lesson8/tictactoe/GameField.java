package com.company.java1.lesson8.tictactoe;

//import com.company.java1.test.AgentAI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GameField extends JPanel {
    public int size;
    /** игровая карта */
    private static char[][] map;
    /** агент искусственного интеллекта */
    //private static AgentAI agentAI;
    /** Символ ячейки, доступной для хода */
    public static final char DOT_EMPTY = '•';
    /** кэш элементов с их координатами */
    private Map<JLabel, Integer[]> cacheOfLable = new HashMap<JLabel, Integer[]>();

    public GameField() {

    }

    /** Метод, отвечающий за инициализациу игрового поля символами DOT_EMPTY */
    public void initMap(int size) {
        this.size = size;
        map = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    /** Метод, отвечающий за отрисовку игрового поля */
    public static void printMap(int size) {
        for (int i = 0; i <= size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < size; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void init(int size) {
        initMap(size);
        setBackground(Color.WHITE);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        for (int i = 0; i < size; i++) {
            JPanel panelRow = new JPanel();
            panelRow.setLayout(new BoxLayout(panelRow, BoxLayout.X_AXIS));
            panelRow.setBackground(Color.WHITE);
            for (int j = 0; j < size; j++) {
                JPanel jPanel = new JPanel(new BorderLayout());
                jPanel.setBorder(blackline);
                JLabel jLabel = new JLabel("X", SwingConstants.CENTER);
                jLabel.setFont(new Font("TimesRoman", Font.BOLD, 14));
                jPanel.add(jLabel, BorderLayout.CENTER);
                jLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        int x = 1;
                        System.out.println(Arrays.toString(cacheOfLable.get(e.getComponent())));

                    }
                });
                panelRow.add(jPanel, BorderLayout.WEST);
                Integer[] xy = {i, j};
                cacheOfLable.put(jLabel, xy);
            }
            add(panelRow);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
//        g.setColor(Color.WHITE);
//        width = getWidth();
//        height = getHeight();
//        g.fillRect(0, 0, width, height);
//        g.setColor(Color.BLACK);
//        cellWidth = width/SIZE;
//        cellHeight = height/SIZE;
//        for (int i = 0; i < SIZE + 1; i++) {
//            g.drawLine(0, i * cellHeight, width, i * cellHeight);
//            g.drawLine(i * cellWidth, 0, i * cellWidth, height);
//        }
//        for (int i = 0; i < SIZE; i++) {
//            for (int j = 0; j < SIZE; j++) {
//                if (map[i][j] == 'X') {
//                    g.setColor(Color.RED);
//                    g.fillOval(i * cellWidth + 10, j * cellHeight + 10, cellWidth - 20, cellHeight - 20);
//                }
//            }
//        }
    }
}
