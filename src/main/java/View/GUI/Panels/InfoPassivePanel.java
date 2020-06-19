package View.GUI.Panels;

import Util.RequestHandler;
import View.GUI.Configs.ConfigsLoader;
import Main.Gamestate;
import Main.InfoPassive;
import Util.Admin;
import View.GUI.Configs.InfoConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static View.GUI.Panels.Constants.*;

public class InfoPassivePanel extends JPanel implements ActionListener {

    private Admin admin;
    private JButton passive1;
    private JButton passive2;
    private JButton passive3;
    private JButton backButton;
    private JButton start;
    private InfoPassive infoPassive;

    private InfoConfig config;

    private ArrayList<InfoPassive> passives;

    private void initConfig() {
        config = ConfigsLoader.getInstance().getInfoConfig();
    }

    public InfoPassivePanel() {
        initConfig();
        setLayout(null);
        admin = Admin.getInstance();

        start = new JButton("Start");
        start.setBounds(800, 800, 200, 60);
        start.addActionListener(this);
        add(start);

        backButton = new JButton();
        backButton.addActionListener(this);
        backButton.setBounds(config.getBack(), 880, 60, 60);
        backButton.setFocusable(false);
        backButton.setIcon(gameIcon.get("back"));
        backButton.setContentAreaFilled(false);
        backButton.setRolloverEnabled(false);
        backButton.setBorderPainted(false);
        add(backButton);

        passives = RequestHandler.SendRequest.PassiveList.response(null);
        createButton(passives);
    }

    private void createButton(ArrayList<InfoPassive> passives) {
        removeAll();
        passive1 = new JButton(passives.get(0).getName());
        passive2 = new JButton(passives.get(1).getName());
        passive3 = new JButton(passives.get(2).getName());

        passive1.setName("0");
        passive2.setName("1");
        passive3.setName("2");


        passive1.setBounds(config.getPassiveX(), config.getPassive1Y(), config.getSize(), config.getSize());
        passive2.setBounds(config.getPassiveX(), config.getPassive2Y(), config.getSize(), config.getSize());
        passive3.setBounds(config.getPassiveX(), config.getPassive3Y(), config.getSize(), config.getSize());

        passive1.addActionListener(this);
        passive2.addActionListener(this);
        passive3.addActionListener(this);

        passive1.setFocusable(false);
        passive2.setFocusable(false);
        passive3.setFocusable(false);

        passive1.setFont(f2.deriveFont(30.f));
        passive2.setFont(f2.deriveFont(30.f));
        passive3.setFont(f2.deriveFont(30.f));

        add(passive1);
        add(passive2);
        add(passive3);
        add(start);
        add(backButton);
    }


    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(222, 222, 222, 180));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(gamePics.get("info"), 0, 0, 1600, 1000, null);
        g2d.fillRect(0, 0, 1600, 1000);
        g2d.setFont(f2.deriveFont(40.0f));
        g2d.setColor(Color.red);
        g2d.drawString(passives.get(0).getDescription(), config.getPassiveDecX(), config.getPassive1Y() + 100);
        g2d.setColor(Color.MAGENTA.darker());
        g2d.drawString(passives.get(1).getDescription(), config.getPassiveDecX(), config.getPassive2Y() + 100);
        g2d.setColor(Color.blue);
        g2d.drawString(passives.get(2).getDescription(), config.getPassiveDecX(), config.getPassive3Y() + 100);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        if (src == start) {
            if (infoPassive != null) {
                admin.createPlayBoard(infoPassive);
                RequestHandler.SendRequest.Log.response("Click_Button : Start Button");
                RequestHandler.SendRequest.Log.response("Start the Battle");
            }
        }
        passive1.setBackground(Color.white);
        passive2.setBackground(Color.white);
        passive3.setBackground(Color.white);
        if (src == passive1 || src == passive2 || src == passive3) {
            src.setBackground(Color.ORANGE);
            infoPassive = passives.get(Integer.parseInt(src.getName()));
            return;
        }
        if (src == backButton) {
            Gamestate.getPlayer().setSelectedPassive(null);
            RequestHandler.SendRequest.Log.response("Click_Button : Back Button");
            RequestHandler.SendRequest.Log.response("Navigate : Main Menu");
            RequestHandler.SendRequest.VisiblePanel.response("menu");
            return;
        }


    }
}
