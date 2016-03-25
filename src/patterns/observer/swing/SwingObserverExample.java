package patterns.observer.swing;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingObserverExample {
    JFrame frame;

    public static void main(String[] args) {
        SwingObserverExample swingObserverExample = new SwingObserverExample();
        swingObserverExample.go();
    }

    public void go(){
        frame = new JFrame("Answer");
        JButton button = new JButton("Should i do it ?");

        // Назначаем объекты слушателями (наблюдателями) событий кнопки
        // With lambdas
        button.addActionListener(event ->
                System.out.println("Don't do it, you might regret it!")
        );
        button.addActionListener(event ->
                System.out.println("Come on, do it!")
        );
        frame.getContentPane().add(BorderLayout.CENTER, button);

        // Set frame properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.CENTER, button);
        frame.setSize(300,300);
        frame.setVisible(true);

        //Without lambdas
       // jButton.addActionListener(new AngelListener());
       // jButton.addActionListener(new DevilListener());
    }
/*
    class AngelListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Don't do it, you might regret it!");
        }
    }
    class DevilListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Come on, do it !");
        }
    }
*/
}
