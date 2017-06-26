package com.company.java2.homework4;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ChatKernel {
    private StringBuilder history = new StringBuilder();
    private StringBuilder historyHtml = new StringBuilder();
    private String path = "";

    public ChatKernel() {

    }

    public String getHistory() {
        return history.toString();
    }

    /**
     * Метод возвращает истрию в виде html-страницы
     * @return
     */
    public String getHistoryHtml() {
        return "<html>" + '\n' + historyHtml.toString() + '\n' + "</html>";
    }

    /**
     * Метода записывает сообщение в историю
     * @param message
     * @param autor
     */
    public void setHistory(String message, String autor) {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
        Date date = Calendar.getInstance().getTime();
        autor = autor + " [ " + formatDate.format(date) + "]: ";
        this.history.append(autor + message + '\n');
        this.historyHtml.append("<b>" + autor +"</b><q>" +  message + "</q><br>" + '\n');
    }
}
