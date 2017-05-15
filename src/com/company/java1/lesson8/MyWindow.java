package com.company.java1.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyWindow extends JFrame {
    public MyWindow() {
        setTitle("My Winndow");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 400);
        setResizable(true);
        setLayout(new FlowLayout());
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
        add(jButton1);
        add(jButton2);
        add(jButton3);
        add(jButton4);

        setVisible(true);
    }
}
