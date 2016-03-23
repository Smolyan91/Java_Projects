package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by smolyan on 23.03.16.
 */
public class ClientGuiView {
    private final ClientGuiController controller;

    private JFrame frame = new JFrame("Чат"); //создаем фрейм
    private JTextField textField = new JTextField(50); // техтовое поле под 50 символов
    private JTextArea messages = new JTextArea(10, 50);//потомок JTextField создает область под несколько строк(10)
    private JTextArea users = new JTextArea(10, 10);
    private JTextArea textArea = new JTextArea();

    public ClientGuiView(ClientGuiController controller) {
        this.controller = controller;
        initView();
    }



    private void initView() {
        //доступен ли для редактирования этот компонент - setEditable(false)
        textField.setEditable(false);
        messages.setEditable(false);
        users.setEditable(false);

        frame.getContentPane().add(textField, BorderLayout.NORTH); //возвращает панель с верхней
        frame.getContentPane().add(new JScrollPane(messages), BorderLayout.WEST); //прокрутка в панели справа
        frame.getContentPane().add(new JScrollPane(users), BorderLayout.EAST);
        frame.pack();           //масштабирование окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//кнопка закрыть
        frame.setVisible(true);//делаем видимым фрейм

        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.sendTextMessage(textField.getText());
                textField.setText("");
            }
        });
    }

    /**
     *  JOptionPane - всплывающее окно
     */
    public String getServerAddress() {
        return JOptionPane.showInputDialog(
                frame,
                "Введите адрес сервера:",
                "Конфигурация клиента",
                JOptionPane.QUESTION_MESSAGE);
    }

    public int getServerPort() {
        while (true) {
            String port = JOptionPane.showInputDialog(
                    frame,
                    "Введите порт сервера:",
                    "Конфигурация клиента",
                    JOptionPane.QUESTION_MESSAGE);
            try {
                return Integer.parseInt(port.trim());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        frame,
                        "Был введен некорректный порт сервера. Попробуйте еще раз.",
                        "Конфигурация клиента",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public String getUserName() {
        return JOptionPane.showInputDialog(
                frame,
                "Введите ваше имя:",
                "Конфигурация клиента",
                JOptionPane.QUESTION_MESSAGE);
    }

    public void notifyConnectionStatusChanged(boolean clientConnected) {
        textField.setEditable(clientConnected);
        if (clientConnected) {
            JOptionPane.showMessageDialog(
                    frame,
                    "Соединение с сервером установлено",
                    "Чат",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(
                    frame,
                    "Клиент не подключен к серверу",
                    "Чат",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public void refreshMessages() {
        messages.append(controller.getModel().getNewMessage() + "\n");
    }

    public void refreshUsers() {
        ClientGuiModel model = controller.getModel();
        StringBuilder sb = new StringBuilder();
        for (String userName : model.getAllUserNames()) {
            sb.append(userName).append("\n");
        }
        users.setText(sb.toString());
    }
}
