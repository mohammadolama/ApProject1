package Client.View.View.Panels;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.PassiveRequest;
import Client.Controller.Responses;
import Client.Model.InfoPassive;
import Client.View.Configs.ConfigsLoader;
import Client.View.Configs.InfoConfig;
import Client.View.View.Panels.Listeners.InfoAction;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InfoPassivePanel extends JPanel {

    private JButton passive1;
    private JButton passive2;
    private JButton passive3;
    private final JButton backButton;
    private final JButton normal;
    private final JButton deckReader;
    private JButton practice;
    private final JButton online;
    private InfoPassive infoPassive;
    private final InfoAction ia = new InfoAction(this);

    private InfoConfig config;

    private final ArrayList<InfoPassive> passives;

    private void initConfig() {
        config = ConfigsLoader.getInstance().getInfoConfig();
    }

    public InfoPassivePanel() {
        initConfig();
        setLayout(null);

        normal = new JButton("Normal Mode");
        normal.setFont(Constants.fantasy.deriveFont(30.0f));
        normal.setBounds(400, 850, 200, 60);
        normal.addActionListener(ia);
        add(normal);

        deckReader = new JButton("Deck Reader");
        deckReader.setFont(Constants.fantasy.deriveFont(23.0f));
        deckReader.setBounds(700, 850, 200, 60);
        deckReader.addActionListener(ia);
        add(deckReader);


        online = new JButton("Online game");
        online.setFont(Constants.fantasy.deriveFont(23.0f));
        online.setBounds(1000, 850, 200, 60);
        online.addActionListener(ia);
        add(online);


        backButton = new JButton();
        backButton.addActionListener(ia);
        backButton.setBounds(config.getBack(), 880, 60, 60);
        backButton.setFocusable(false);
        backButton.setIcon(Constants.gameIcon.get("back"));
        backButton.setContentAreaFilled(false);
        backButton.setRolloverEnabled(false);
        backButton.setBorderPainted(false);
        add(backButton);

        RequestHandler.getInstance().sendRequest(new PassiveRequest());
        passives = Responses.getInstance().getPassiveList();
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

        passive1.addActionListener(ia);
        passive2.addActionListener(ia);
        passive3.addActionListener(ia);

        passive1.setFocusable(false);
        passive2.setFocusable(false);
        passive3.setFocusable(false);

        passive1.setFont(Constants.f2.deriveFont(30.f));
        passive2.setFont(Constants.f2.deriveFont(30.f));
        passive3.setFont(Constants.f2.deriveFont(30.f));

        add(passive1);
        add(passive2);
        add(passive3);
        add(normal);
        add(deckReader);
        add(practice);
        add(backButton);
        add(online);
    }


    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(222, 222, 222, 180));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(Constants.gamePics.get("info"), 0, 0, 1600, 1000, null);
        g2d.fillRect(0, 0, 1600, 1000);
        g2d.setFont(Constants.f2.deriveFont(40.0f));
        g2d.setColor(Color.red);
        g2d.drawString(passives.get(0).getDescription(), config.getPassiveDecX(), config.getPassive1Y() + 100);
        g2d.setColor(Color.MAGENTA.darker());
        g2d.drawString(passives.get(1).getDescription(), config.getPassiveDecX(), config.getPassive2Y() + 100);
        g2d.setColor(Color.blue);
        g2d.drawString(passives.get(2).getDescription(), config.getPassiveDecX(), config.getPassive3Y() + 100);
    }

    public JButton getPassive1() {
        return passive1;
    }

    public JButton getPassive2() {
        return passive2;
    }

    public JButton getPassive3() {
        return passive3;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getNormal() {
        return normal;
    }

    public JButton getDeckReader() {
        return deckReader;
    }

    public JButton getOnline() {
        return online;
    }

    public InfoPassive getInfoPassive() {
        return infoPassive;
    }

    public void setInfoPassive(InfoPassive infoPassive) {
        this.infoPassive = infoPassive;
    }

    public InfoConfig getConfig() {
        return config;
    }

    public void setConfig(InfoConfig config) {
        this.config = config;
    }

    public ArrayList<InfoPassive> getPassives() {
        return passives;
    }
}
