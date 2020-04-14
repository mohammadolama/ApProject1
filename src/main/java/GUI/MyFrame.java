package GUI;

import Main.Gamestate;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    private static final MyFrame frame = new MyFrame();
    static JPanel panel;
    CardLayout cardLayout;

    private MyFrame() {

        cardLayout = new CardLayout();
        panel = new JPanel();
        setSize(1600, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(cardLayout);
        LoginPanel loginPanel = LoginPanel.getInstance();
        MenuPanel menuPanel = MenuPanel.getInstance();
        FirstHeroSelector firstHeroSelector = new FirstHeroSelector();


        panel.add(loginPanel, "login");
        panel.add(menuPanel, "menu");

        panel.add(firstHeroSelector, "hero");

        add(panel);
        setVisible(true);

        System.out.println(getSize());
        System.out.println(getPreferredSize());


    }

    public static MyFrame getInstance() {
        return frame;
    }

    public void changePanel(String string) {
        cardLayout.show(panel, string);
    }

}
