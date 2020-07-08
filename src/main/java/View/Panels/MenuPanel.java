package View.Panels;

import Controller.RequestHandler;
import Configs.ConfigsLoader;
import Configs.MenuConfig;
import Controller.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuPanel extends JPanel implements ActionListener, MouseListener, KeyListener {
    private MenuConfig config;

    private void initConfig() {
        config = ConfigsLoader.getInstance().getMenuConfig();
    }

    private static JButton play = new JButton("Play");
    private static JButton collection = new JButton("Collection");
    private static JButton store = new JButton("Store");
    private static JButton setting = new JButton("Setting");
    private static JButton status = new JButton("Status");
    private static JButton logout = new JButton("Log Out");
    private static JButton exit = new JButton();
    private static JButton cheat = new JButton();
    private static JButton src;

    private static final MenuPanel menu = new MenuPanel();

    private MenuPanel() {
        initConfig();
        setLayout(null);
        setFocusable(true);
        play.addActionListener(this);
        play.addMouseListener(this);
        play.setBounds(config.getStartX(), config.getStartY(), 200, 50);
        play.setFont(Constants.f2);
        play.setFocusable(false);
        add(play);


        collection.addActionListener(this);
        collection.addMouseListener(this);
        collection.setBounds(config.getStartX(), config.getStartY() + config.getSpcaing(), 200, 50);
        collection.setFocusable(false);
        collection.setFont(Constants.f2);
        add(collection);

        store.addActionListener(this);
        store.addMouseListener(this);
        store.setBounds(config.getStartX(), config.getStartY() + 2 * config.getSpcaing(), 200, 50);
        store.setFocusable(false);
        store.setFont(Constants.f2);
        add(store);

        setting.addActionListener(this);
        setting.addMouseListener(this);
        setting.setBounds(config.getStartX(), config.getStartY() + 3 * config.getSpcaing(), 200, 50);
        setting.setFocusable(false);
        setting.setFont(Constants.f2);
        add(setting);

        status.addActionListener(this);
        status.addMouseListener(this);
        status.setBounds(config.getStartX(), config.getStartY() + 4 * config.getSpcaing(), 200, 50);
        status.setFocusable(false);
        status.setFont(Constants.f2);
        add(status);

        logout.addActionListener(this);
        logout.addMouseListener(this);
        logout.setBounds(config.getStartX(), config.getStartY() + 5 * config.getSpcaing(), 200, 50);
        logout.setFocusable(false);
        logout.setText("Log Out");
        logout.setFont(Constants.f2);
        add(logout);

        cheat.addActionListener(this);
        cheat.setBounds(1420, 760, 70, 100);
        cheat.setContentAreaFilled(false);
        cheat.setRolloverEnabled(false);
        cheat.setBorderPainted(false);
        cheat.setFocusable(false);
        add(cheat);


        exit.addActionListener(this);
        exit.setIcon(Constants.gameIcon.get("exit"));
        exit.setBounds(10, 890, 60, 60);
        exit.setFocusable(false);
        exit.setContentAreaFilled(false);
        exit.setRolloverEnabled(false);
        exit.setBorderPainted(false);
        add(exit);


        addKeyListener(this);
        setFocusable(true);
        requestFocus();
    }

    public static MenuPanel getInstance() {
        return menu;
    }


    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(Constants.gamePics.get("main"), 0, 0, null);
        g.setColor(new Color(254, 255, 253, 170));
        g.fillRect(0, 0, 250, 1000);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(Constants.f2.deriveFont(80.0f));
        g2d.setColor(Color.red);
        g2d.drawString("Math & CStone", 550, 100);
        g2d.setColor(Color.RED);
        g2d.setFont(Constants.designer);
        g2d.drawString("Designed by Ghaffari", 1450, 940);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button == logout) {
            RequestHandler.SendRequest.Log.response("Click_Button : Logout Button");
           RequestHandler.SendRequest.LogOut.response(null);
        } else if (button == cheat) {
            String st = JOptionPane.showInputDialog(this, "Don't Cheat!!");
            if (st.equalsIgnoreCase("lvlup")) {
                RequestHandler.SendRequest.LvlUp.response(null);
                JOptionPane.showMessageDialog(this, "Level up!");
            } else if (st.equalsIgnoreCase("hesoyam")) {
                RequestHandler.SendRequest.UnlockHeros.response(null);
                JOptionPane.showMessageDialog(this, "All heroes unlocked!");
            }
        } else if (button == exit) {
            RequestHandler.SendRequest.Log.response("Click_Button : Exit Button");
            RequestHandler.SendRequest.Exit.response(null);
        } else if (button == play) {
            RequestHandler.SendRequest.Log.response("Click_Button : Play Button");
            Admin.getInstance().enterGame();
        } else if (button == status) {
            RequestHandler.SendRequest.Log.response("Click_Button : Status Button");
            RequestHandler.SendRequest.Log.response("Navigate : Status");
            RequestHandler.SendRequest.VisiblePanel.response("status");
        } else if (button == setting) {
            RequestHandler.SendRequest.Log.response("Click_Button : Setting Button");
            RequestHandler.SendRequest.Log.response("Navigate : Setting");
            RequestHandler.SendRequest.VisiblePanel.response("setting");
        } else if (button == store) {
            RequestHandler.SendRequest.Log.response("Click_Button : Store Button");
            RequestHandler.SendRequest.Log.response("Navigate : Store");
            RequestHandler.SendRequest.VisiblePanel.response("shop");
        } else if (button == collection) {
            RequestHandler.SendRequest.Log.response("Click_Button : Collection Button");
            RequestHandler.SendRequest.Log.response("Navigate : Collection");
            RequestHandler.SendRequest.VisiblePanel.response("collection");
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        src = (JButton) e.getSource();
        src.setBackground(new Color(240, 255, 97));

    }

    @Override
    public void mouseExited(MouseEvent e) {
        src.setBackground(Color.WHITE);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
