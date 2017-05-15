package com.company.java1.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by aazar on 15.05.2017.
 */
public class TicTacToe extends JFrame {
    public TicTacToe() {
        setTitle("Крестики-нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //setSize(400, 400);
        setBounds(300, 300, 400, 400);
        setResizable(true);

        JPanel jBottomPanel = new JPanel();
        jBottomPanel.setLayout(new GridLayout());
        add(jBottomPanel, BorderLayout.SOUTH);
        JButton jbStart = new JButton("Start");
        JButton jbExit = new JButton("Exit");
        jbStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

        GameField gameField = new GameField();
        add(gameField, BorderLayout.CENTER);
        setVisible(true);
    }


}
