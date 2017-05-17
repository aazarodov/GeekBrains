package com.company.java1.lesson8;

import com.company.java1.lesson8.tictactoe.AgentAI;
import com.company.java1.lesson8.tictactoe.GameCell;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class TestPanel extends JPanel {

    public TestPanel() {

    }

    @Override
    public void paintComponent(Graphics g) {
        //if (!textFin.equals("")) {
            Graphics2D g2 = (Graphics2D) g;
            int fontSize = 20;
            g.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
            g.setColor(Color.red);
            int width = getWidth();
            int height = getHeight();
            g.drawString("TEST", 100, 100);
            g.drawLine(0, 0, width, height);
            //repaint();
        //}
    }
}
