package GUI;

import AllCards.Cards;
import Enums.Carts;
import G_L_Interface.Update;
import Main.Deck;
import Main.Gamestate;
import Main.JsonBuilders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static GUI.Constants.*;

public class StatusPanel extends JPanel implements ActionListener {

    private static ArrayList<BufferedImage> ar1 = new ArrayList<>();
    private static ArrayList<JButton> buttons = new ArrayList<>();

    private Deck selectedDeck;


    private int startX = 300;
    private int startY = 190;
    private int width = 180;
    private int height = 250;


    private JButton back;
    private JButton exit = new JButton();
    private static StatusPanel statusPanel = new StatusPanel();

    private StatusPanel() {
        showDecks();
        setLayout(null);
        back = new JButton();
        back.setIcon(backIcon);
        back.setFocusable(false);
        back.addActionListener(this);
        back.setBounds(1430, 890, 60, 60);
        back.setContentAreaFilled(false);
        back.setRolloverEnabled(false);
        back.setBorderPainted(false);
        add(back);

        exit.addActionListener(this);
        exit.setIcon(exitIcon);
        exit.setBounds(1500, 890, 60, 60);
        exit.setFocusable(false);
        exit.setContentAreaFilled(false);
        exit.setRolloverEnabled(false);
        exit.setBorderPainted(false);
        add(exit);
        setFocusable(true);
    }

    public static StatusPanel getInstance() {
        return statusPanel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        showDecks();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.YELLOW);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(status, 0, 0, 1600, 1000, null);
        g2d.drawImage(status2, 0, -55, 300, 970, null);
        g2d.drawLine(1250, 0, 1250, 1000);
        g2d.setFont(f2.deriveFont(40.0f));
        g2d.drawString("My Decks", 1330, 40);
        g2d.setFont(f2);
        g2d.drawString("Username: " + Gamestate.getPlayer().getUsername(), 50, 200);
        g2d.drawString("Level   : " + Gamestate.getPlayer().getLevel(), 50, 300);
        g2d.drawString("EXP     : " + Gamestate.getPlayer().getExp(), 50, 400);
        g2d.drawString("Wallet: " + Gamestate.getPlayer().getMoney(), 50, 500);
        g2d.drawLine(300, startY - 10, 1250, startY - 10);
        g2d.setFont(f2.deriveFont(18.0f));
        if (selectedDeck != null) {
            g2d.drawString("Name          : " + selectedDeck.getName(), startX + 30, 40);
            g2d.drawString("Total plays : " + selectedDeck.getTotalPlays(), startX + 30, 70);
            g2d.drawString("Total wins  : " + selectedDeck.getTotalWins(), startX + 30, 100);
            g2d.drawString("Win Rate    : " + selectedDeck.winRate(), startX + 30, 130);
            g2d.drawString("Average Mana    : " + calculateAvgMana(selectedDeck), startX + 30, 160);
            g2d.drawImage(heroPics.get(selectedDeck.getHero().getName().toLowerCase()), startX + 400, 0, 150, 200, null);
            g2d.drawImage(cardPics.get(selectedDeck.getMostUsedCard().toString().toLowerCase()), startX + 700, 0, 130, 180, null);
        }
        int i = 0;
        while (i < ar1.size()) {
            g.drawImage(ar1.get(i), startX, startY, width, height, null);
            startX = startX + width;
            if (startX >= 1200) {
                startX = 300;
                startY = startY + (height) - 5;
            }
            i++;
        }
        startX = 300;
        startY = 190;
    }

    public void refresh() {
        removeAll();
        add(back);
        add(exit);
        showDecks();

    }


    private void showDecks() {
        if (Gamestate.getPlayer() != null) {
            int i = 0;
            buttons = new ArrayList<>();
            for (Map.Entry<String, Deck> entry : Gamestate.getPlayer().getAllDecks().entrySet()) {
                String s = entry.getKey();
                JButton button = new JButton(s);
                button.setName(s);
                button.setBounds(1265, 65 + (i * 70), 280, 60);
                button.setFont(f2);
                button.setFocusable(false);
                button.addActionListener(this);
                this.add(button);
                buttons.add(button);
                i++;
            }
        }
    }

    private void updateSelectedDeck(String name) {
        if (Gamestate.getPlayer() != null) {
            selectedDeck = new Deck();
            selectedDeck = Deck.changeSelectedDeck(Gamestate.getPlayer().getAllDecks().get(name));
            ar1 = new ArrayList<>();
            ArrayList<Carts> ar3 = selectedDeck.getDeck();
            for (Carts carts : ar3) {
                BufferedImage bf = cardPics.get(carts.toString());
                ar1.add(bf);
            }
        }
    }

    private double calculateAvgMana(Deck deck) {
        ArrayList<Cards> ar = Deck.UpdateDeck(deck.getDeck());
        double i = 0;
        for (Cards card : ar) {
            i = i + card.getManaCost();
        }
        i = i / ar.size();
        BigDecimal bd = new BigDecimal(Double.toString(i));
        bd = bd.setScale(3, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        if (src == back) {
            Update.saveAndUpdate();
            MyFrame.getInstance().changePanel("menu");
        } else if (src == exit) {
            JsonBuilders.PlayerJsonBuilder(Gamestate.getPlayer().getUsername(), Gamestate.getPlayer());
            System.exit(0);
        } else {
            for (JButton button : buttons) {
                if (src.getName().equalsIgnoreCase(button.getName())) {
                    updateSelectedDeck(button.getName());
                    revalidate();
                    repaint();
                }
            }
        }
    }
}
