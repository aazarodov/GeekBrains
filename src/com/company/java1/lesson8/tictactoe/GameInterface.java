package com.company.java1.lesson8.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameInterface extends JFrame {
    private static final String APP_TITLE = "Strategic Tic-Tac-Toe";
    private GameField gameField;
    private JPanel startPanel;
    private JTextField tfSize;
    // Данные игры
    private char humanSymb, aiSymb;

    /** Размер карты */
    private int size;

    public GameInterface() {

    }

    private void addMenu() {
        JPanel jBottomPanel = new JPanel();
        jBottomPanel.setLayout(new GridLayout());
        add(jBottomPanel, BorderLayout.SOUTH);
        JButton jbStart = new JButton("Start");
        JButton jbExit = new JButton("Exit");
        jbStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                size = Integer.parseInt(tfSize.getText());
                startPanel.setVisible(false);
                gameField.init(size);
                add(gameField, BorderLayout.CENTER);
            }
        });
        jbExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jBottomPanel.add(jbStart);
        jBottomPanel.add(jbExit);
    }

    private void addStartPanel() {
        startPanel = new JPanel();
        //startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));
        JPanel jpSize = new JPanel();
        JLabel jlSize = new JLabel("Размерность игрового поля");
        tfSize = new JTextField(3);
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
        startPanel.add(jpSize);

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
        startPanel.add(jpSymbol);

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
        // игровая панель
        gameField = new GameField();
        gameField.setLayout(new BoxLayout(gameField, BoxLayout.Y_AXIS));

        setVisible(true);
    }
}
