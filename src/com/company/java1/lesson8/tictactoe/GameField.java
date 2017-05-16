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
    private String textFin = "";
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

    public void aiTurn() {
        int[] cell = agentAI.aiTurn(map);
        for (Map.Entry entry : cacheOfLable.entrySet()) {
            Integer[] value = (Integer[]) entry.getValue();
            if (value[0] == cell[0] && value[1] == cell[1]) {
                ((GameCell)entry.getKey()).setSymbol(agentAI.getAiSymb());
                repaint();
                break;
            }
        }
    }

    public void init(int size, char humanSymb) {
        initMap(size);
        this.humanSymb = humanSymb;
        setBackground(Color.WHITE);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        // строки игрового поля
        for (int i = 0; i < size; i++) {
            JPanel panelRow = new JPanel();
            panelRow.setLayout(new BoxLayout(panelRow, BoxLayout.X_AXIS));
            panelRow.setBackground(Color.WHITE);
            // ячейки строки игрового поля
            for (int j = 0; j < size; j++) {
                GameCell jCell = new GameCell();
                jCell.setLayout(new BorderLayout());
                jCell.setBorder(blackline);
                jCell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (!agentAI.isGameOver()) {
                            Component component = e.getComponent();
                            if (component instanceof GameCell) {
                                if (((GameCell) component).getSymbol() == 0) {
                                    int[] cell = new int[2];
                                    cell[0] = cacheOfLable.get(((GameCell) component))[0];
                                    cell[1] = cacheOfLable.get(((GameCell) component))[1];
                                    agentAI.humanTurn(map, cell);
                                    ((GameCell) component).setSymbol(humanSymb);
                                    repaint();
                                    if (!agentAI.isGameEnd(map)) {
                                        aiTurn();
                                        agentAI.isGameEnd(map);
                                    }
                                    if (agentAI.isGameOver()) {
                                        textFin = agentAI.getTextFinish();
                                        repaint();
                                    }
                                }
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
            aiTurn();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        //if (!textFin.equals("")) {
            Graphics2D g2 = (Graphics2D) g;
            int fontSize = 20;
            g2.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
            g2.setColor(Color.red);
            int width = getWidth();
            int height = getHeight();
            g2.drawString("TEST", 10, 10);
            repaint();
        //}
    }
}
