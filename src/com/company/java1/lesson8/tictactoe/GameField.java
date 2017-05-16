package com.company.java1.lesson8.tictactoe;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class GameField extends JPanel {
    // Размер карты
    public int size;
    // Данные игры
    private char humanSymb;
    /** игровая карта */
    private char[][] map;
    /** агент искусственного интеллекта */
    private AgentAI agentAI;
    /** Символ ячейки, доступной для хода */
    public static final char DOT_EMPTY = '•';
    /** кэш элементов с их координатами */
    private Map<GameCell, Integer[]> cacheOfLable = new HashMap<GameCell, Integer[]>();

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

    public void init(int size, char humanSymb) {
        initMap(size);
        this.humanSymb = humanSymb;
        setBackground(Color.WHITE);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        for (int i = 0; i < size; i++) {
            JPanel panelRow = new JPanel();
            panelRow.setLayout(new BoxLayout(panelRow, BoxLayout.X_AXIS));
            panelRow.setBackground(Color.WHITE);
            for (int j = 0; j < size; j++) {
                GameCell jCell = new GameCell();
                jCell.setLayout(new BorderLayout());
                jCell.setBorder(blackline);
                //JLabel jLabel = new JLabel("", SwingConstants.CENTER);
                //jLabel.setFont(new Font("TimesRoman", Font.BOLD, 18));
                //jPanel.add(jLabel, BorderLayout.CENTER);
                jCell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        //cacheOfLable.get(e.getComponent())
                        Component component =  e.getComponent();
                        if (component instanceof GameCell) {
                            if (((GameCell) component).getSymbol() == 0) {
                                ((GameCell) component).setSymbol(humanSymb);
                            }
                        }
                    }
                });
                panelRow.add(jCell, BorderLayout.WEST);
                Integer[] xy = {i, j};
                cacheOfLable.put(jCell, xy);
            }
            add(panelRow);
        }
        // Инициализация агента искуственного интеллекта
        agentAI = new AgentAI(size, humanSymb);
        if (!agentAI.isStepHuman()) {
            int[] cell = agentAI.aiTurn(map);
            for (Map.Entry entry : cacheOfLable.entrySet()) {
                Integer[] value = (Integer[]) entry.getValue();
                if (value[0] == cell[0] && value[1] == cell[1]) {
                    ((GameCell)entry.getKey()).setSymbol(agentAI.getAiSymb());
                    break;
                }
            }
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
