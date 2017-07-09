package com.company.java2.homework4;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ChatInterface extends JFrame {
    // Заголовок
    private static final String APP_TITLE = "Chat 0.1";
    // Основной функционал приложения
    private ChatKernel chatKernel = new ChatKernel();
    // Поле ввода сообщения
    private JTextArea message;
    // Поле истории сообщений
    private JTextPane history;

    public ChatInterface() {

    }

    /**
     * Метод записывает текст сообщения в историю и выводит его на экран
     * @param messageText
     */
    public void sendMessage(String messageText) {
        if (!messageText.isEmpty()) {
            this.chatKernel.setHistory(messageText.trim(), "I");
            this.message.setText("");
            this.history.setText(chatKernel.getHistoryHtml());
        }
        this.message.requestFocus();
    }

    /**
     * Метод инициализирует главное окно приложения
     */
    public void init() {
        this.setLayout(new BorderLayout());
        this.setTitle(APP_TITLE);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(400, 400);
        this.setResizable(true);
        // рамка для панелей
        Border border = BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
                BorderFactory.createEmptyBorder(2, 1, 2, 1));
        // верхняя панель окна
        // поле истории сообщений
        this.history = new JTextPane();
        this.history.setContentType("text/html");
        this.history.setEditable(false);
        JScrollPane scroller = new JScrollPane(history);
        scroller.setBorder(border);
        this.add(scroller);
        // нижняя панель окна
        JPanel jBottomPanel = new JPanel();
        jBottomPanel.setLayout(new BorderLayout());
        jBottomPanel.setBorder(border);
        // поле ввода сообщение
        this.message = new JTextArea();
        this.message.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyChar() == KeyEvent.VK_ENTER) {
                    if (e.isControlDown()) {
                        sendMessage(message.getText());
                    }
                }
            }
        });
        jBottomPanel.add(this.message, BorderLayout.CENTER);
        // кнопка отправить
        JButton buttonSend = new JButton("Send");
        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage(message.getText());
            }
        });
        jBottomPanel.add(buttonSend, BorderLayout.EAST);
        this.add(jBottomPanel, BorderLayout.SOUTH);
        this.setVisible(true);
        this.message.requestFocus();
    }
}
