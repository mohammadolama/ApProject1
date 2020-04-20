package GUI;

import Enums.Heroes;
import G_L_Interface.Update;
import Interfaces.MyMouseListener;
import Main.Gamestate;
import Main.JsonBuilders;
import Main.LOGGER;
import Main.Shop;
import Sounds.SoundAdmin;
import Util.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import static GUI.Constants.*;

public class FirstHeroSelector extends JPanel implements ActionListener, MyMouseListener {

    private JButton mage;
    private JButton rogue;
    private JButton warlock;
    private JButton src;
    private JButton choose;
    private String hero;
    private ArrayList<Images> ar;
    private boolean flag;
    private boolean flag2;
    private String name;


    private JLabel jLabel;

    private int size = 350;
    private int spacing = 400;
    private int startX = 340;
    private int startY = 10;
    private int width = 210;
    private int height = 300;


    public FirstHeroSelector() {
        ar = new ArrayList<>();
        addMouseListener(this);
        setFocusable(true);
        requestFocus();
        setLayout(null);

        mage = new JButton();
        mage.setIcon(mageIcon);
        mage.setContentAreaFilled(false);
        mage.setBorderPainted(false);
        mage.setBounds(startX, startY, size, size);
        mage.setFont(f2);
        mage.setFocusable(false);
        mage.addActionListener(this);

        rogue = new JButton();
        rogue.setIcon(rogueIcon);
        rogue.setContentAreaFilled(false);
        rogue.setBorderPainted(false);
        rogue.setBounds(startX + spacing, startY, size, size);
        rogue.setFont(f2);
        rogue.setFocusable(false);
        rogue.addActionListener(this);

        warlock = new JButton();
        warlock.setIcon(warlockIcon);
        warlock.setContentAreaFilled(false);
        warlock.setBorderPainted(false);
        warlock.setBounds(startX + 2 * spacing, startY, size, size);
        warlock.setFont(f2);
        warlock.setFocusable(false);
        warlock.addActionListener(this);


        choose = new JButton("Choose");
        choose.setFocusable(false);
        choose.setBounds(120, gameHight - 120, 120, 30);
        choose.setFont(f2);
        choose.addActionListener(this);

        jLabel = new JLabel();
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
        refresh();
        g.drawImage(heroBackground, 0, 0, 1600, 1000, null);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(f2.deriveFont(35.0f));
        FontMetrics fontMetrics = g2d.getFontMetrics(f2);
        g2d.setColor(Color.BLUE);
        g2d.drawString("Mage", (startX + 120), startY + size + 15);
        g2d.drawString("Rogue", (startX + 120) + spacing, startY + size + 15);
        g2d.drawString("warlock", (startX + 120) + 2 * spacing, startY + size + 15);
        g2d.setFont(f2.deriveFont(40.0f));
        g2d.setColor(Color.YELLOW);
        g2d.drawString("     Hero    ", 20, startY + size + 15);
        g2d.drawString("  Hero Power ", 20, startY + 500);
        g2d.drawString("Special Cards", 20, startY + 750);
        g2d.drawImage(magePower, startX + 70, startY + 370, 180, 250, null);
        g2d.drawImage(magePower, (startX + 70) + spacing + 10, startY + 370, 180, 250, null);
        g2d.drawImage(magePower, (startX + 70) + 2 * spacing + 10, startY + 370, 180, 250, null);
        g2d.drawImage(cardPics.get("polymorph"), startX - 50, startY + 610, width, height, null);
        ar.add(new Images("polymorph", startX - 50, startY + 610, width, height));
        g2d.drawImage(cardPics.get("flamestrike"), startX + 150, startY + 610, width, height, null);
        ar.add(new Images("flamestrike", startX + 150, startY + 610, width, height));
        g2d.drawImage(cardPics.get("friendlysmith"), (startX - 50) + spacing + 10, startY + 610, width, height, null);
        ar.add(new Images("friendlysmith", (startX - 50) + spacing + 10, startY + 610, width, height));
        g2d.drawImage(cardPics.get("umbralskulker"), (startX + 150) + spacing + 10, startY + 610, width, height, null);
        ar.add(new Images("umbralskulker", (startX + 150) + spacing + 10, startY + 610, width, height));
        g2d.drawImage(cardPics.get("dreadscale"), (startX - 50) + 2 * spacing + 20, startY + 610, width, height, null);
        ar.add(new Images("dreadscale", (startX - 50) + 2 * spacing + 20, startY + 610, width, height));
        g2d.drawImage(cardPics.get("darkskies"), (startX + 150) + 2 * spacing + 20, startY + 610, width, height, null);
        ar.add(new Images("darkskies", (startX + 150) + 2 * spacing + 20, startY + 610, width, height));
        g2d.setColor(Color.RED);
        g2d.setFont(designer);
        g2d.drawString("Designed by Ghaffari", 5, 940);
        flag2 = false;
        if (flag) {
            if (!flag2) {
                removeAll();
                ar.clear();
                g2d.setColor(new Color(222, 222, 222, 170));
                g2d.fillRect(0, 0, 1600, 1000);
                g2d.setColor(Color.white);
                flag2 = true;
            }
            g2d.drawImage(cardPics.get(name), 620, 270, null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        src = (JButton) e.getSource();
        if (src == mage) {
            hero = "mage";
        } else if (src == rogue) {
            hero = "rogue";
        } else if (src == warlock) {
            hero = "warlock";
        } else if (src == choose) {
            if (hero == null || hero == "")
                return;
            Admin.getInstance().selectFirstHero(hero);
        }

    }

    private void drawBigger(String st) {
        flag = true;
        name = st;
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        String st = null;
        for (Images images : ar) {
            if ((x >= images.getX() && x <= images.getX() + images.getWidth()) && (y >= images.getY() && y <= images.getY() + images.getHeigth())) {
                st = images.getName();
                break;
            }
        }
        if (st == null || st == "") {
            flag = false;
            return;
        }
        drawBigger(st);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
