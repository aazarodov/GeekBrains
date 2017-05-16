package com.company.java1.lesson8.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameInterface extends JFrame {
    // заголовок
    private static final String APP_TITLE = "Strategic Tic-Tac-Toe";
    // интерфейсные компоненты
    private GameField gameField;
    private JPanel startPanel, jBottomPanel;
    private JTextField tfSize;
    private JButton jbStart, jbNewGame;
    // Данные игры
    private char humanSymb = 'X';
    // Размер карты
    private int size;

    public GameInterface() {

    }

    private void addMenu() {
        jBottomPanel = new JPanel();
        jBottomPanel.setLayout(new GridLayout());
        add(jBottomPanel, BorderLayout.SOUTH);
        // кнопка старта игры
        jbStart = new JButton("Start");
        jbStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                size = Integer.parseInt(tfSize.getText());
                //humanSymb =
                startPanel.setVisible(false);
                // игровая панель
                gameField = new GameField();
                gameField.setLayout(new BoxLayout(gameField, BoxLayout.Y_AXIS));
                gameField.init(size, humanSymb);
                add(gameField, BorderLayout.CENTER);
                jBottomPanel.remove(jbStart);
                jBottomPanel.add(jbNewGame, 0);
                jBottomPanel.repaint();
            }
        });
        jBottomPanel.add(jbStart);
        // кнопка новой игры
        jbNewGame = new JButton("New game");
        jbNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startPanel.setVisible(true);
                gameField.setVisible(false);
                remove(gameField);
                gameField = null;
                jBottomPanel.remove(jbNewGame);
                jBottomPanel.add(jbStart, 0);
                jBottomPanel.repaint();
            }
        });
        // Кнопка выхода
        JButton jbExit = new JButton("Exit");
        jbExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jBottomPanel.add(jbExit);
    }

    private void addStartPanel() {
        startPanel = new JPanel(new BorderLayout());
        // панель выбора размерности игрового поля
        JPanel jpSize = new JPanel();
        JLabel jlSize = new JLabel("Размерность игрового поля");
        tfSize = new JTextField("3", 10);
        tfSize.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char a = e.getKeyChar();
                if (!Character.isDigit(a)){
                    e.consume();
                }
            }
        });
        jpSize.add(jlSize, BorderLayout.WEST);
        jpSize.add(tfSize, BorderLayout.CENTER);
        startPanel.add(jpSize, BorderLayout.NORTH);
        // панель выбора чем играет пользователь
        JPanel jpSymbol = new JPanel();
        JLabel jlSymbol = new JLabel("Чем играем?");
        JCheckBox jcbSymbol = new JCheckBox();
        JRadioButton jcbSymbolX = new JRadioButton("X", true);
        jcbSymbolX.setMnemonic(KeyEvent.VK_X);
        jcbSymbolX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                humanSymb = 'X';
            }
        });
        JRadioButton jcbSymbolO = new JRadioButton("O");
        jcbSymbolO.setMnemonic(KeyEvent.VK_O);
        jcbSymbolO.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                humanSymb = 'O';
            }
        });
        ButtonGroup group = new ButtonGroup();
        group.add(jcbSymbolX);
        group.add(jcbSymbolO);
        jpSymbol.add(jlSymbol, BorderLayout.WEST);
        jpSymbol.add(jcbSymbolX, BorderLayout.CENTER);
        jpSymbol.add(jcbSymbolO, BorderLayout.CENTER);
        startPanel.add(jpSymbol, BorderLayout.CENTER);
        add(startPanel);
    }

    public void init() {
        setTitle(APP_TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 400);
        setResizable(true);

        // меню кнопок
        addMenu();
        // стартовая панель
        addStartPanel();

        setVisible(true);
    }
}
