package GUI;

import Main.Gamestate;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    private static final MyFrame frame=new MyFrame();
    JPanel panel;
    CardLayout cardLayout;
    private MyFrame(){

         cardLayout=new CardLayout();
        panel=new JPanel();
       setSize(1600,1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(cardLayout);
        LoginPanel loginPanel=LoginPanel.getInstance();
        MenuPanel menuPanel=MenuPanel.getInstance();
        StatusPanel statusPanel=StatusPanel.getInstance();
        FirstHeroSelector firstHeroSelector=new FirstHeroSelector();

        panel.add(firstHeroSelector,"hero");
        panel.add(loginPanel,"login");
        panel.add(statusPanel,"status");
        panel.add(menuPanel,"menu");
        add(panel);
        setVisible(true);


    }

    static MyFrame getInstance(){
        return frame;
    }
   public void changePanel(String string){
        cardLayout.show(panel,string);
   }

}
