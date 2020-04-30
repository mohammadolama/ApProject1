package GUI.Panels;

import AllCards.Minions;
import AllCards.Spell;
import AllCards.Weapon;
import Enums.Carts;
import GUI.Configs.ConfigsLoader;
import GUI.Configs.StatusConfig;
import G_L_Interface.Update;
import Main.Deck;
import Main.Gamestate;
import Util.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.*;

import static GUI.Panels.Constants.*;

public class StatusPanel extends JPanel implements ActionListener {

    private static ArrayList<BufferedImage> ar1 = new ArrayList<>();
    private static ArrayList<JButton> buttons = new ArrayList<>();

    private Deck selectedDeck;
    private Admin admin;
    private StatusConfig config;

    private JButton back;
    private JButton exit = new JButton();
    private JButton deleteAccount;
    private ArrayList<Carts> card;
    private static StatusPanel statusPanel = new StatusPanel();

    private void initConfig() {
        config = ConfigsLoader.getInstance().getStatusConfig();
    }

    private StatusPanel() {
        initConfig();
        admin = Admin.getInstance();
        showDecks();
        setLayout(null);
        back = new JButton();
        back.setIcon(gameIcon.get("back"));
        back.setFocusable(false);
        back.addActionListener(this);
        back.setBounds(1430, 890, 60, 60);
        back.setContentAreaFilled(false);
        back.setRolloverEnabled(false);
        back.setBorderPainted(false);
        add(back);


        deleteAccount = new JButton();
        deleteAccount.setIcon(gameIcon.get("delete"));
//        deleteAccount.setBackground(Color.ORANGE);
        deleteAccount.setBounds(1370, 890, 50, 50);
//        deleteAccount.setContentAreaFilled(false);
//        deleteAccount.setBorderPainted(false);
        deleteAccount.setFocusable(false);
//        deleteAccount.setRolloverEnabled(false);
        deleteAccount.addActionListener(this);
        add(deleteAccount);

        exit.addActionListener(this);
        exit.setIcon(gameIcon.get("exit"));
        exit.setBounds(1500, 890, 60, 60);
        exit.setFocusable(false);
        exit.setContentAreaFilled(false);
        exit.setRolloverEnabled(false);
        exit.setBorderPainted(false);
        add(exit);
        setFocusable(false);
        showDecks();

    }

    public static StatusPanel getInstance() {
        return statusPanel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        System.out.println("asdadas");
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.YELLOW);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(gamePics.get("status"), 0, 0, 1600, 1000, null);
        g2d.drawImage(gamePics.get("status2"), 0, -55, 300, 970, null);
        g2d.drawLine(1250, 0, 1250, 1000);
        g2d.setFont(f2.deriveFont(40.0f));
        g2d.drawString("My Decks", 1330, 40);
        g2d.setFont(f2);
        g2d.drawString("Username: " + Gamestate.getPlayer().getUsername(), 50, 200);
        g2d.drawString("Level   : " + Gamestate.getPlayer().getLevel(), 50, 300);
        g2d.drawString("EXP     : " + Gamestate.getPlayer().getExp(), 50, 400);
        g2d.drawString("Wallet: " + Gamestate.getPlayer().getMoney(), 50, 500);
        g2d.drawString("Deck: " + Gamestate.getPlayer().getSelectedDeck().getName(), 50, 600);
        g2d.drawLine(300, config.getStartY() - 10, 1250, config.getStartY() - 10);
        g2d.setFont(f2.deriveFont(18.0f));
        if (selectedDeck != null) {
            g2d.drawString("Name          : " + selectedDeck.getName(), config.getStartX() + 30, 40);
            g2d.drawString("Total plays : " + selectedDeck.getTotalPlays(), config.getStartX() + 30, 70);
            g2d.drawString("Total wins  : " + selectedDeck.getTotalWins(), config.getStartX() + 30, 100);
            g2d.drawString("Win Rate    : " + selectedDeck.winRate(), config.getStartX() + 30, 130);
            g2d.drawString("Average Mana    : " + calculateAvgMana(selectedDeck), config.getStartX() + 30, 160);
            g2d.drawImage(heroPics.get(selectedDeck.getHero().getName().toLowerCase()), config.getStartX() + 400, 0, 150, 200, null);
            g2d.drawImage(cardPics.get(selectedDeck.getMostUsedCard().toString().toLowerCase()), config.getStartX() + 700, 0, 130, 180, null);
        }

        drawCards(g2d);
    }

    private void drawCardInfo(Graphics2D g, String cards, int xe, int ye) {
        g.setFont(fantasy.deriveFont(40.0f));
        g.setColor(Color.WHITE);
        if (admin.getCardOf(cards) instanceof Minions) {
            int x = xe + (config.getWidth() / 6) - 10;
            int y = ye + (config.getHeight() / 6) + 10;
            g.drawString(admin.defaultCardMana(admin.getCardOf(cards)) + "", x, y);
            g.drawString((admin.cardMAttack((Minions) admin.getCardOf(cards)) + ""), x, y + (config.getHeight() * 4 / 5) - 10);
            g.drawString((admin.cardHp((Minions) admin.getCardOf(cards)) + ""), (x + config.getWidth() * 4 / 5) - 15, y + (config.getHeight() * 4 / 5) - 10);
        } else if (admin.getCardOf(cards) instanceof Weapon) {
            int x = xe + (config.getWidth() / 6) - 10;
            int y = ye + (config.getHeight() / 6);
            g.drawString(admin.defaultCardMana(admin.getCardOf(cards)) + "", x, y);
            g.drawString((admin.cardWAttack((Weapon) admin.getCardOf(cards)) + ""), x, y + (config.getHeight() * 4 / 5));
            g.drawString((admin.cardDurability((Weapon) admin.getCardOf(cards)) + ""), (x + config.getWidth() * 4 / 5) - 10, y + (config.getHeight() * 4 / 5));
        } else if (admin.getCardOf(cards) instanceof Spell) {
            int x = xe + (config.getWidth() / 6) - 10;
            int y = ye + (config.getHeight() / 6);
            g.drawString(admin.defaultCardMana(admin.getCardOf(cards)) + "", x, y);
        }
    }

    private void drawCards(Graphics2D g2d) {
        if (ar1 != null) {
            int i = 0;
            while (i < ar1.size()) {
                g2d.drawImage(ar1.get(i), config.getStartX(), config.getStartY(), config.getWidth(), config.getHeight(), null);
                drawCardInfo(g2d, card.get(i).toString().toLowerCase(), config.getStartX(), config.getStartY());
                if (i < ar1.size() - 1 && selectedDeck.getDeck().get(i).toString().equalsIgnoreCase(selectedDeck.getDeck().get(i + 1).toString())) {
                    g2d.setFont(f2.deriveFont(30.f));
                    g2d.setColor(Color.CYAN);
                    g2d.drawString("X2", (config.getStartX() + 70), (config.getStartY() + config.getHeight() - 5));
                    i++;
                }
                config.setStartX(config.getStartX() + config.getWidth());
                if (config.getStartX() >= 1200) {
                    config.setStartX(300);
                    config.setStartY(config.getStartY() + (config.getHeight()) - 5);
                }
                i++;
            }
            config.setStartX(300);
            config.setStartY(190);
        }
    }

    public void refresh() {
        removeAll();
        add(back);
        add(exit);
        add(deleteAccount);
        showDecks();

    }


    private void showDecks() {
        if (Gamestate.getPlayer() != null) {
            int i = 0;
            buttons = new ArrayList<>();
            System.out.println();
            ArrayList<String> ar = Deck.bestDeck(admin.player());
            for (String string : ar) {
                String s = string;
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
            card = selectedDeck.getDeck();
            for (Carts carts : card) {
                BufferedImage bf = cardPics.get(carts.toString());
                ar1.add(bf);
            }
        }
    }

    private double calculateAvgMana(Deck deck) {
        return deck.avarageMana();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        if (src == back) {
            ShopPanel.getInstance().grabFocus();
            admin.Log("Click_Button : Exit Button");
            Update.saveAndUpdate();
            ar1 = null;
            selectedDeck = null;
            admin.Log("Navigate : Main Menu");
            MyFrame.getInstance().changePanel("menu");
            MyFrame.getInstance().requestFocus();
        } else if (src == deleteAccount) {
            admin.Log("Click_Button : DeleteAccount Button");
            admin.deleteAccount();
        } else if (src == exit) {
            admin.Log("Click_Button : Exit Button");
            admin.exit();
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
