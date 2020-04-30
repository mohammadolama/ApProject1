package GUI.Panels;

import G_L_Interface.Update;
import Sounds.SoundAdmin;
import Util.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUI.Panels.Constants.*;

public class SettingPanel extends JPanel implements ActionListener {

    private JButton decreaseSound;
    private JButton increaseSound;
    private JButton muteSound;
    private JButton back;
    private JButton exit;
    private Admin admin;

    private int i = 0;

    private static SettingPanel settingPanel = new SettingPanel();

    private SettingPanel() {
        admin = Admin.getInstance();

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
            admin.Log("Click_Button : Volume_Down Button");
            admin.decreaseVolume();
        } else if (sr == increaseSound) {
            admin.Log("Click_Button : Up Button");
            admin.increaseVolume();
        } else if (sr == muteSound) {
            admin.Log("Click_Button : Mute Button");
            SoundAdmin.stopStart(i);
            i++;
        } else if (sr == back) {
            Admin.getInstance().Log("Click_Button : Exit Button");
            Update.saveAndUpdate();
            Admin.getInstance().Log("Navigate : Main Menu");
            MyFrame.getInstance().changePanel("menu");
            MyFrame.getInstance().requestFocus();
        } else if (sr == exit) {
            Admin.getInstance().Log("Click_Button : Exit Button");
            Admin.getInstance().exit();
        }
    }
}
