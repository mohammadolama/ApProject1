package View.Panels;

import Model.Enums.Carts;
import Model.Enums.Type;
import Model.Player;
import Model.CardModelView;
import Controller.RequestHandler;
import Configs.ConfigsLoader;
import Configs.StatusConfig;
import Model.Deck;
import MainLogic.Gamestate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.*;

import static View.Panels.Constants.*;

public class StatusPanel extends JPanel implements ActionListener {

    private static ArrayList<BufferedImage> ar1 = new ArrayList<>();
    private static ArrayList<JButton> buttons = new ArrayList<>();

    private Deck selectedDeck;
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
        showDecks();
        setLayout(null);
        back = new JButton();
        back.setIcon(gameIcon.get("back"));
        back.setFocusable(false);
        back.addActionListener(this);
        back.setBounds(config.getBackX(), config.getBackY(), config.getBackSize(), config.getBackSize());
        back.setContentAreaFilled(false);
        back.setRolloverEnabled(false);
        back.setBorderPainted(false);
        add(back);


        deleteAccount = new JButton();
        deleteAccount.setIcon(gameIcon.get("delete"));
        deleteAccount.setBounds(config.getDeleteX(), config.getBackY(), config.getBackSize(), config.getBackSize());
        deleteAccount.setFocusable(false);
        deleteAccount.addActionListener(this);
        add(deleteAccount);

        exit.addActionListener(this);
        exit.setIcon(gameIcon.get("exit"));
        exit.setBounds(config.getExitX(), config.getBackY(), config.getBackSize(), config.getBackSize());
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
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.YELLOW);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        BufferedImage status= gamePics.get("status");
        BufferedImage status2=gamePics.get("status2");
        g2d.drawImage(status, 0, 0, 1600, 1000, null);
        g2d.drawImage(status2, 0, -55, 300, 970, null);
        g2d.drawLine(1250, 0, 1250, 1000);
        g2d.setFont(f2.deriveFont(40.0f));
        g2d.drawString("My Decks", 1330, 40);
        g2d.setFont(f2);
        Player player=RequestHandler.SendRequest.Player.response(null);
        g2d.drawString("Username: " + player.getUsername(), config.getInfoX(), config.getInfoY());
        g2d.drawString("Level   : " + player.getLevel(), config.getInfoX(), config.getInfoY() + 100);
        g2d.drawString("EXP     : " + player.getExp(), config.getInfoX(), config.getInfoY() + 200);
        g2d.drawString("Wallet: " + player.getMoney(), config.getInfoX(), config.getInfoY() + 300);
        g2d.drawString("Deck: " + player.getSelectedDeck().getName(), config.getInfoX(), config.getInfoY() + 400);
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
        CardModelView view= RequestHandler.SendRequest.PureModelView.response(cards);
        if (view.getType().equals(Type.Minion)) {
            int x = xe + (config.getWidth() / 6) - 10;
            int y = ye + (config.getHeight() / 6) + 10;
            g.drawString(view.getManaCost()+"", x, y);
            g.drawString(view.getDamage()+"", x, y + (config.getHeight() * 4 / 5) - 10);
            g.drawString(view.getHp()+"", (x + config.getWidth() * 4 / 5) - 15, y + (config.getHeight() * 4 / 5) - 10);
        } else if (view.getType().equals(Type.Weapon)) {
            int x = xe + (config.getWidth() / 6) - 10;
            int y = ye + (config.getHeight() / 6);
            g.drawString(view.getManaCost()+"", x, y);
            g.drawString(view.getDamage()+"", x, y + (config.getHeight() * 4 / 5));
            g.drawString(view.getHp()+"", (x + config.getWidth() * 4 / 5) - 10, y + (config.getHeight() * 4 / 5));
        } else if (view.getType().equals(Type.Spell)) {
            int x = xe + (config.getWidth() / 6) - 10;
            int y = ye + (config.getHeight() / 6);
            g.drawString(view.getManaCost()+"", x, y);
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
                    g2d.setColor(Color.YELLOW);
                    g2d.drawString("X2", (config.getStartX() + config.getWidth() / 2), (config.getStartY() + config.getHeight() - 5));
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
            ArrayList<String> ar = RequestHandler.SendRequest.BestDecks.response(null);
            for (String string : ar) {
                JButton button = new JButton(string);
                button.setName(string);
                button.setBounds(config.getDeckX(), 65 + (i * 70), 280, 60);
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
            selectedDeck = RequestHandler.SendRequest.CloneDeck.response(name);
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
            RequestHandler.SendRequest.Log.response("Click_Button : Exit Button");
            RequestHandler.SendRequest.SaveAndUpdate.response(null);
            ar1 = null;
            selectedDeck = null;
            RequestHandler.SendRequest.Log.response("Navigate : Main Menu");
            RequestHandler.SendRequest.VisiblePanel.response("menu");
        } else if (src == deleteAccount) {
            RequestHandler.SendRequest.Log.response("Click_Button : DeleteAccount Button");
            RequestHandler.SendRequest.DeleteAccount.response(null);
        } else if (src == exit) {
            RequestHandler.SendRequest.Log.response("Click_Button : Exit Button");
            RequestHandler.SendRequest.Exit.response(null);
        } else {
            for (JButton button : buttons) {
                if (src.getName().equalsIgnoreCase(button.getName())) {
                    updateSelectedDeck(button.getName());
                    RequestHandler.SendRequest.FrameRender.response(null);
                }
            }
        }
    }
}
