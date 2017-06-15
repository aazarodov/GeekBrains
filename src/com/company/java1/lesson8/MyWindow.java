package com.company.java1.lesson8;

import com.company.java1.lesson8.tictactoe.GameCell;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyWindow extends JFrame {
    public MyWindow() {
        setTitle("My Winndow");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 400);
        setResizable(true);
        setLayout(new BorderLayout());
        JButton jButton1 = new JButton("Next1");
        JButton jButton2 = new JButton("Next2");
        JButton jButton3 = new JButton("Next3");
        JButton jButton4 = new JButton("Next4");

        jButton1.setSize(new Dimension(200, 50));
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Next");
            }
        });
//        add(jButton1);
//        add(jButton2);
//        add(jButton3);
//        add(jButton4);

        Border blackline = BorderFactory.createLineBorder(Color.black);

        TestPanel jPanel = new TestPanel();
        TestPanel1 jPanel1 = new TestPanel1();
        jPanel1.setLayout(new BorderLayout());
        jPanel1.setBackground(Color.WHITE);
        jPanel1.setBorder(blackline);
        add(jPanel, BorderLayout.CENTER);
        jPanel.add(jPanel1, BorderLayout.CENTER);

        setVisible(true);
        //jPanel.repaint();
    }

    public static void main(String[] args) {
        MyWindow myWindow = new MyWindow();
    }
}
