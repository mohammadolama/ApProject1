package GUI;

import Enums.Carts;
import Main.Deck;
import Main.Gamestate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.*;

import static GUI.Constants.*;

public class StatusPanel extends JPanel implements ActionListener {

    private static ArrayList<BufferedImage> ar1;
    private static ArrayList<JButton> buttons = new ArrayList<>();

    private Deck selectedDeck;


    private int startX = 300;
    private int startY = 190;
    private int width = 180;
    private int height = 250;


    private JButton back;

    private static StatusPanel statusPanel = new StatusPanel();

    private StatusPanel() {
        showDecks();
        buttons.get(0).doClick();
        setLayout(null);
        back = new JButton("Back");
        back.setFocusable(false);
        back.setFont(f2);
        back.addActionListener(this);
        back.setBounds(1265, 850, 280, 60);
        add(back);


        requestFocus();
        setFocusable(true);
        setBackground(Color.LIGHT_GRAY);
        revalidate();
        repaint();
    }

    public static StatusPanel getInstance() {
        return statusPanel;
    }


    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.YELLOW);
        g.drawImage(status, 0, 0, 1600, 1000, null);
        g.drawImage(status2, 0, -55, 300, 970, null);
        g.drawLine(1250, 0, 1250, 1000);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(f2.deriveFont(40.0f));
        g2d.drawString("My Decks", 1330, 40);
        g2d.setColor(Color.YELLOW);
        g2d.setFont(f2);
        g2d.drawString("Username: "+Gamestate.getPlayer().getUsername() , 50 , 200);
        g2d.drawString("Level   : "+Gamestate.getPlayer().getLevel() , 50 , 300);
        g2d.drawString("EXP     : "+Gamestate.getPlayer().getExp() , 50 , 400);
        g2d.drawString("Wallet: "+Gamestate.getPlayer().getMoney() , 50 , 500);

        g2d.drawLine(300, startY - 10, 1250, startY - 10);
        g2d.setFont(f2);
        g2d.drawString("Name          : "+selectedDeck.getName(), startX + 30, 40);
        g2d.drawString("Total plays : "+selectedDeck.getTotalPlays(), startX + 30, 80);
        g2d.drawString("Total wins  : "+selectedDeck.getTotalWins(), startX + 30, 120);
        g2d.drawString("Win Rate    : "+selectedDeck.winRate(), startX + 30, 160);
        g2d.drawImage(heroPics.get(selectedDeck.getHero().getName().toLowerCase()), startX + 400, 0, 150, 200, null);
//        g2d.drawImage(cardPics.get(selectedDeck.getMostUsedCard().toString().toLowerCase()), startX + 700, 0, 130, 180, null);
//
        int i = 0;
        while (i < ar1.size()) {
            g.drawImage(ar1.get(i), startX, startY, width, height, null);
            startX = startX + width;
            if (startX >= 1200) {
                startX = 300;
                startY = startY + (height)-5;
            }
            i++;
        }
        startX = 300;
        startY = 190;
        revalidate();
    }


    public static void pictures() {
        if (Gamestate.getPlayer() != null) {
            ar1 = new ArrayList<>();
            ArrayList<Carts> ar3 = Gamestate.getPlayer().getSelectedDeck().getDeck();
            for (Carts carts : ar3) {
                BufferedImage bf = cardPics.get(carts.toString());
                ar1.add(bf);
            }
        }
    }

    public void updatePictures(String name) {
        if (Gamestate.getPlayer() != null) {
            ar1 = new ArrayList<>();
            ArrayList<Carts> ar3 = Gamestate.getPlayer().getAllDecks().get(name).getDeck();
            for (Carts carts : ar3) {
                BufferedImage bf = cardPics.get(carts.toString());
                ar1.add(bf);
            }
        }
        repaint();
    }

    private void showDecks() {
        if (Gamestate.getPlayer() != null) {
            int i = 0;
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
            selectedDeck = Deck.changeSelectedDeck(Gamestate.getPlayer().getAllDecks().get(name));
            ar1 = new ArrayList<>();
            ArrayList<Carts> ar3 = selectedDeck.getDeck();
            for (Carts carts : ar3) {
                BufferedImage bf = cardPics.get(carts.toString());
                ar1.add(bf);
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        if (src == back) {
            MyFrame.getInstance().changePanel("menu");
            repaint();
            revalidate();
        }
        else {
            for (JButton button : buttons) {
                if (src == button) {
                    updateSelectedDeck(button.getName());
                            System.out.println(selectedDeck.getName());

                    revalidate();
                    repaint();

                }
            }
        }
    }
}
