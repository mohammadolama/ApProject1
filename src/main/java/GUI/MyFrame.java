package GUI;

import Main.Gamestate;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    private static final MyFrame frame = new MyFrame();
    static JPanel panel;
    CardLayout cardLayout;

    private MyFrame() {
        setSize(1600, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        cardLayout = new CardLayout();
        panel = new JPanel();


        panel.setLayout(cardLayout);
        LoginPanel loginPanel = LoginPanel.getInstance();
        MenuPanel menuPanel = MenuPanel.getInstance();

        panel.add(loginPanel, "login");
        panel.add(menuPanel, "menu");

        add(panel);
        setVisible(true);

        setPreferredSize(new Dimension(1600,1000));


    }

    public static MyFrame getInstance() {
        return frame;
    }

    public static JPanel getPanel() {
        return panel;
    }

    public static void setPanel(JPanel panel) {
        MyFrame.panel = panel;
    }

    public void changePanel(String string) {
        cardLayout.show(panel, string);
    }

}
