package Client.View.View.Panels;

import Client.View.Configs.ConfigsLoader;
import Client.View.Configs.MenuConfig;
import Client.View.View.Panels.Listeners.MenuListeners.MenuAction;
import Client.View.View.Panels.Listeners.MenuListeners.MenuMouse;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private MenuConfig config;

    private void initConfig() {
        config = ConfigsLoader.getInstance().getMenuConfig();
    }

    private final JButton play = new JButton("Play");
    private final JButton collection = new JButton("Collection");
    private final JButton store = new JButton("Store");
    private final JButton setting = new JButton("Setting");
    private final JButton status = new JButton("Status");
    private final JButton logout = new JButton("Log Out");
    private final JButton exit = new JButton();
    private final JButton cheat = new JButton();
    private JButton src;
    private final MenuMouse mm = new MenuMouse(this);
    private final MenuAction ma = new MenuAction(this);

    private static final MenuPanel menu = new MenuPanel();

    private MenuPanel() {
        initConfig();
        setLayout(null);
        setFocusable(true);
        play.addActionListener(ma);
        play.addMouseListener(mm);
        play.setBounds(config.getStartX(), config.getStartY(), 200, 50);
        play.setFont(Constants.f2);
        play.setFocusable(false);
        add(play);


        collection.addActionListener(ma);
        collection.addMouseListener(mm);
        collection.setBounds(config.getStartX(), config.getStartY() + config.getSpcaing(), 200, 50);
        collection.setFocusable(false);
        collection.setFont(Constants.f2);
        add(collection);

        store.addActionListener(ma);
        store.addMouseListener(mm);
        store.setBounds(config.getStartX(), config.getStartY() + 2 * config.getSpcaing(), 200, 50);
        store.setFocusable(false);
        store.setFont(Constants.f2);
        add(store);

        setting.addActionListener(ma);
        setting.addMouseListener(mm);
        setting.setBounds(config.getStartX(), config.getStartY() + 3 * config.getSpcaing(), 200, 50);
        setting.setFocusable(false);
        setting.setFont(Constants.f2);
        add(setting);

        status.addActionListener(ma);
        status.addMouseListener(mm);
        status.setBounds(config.getStartX(), config.getStartY() + 4 * config.getSpcaing(), 200, 50);
        status.setFocusable(false);
        status.setFont(Constants.f2);
        add(status);

        logout.addActionListener(ma);
        logout.addMouseListener(mm);
        logout.setBounds(config.getStartX(), config.getStartY() + 5 * config.getSpcaing(), 200, 50);
        logout.setFocusable(false);
        logout.setText("Log Out");
        logout.setFont(Constants.f2);
        add(logout);

        cheat.addActionListener(ma);
        cheat.setBounds(1420, 760, 70, 100);
        cheat.setContentAreaFilled(false);
        cheat.setRolloverEnabled(false);
        cheat.setBorderPainted(false);
        cheat.setFocusable(false);
        add(cheat);


        exit.addActionListener(ma);
        exit.setIcon(Constants.gameIcon.get("exit"));
        exit.setBounds(10, 890, 60, 60);
        exit.setFocusable(false);
        exit.setContentAreaFilled(false);
        exit.setRolloverEnabled(false);
        exit.setBorderPainted(false);
        add(exit);

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


    public MenuConfig getConfig() {
        return config;
    }

    public void setConfig(MenuConfig config) {
        this.config = config;
    }

    public JButton getPlay() {
        return play;
    }

    public JButton getCollection() {
        return collection;
    }

    public JButton getStore() {
        return store;
    }

    public JButton getSetting() {
        return setting;
    }

    public JButton getStatus() {
        return status;
    }

    public JButton getLogout() {
        return logout;
    }

    public JButton getExit() {
        return exit;
    }

    public JButton getCheat() {
        return cheat;
    }

    public JButton getSrc() {
        return src;
    }

    public void setSrc(JButton src) {
        this.src = src;
    }

    public static MenuPanel getMenu() {
        return menu;
    }
}

