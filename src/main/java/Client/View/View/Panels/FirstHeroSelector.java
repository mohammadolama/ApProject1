package Client.View.View.Panels;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.FirstHeroRequest;
import Client.Controller.Requests.PureModelViewRequest;
import Client.Controller.Responses;
import Client.Model.CardModelView;
import Client.Model.Enums.Type;
import Client.View.Configs.ConfigsLoader;
import Client.View.Configs.FirstHeroConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Client.View.View.Panels.Constants.*;

public class FirstHeroSelector extends JPanel implements ActionListener {

    private JButton mage;
    private JButton rogue;
    private JButton warlock;
    private JButton choose;
    private String hero;

    private FirstHeroConfig config;

    private void initConfig() {
        config = ConfigsLoader.getInstance().getFirstHeroConfig();
    }

    public FirstHeroSelector() {
        initConfig();
        setFocusable(true);
        requestFocus();
        setLayout(null);

        mage = new JButton();
        mage.setIcon(mageIcon);
        mage.setContentAreaFilled(false);
        mage.setBorderPainted(false);
        mage.setBounds(config.getStartX(), config.getStartY(), config.getSize(), config.getSize());
        mage.setFont(f2);
        mage.setFocusable(false);
        mage.addActionListener(this);

        rogue = new JButton();
        rogue.setIcon(rogueIcon);
        rogue.setContentAreaFilled(false);
        rogue.setBorderPainted(false);
        rogue.setBounds(config.getStartX() + config.getSpacing(), config.getStartY(), config.getSize(), config.getSize());
        rogue.setFont(f2);
        rogue.setFocusable(false);
        rogue.addActionListener(this);

        warlock = new JButton();
        warlock.setIcon(warlockIcon);
        warlock.setContentAreaFilled(false);
        warlock.setBorderPainted(false);
        warlock.setBounds(config.getStartX() + 2 * config.getSpacing(), config.getStartY(), config.getSize(), config.getSize());
        warlock.setFont(f2);
        warlock.setFocusable(false);
        warlock.addActionListener(this);


        choose = new JButton("Choose");
        choose.setFocusable(false);
        choose.setBounds(120, gameHight - 120, 120, 30);
        choose.setFont(f2);
        choose.addActionListener(this);

        JLabel jLabel = new JLabel();
        jLabel.setIcon(new ImageIcon());
        refresh();
    }

    private void refresh() {
        add(mage);
        add(warlock);
        add(rogue);
        add(choose);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.drawImage(gamePics.get("herobackground"), 0, 0, 1600, 1000, null);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setFont(f2.deriveFont(35.0f));
        g2d.setColor(Color.BLUE);
        g2d.drawString("Mage", (config.getStartX() + 120), config.getStartY() + config.getSize() + 15);
        g2d.drawString("Rogue", (config.getStartX() + 120) + config.getSpacing(), config.getStartY() + config.getSize() + 15);
        g2d.drawString("warlock", (config.getStartX() + 120) + 2 * config.getSpacing(), config.getStartY() + config.getSize() + 15);

        g2d.setFont(f2.deriveFont(40.0f));
        g2d.setColor(Color.YELLOW);
        g2d.drawString("     Hero    ", 20, config.getStartY() + config.getSize() + 15);
        g2d.drawString("  Hero Power ", 20, config.getStartY() + 500);
        g2d.drawString("Special Cards", 20, config.getStartY() + 750);

        g2d.drawImage(cardPics.get("mage"), config.getStartX() + 70, config.getStartY() + 370, 180, 250, null);
        g2d.drawImage(cardPics.get("rogue"), (config.getStartX() + 70) + config.getSpacing() + 10, config.getStartY() + 370, 180, 250, null);
        g2d.drawImage(cardPics.get("warlock"), (config.getStartX() + 70) + 2 * config.getSpacing() + 10, config.getStartY() + 370, 180, 250, null);

        g2d.drawImage(cardPics.get("polymorph"), config.getStartX() - 50, config.getStartY() + 610, config.getWidth(), config.getHeight(), null);
        drawCardInfo(g2d, "polymorph", config.getStartX() - 50, config.getStartY() + 610);

        g2d.drawImage(cardPics.get("flamestrike"), config.getStartX() + 150, config.getStartY() + 610, config.getWidth(), config.getHeight(), null);
        drawCardInfo(g2d, "flamestrike", config.getStartX() + 150, config.getStartY() + 610);

        g2d.drawImage(cardPics.get("aylar"), (config.getStartX() - 50) + config.getSpacing() + 10, config.getStartY() + 610, config.getWidth(), config.getHeight(), null);
        drawCardInfo(g2d, "aylar", (config.getStartX() - 50) + config.getSpacing() + 10, config.getStartY() + 610);

        g2d.drawImage(cardPics.get("yasaman"), (config.getStartX() + 150) + config.getSpacing() + 10, config.getStartY() + 610, config.getWidth(), config.getHeight(), null);
        drawCardInfo(g2d, "yasaman", (config.getStartX() + 150) + config.getSpacing() + 10, config.getStartY() + 610);

        g2d.drawImage(cardPics.get("benyamin"), (config.getStartX() - 50) + 2 * config.getSpacing() + 20, config.getStartY() + 610, config.getWidth(), config.getHeight(), null);
        drawCardInfo(g2d, "benyamin", (config.getStartX() - 50) + 2 * config.getSpacing() + 20, config.getStartY() + 610);

        g2d.drawImage(cardPics.get("darkskies"), (config.getStartX() + 150) + 2 * config.getSpacing() + 20, config.getStartY() + 610, config.getWidth(), config.getHeight(), null);
        drawCardInfo(g2d, "darkskies", (config.getStartX() + 150) + 2 * config.getSpacing() + 20, config.getStartY() + 610);

        g2d.setColor(Color.RED);
        g2d.setFont(designer);
        g2d.drawString("Designed by Ghaffari", 5, 940);
    }

    private void drawCardInfo(Graphics2D g, String cards, int xe, int ye) {
        g.setFont(fantasy.deriveFont(50.0f));
        g.setColor(Color.WHITE);
        RequestHandler.getInstance().sendRequest(new PureModelViewRequest(cards));
        CardModelView view = Responses.getInstance().getCardModelView();
        if (view.getType().equals(Type.Minion)) {
            int x = xe + 20;
            int y = ye + 65;
            g.drawString(view.getManaCost() + "", x, y);
            g.drawString((view.getDamage() + ""), x, y + 230);
            g.drawString((view.getHp() + ""), x + 150, y + 230);
        } else if (view.getType().equals(Type.Weapon)) {
            int x = xe + 45;
            int y = ye + 65;
            g.drawString(view.getManaCost() + "", x, y);
            g.drawString((view.getDamage() + ""), x, y + 445);
            g.drawString((view.getHp() + ""), x + 300, y + 440);
        } else if (view.getType().equals(Type.Spell)) {
            int x = xe + 25;
            int y = ye + 50;
            g.drawString(view.getManaCost() + "", x, y);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        if (src == mage) {
            JOptionPane.showMessageDialog(this, "Mage selected");
            hero = "mage";
        } else if (src == rogue) {
            JOptionPane.showMessageDialog(this, "Rogue selected");
            hero = "rogue";
        } else if (src == warlock) {
            JOptionPane.showMessageDialog(this, "Warlock selected");
            hero = "warlock";
        } else if (src == choose) {
            if (hero == null || hero.equals(""))
                return;
            RequestHandler.getInstance().sendRequest(new FirstHeroRequest(hero));
        }
    }

}
