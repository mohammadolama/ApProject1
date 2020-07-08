package View.Panels;

import Controller.RequestHandler;
import Controller.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static View.Panels.Constants.*;

public class SettingPanel extends JPanel implements ActionListener {

    private JButton decreaseSound;
    private JButton increaseSound;
    private JButton muteSound;
    private JButton back;
    private JButton exit;

    private int i = 0;

    private static SettingPanel settingPanel = new SettingPanel();

    private SettingPanel() {
        setLayout(null);
        setFocusable(true);
        back = new JButton();
        back.setIcon(gameIcon.get("back"));
        back.setFocusable(false);
        back.addActionListener(this);
        back.setBounds(1430, 890, 60, 60);
        back.setContentAreaFilled(false);
        back.setRolloverEnabled(false);
        back.setBorderPainted(false);
        add(back);

        exit = new JButton();
        exit.addActionListener(this);
        exit.setIcon(gameIcon.get("exit"));
        exit.setBounds(1500, 890, 60, 60);
        exit.setFocusable(false);
        exit.setContentAreaFilled(false);
        exit.setRolloverEnabled(false);
        exit.setBorderPainted(false);
        add(exit);


        decreaseSound = new JButton("Decrease volume");
        decreaseSound.addActionListener(this);
        decreaseSound.setFocusable(false);
        decreaseSound.setFont(f2.deriveFont(25.0f));
        decreaseSound.setBounds(200, 300, 250, 100);
        add(decreaseSound);

        increaseSound = new JButton("Increase volume");
        increaseSound.addActionListener(this);
        increaseSound.setFocusable(false);
        increaseSound.setFont(f2.deriveFont(25.0f));
        increaseSound.setBounds(200, 500, 250, 100);
        add(increaseSound);

        muteSound = new JButton("Mute volume");
        muteSound.addActionListener(this);
        muteSound.setFocusable(false);
        muteSound.setFont(f2.deriveFont(25.0f));
        muteSound.setBounds(200, 700, 250, 100);
        add(muteSound);


    }

    public static SettingPanel getInstance() {
        return settingPanel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(gamePics.get("setting"), 0, 0, 1600, 1000, null);
        g.setColor(new Color(222, 222, 222, 130));
        g.fillRect(0, 0, 1600, 1000);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sr = (JButton) e.getSource();
        if (sr == decreaseSound) {
            RequestHandler.SendRequest.Log.response("Click_Button : Volume_Down Button");
            RequestHandler.SendRequest.DecreaseSound.response(null,0);
        } else if (sr == increaseSound) {
            RequestHandler.SendRequest.Log.response("Click_Button : Volume_Up Button");
            RequestHandler.SendRequest.IncreaseSound.response(null,0);
        } else if (sr == muteSound) {
            RequestHandler.SendRequest.Log.response("Click_Button : Mute Button");
            RequestHandler.SendRequest.MuteSound.response(null,i);
            i++;
        } else if (sr == back) {
            RequestHandler.SendRequest.Log.response("Click_Button : Back Button");
            RequestHandler.SendRequest.Log.response("Navigate : Main Menu");
            RequestHandler.SendRequest.SaveAndUpdate.response(null);
            RequestHandler.SendRequest.VisiblePanel.response("menu");
        } else if (sr == exit) {
            RequestHandler.SendRequest.Log.response("Click_Button : Exit Button");
            RequestHandler.SendRequest.Exit.response(null);
        }
    }
}
