package Client.View.View.Panels;


import Client.Controller.RequestHandler;
import Client.Controller.Requests.BestDecksRequest;
import Client.Controller.Requests.DeckModelRequest;
import Client.Controller.Requests.PlayerModelRequest;
import Client.Controller.Requests.PureModelViewRequest;
import Client.Controller.Responses;
import Client.Model.CardModelView;
import Client.Model.DeckModel;
import Client.Model.Enums.Carts;
import Client.Model.Enums.Type;
import Client.Model.PlayerModel;
import Client.View.Configs.ConfigsLoader;
import Client.View.Configs.StatusConfig;
import Client.View.View.Panels.Listeners.StatusListener;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static Client.View.View.Panels.Constants.*;

public class StatusPanel extends JPanel {

    private ArrayList<BufferedImage> ar1 = new ArrayList<>();
    private ArrayList<JButton> buttons = new ArrayList<>();

    private DeckModel selectedDeck;
    private StatusConfig config;
    private PlayerModel player;

    private final JButton back;
    private final JButton exit = new JButton();
    private final JButton deleteAccount;
    private List<Carts> card;
    private static final StatusPanel statusPanel = new StatusPanel();
    private final StatusListener sl = new StatusListener(this);

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
        back.addActionListener(sl);
        back.setBounds(config.getBackX(), config.getBackY(), config.getBackSize(), config.getBackSize());
        back.setContentAreaFilled(false);
        back.setRolloverEnabled(false);
        back.setBorderPainted(false);
        add(back);


        deleteAccount = new JButton();
        deleteAccount.setIcon(gameIcon.get("delete"));
        deleteAccount.setBounds(config.getDeleteX(), config.getBackY(), config.getBackSize(), config.getBackSize());
        deleteAccount.setFocusable(false);
        deleteAccount.addActionListener(sl);
        add(deleteAccount);

        exit.addActionListener(sl);
        exit.setIcon(gameIcon.get("exit"));
        exit.setBounds(config.getExitX(), config.getBackY(), config.getBackSize(), config.getBackSize());
        exit.setFocusable(false);
        exit.setContentAreaFilled(false);
        exit.setRolloverEnabled(false);
        exit.setBorderPainted(false);
        add(exit);
        setFocusable(false);
        showDecks();

        requests();

    }

    public static StatusPanel getInstance() {
        return statusPanel;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.YELLOW);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        BufferedImage status = gamePics.get("status");
        BufferedImage status2 = gamePics.get("status2");
        g2d.drawImage(status, 0, 0, 1600, 1000, null);
        g2d.drawImage(status2, 0, -55, 300, 970, null);
        g2d.drawLine(1250, 0, 1250, 1000);
        g2d.setFont(f2.deriveFont(40.0f));
        g2d.drawString("My Decks", 1330, 40);
        g2d.setFont(f2);


        g2d.drawString("Username: " + player.getName(), config.getInfoX(), config.getInfoY());
        g2d.drawString("Level   : " + player.getLevel(), config.getInfoX(), config.getInfoY() + 100);
        g2d.drawString("EXP     : " + player.getExp(), config.getInfoX(), config.getInfoY() + 200);
        g2d.drawString("Wallet: " + player.getMoney(), config.getInfoX(), config.getInfoY() + 300);
        g2d.drawString("Deck: " + player.getSelectedDeckName(), config.getInfoX(), config.getInfoY() + 400);
        g2d.drawLine(300, config.getStartY() - 10, 1250, config.getStartY() - 10);
        g2d.setFont(f2.deriveFont(18.0f));
        if (selectedDeck != null) {
            g2d.drawString("Name          : " + selectedDeck.getName(), config.getStartX() + 30, 20);
            g2d.drawString("Total plays : " + selectedDeck.getTotalPlays(), config.getStartX() + 30, 50);
            g2d.drawString("Total wins  : " + selectedDeck.getTotalWins(), config.getStartX() + 30, 80);
            g2d.drawString("Win Rate    : " + selectedDeck.getWinRate(), config.getStartX() + 30, 110);
            g2d.drawString("Average Mana    : " + selectedDeck.getAvarageMana(), config.getStartX() + 30, 140);
            g2d.drawString("Earned Cup      : " + selectedDeck.getCup(), config.getStartX() + 30, 170);
            g2d.drawImage(heroPics.get(selectedDeck.getHero().toLowerCase()), config.getStartX() + 400, 0, 150, 200, null);
            g2d.drawImage(cardPics.get(selectedDeck.getMostUsedcart().toString().toLowerCase()), config.getStartX() + 700, 0, 130, 180, null);
        }
        drawCards(g2d);
    }

    private void requests() {
        RequestHandler.getInstance().sendRequest(new PlayerModelRequest());
        player = Responses.getInstance().getPlayer();
    }

    private void drawCardInfo(Graphics2D g, String cards, int xe, int ye) {
        g.setFont(fantasy.deriveFont(40.0f));
        g.setColor(Color.WHITE);
        RequestHandler.getInstance().sendRequest(new PureModelViewRequest(cards));
        CardModelView view = Responses.getInstance().getCardModelView();
        if (view.getType().equals(Type.Minion)) {
            int x = xe + (config.getWidth() / 6) - 10;
            int y = ye + (config.getHeight() / 6) + 10;
            g.drawString(view.getManaCost() + "", x, y);
            g.drawString(view.getDamage() + "", x, y + (config.getHeight() * 4 / 5) - 10);
            g.drawString(view.getHp() + "", (x + config.getWidth() * 4 / 5) - 15, y + (config.getHeight() * 4 / 5) - 10);
        } else if (view.getType().equals(Type.Weapon)) {
            int x = xe + (config.getWidth() / 6) - 10;
            int y = ye + (config.getHeight() / 6);
            g.drawString(view.getManaCost() + "", x, y);
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
                if (i < ar1.size() - 1 && selectedDeck.getList().get(i).toString().equalsIgnoreCase(selectedDeck.getList().get(i + 1).toString())) {
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
        int i = 0;
        buttons = new ArrayList<>();

        RequestHandler.getInstance().sendRequest(new BestDecksRequest());
        List<String> deckNames = Responses.getInstance().getBestDecks();

        for (String string : deckNames) {
            JButton button = new JButton(string);
            button.setName(string);
            button.setBounds(config.getDeckX(), 65 + (i * 70), 280, 60);
            button.setFont(f2);
            button.setFocusable(false);
            button.addActionListener(sl);
            this.add(button);
            buttons.add(button);
            i++;
        }

    }

    public void updateSelectedDeck(String name) {
        RequestHandler.getInstance().sendRequest(new DeckModelRequest(name));
        selectedDeck = Responses.getInstance().getDeckModel();
        ar1 = new ArrayList<>();
        card = selectedDeck.getList();
        for (Carts carts : card) {
            BufferedImage bf = cardPics.get(carts.toString());
            ar1.add(bf);
        }
    }

    public void setAr1(ArrayList<BufferedImage> ar1) {
        this.ar1 = ar1;
    }

    public ArrayList<JButton> getButtons() {
        return buttons;
    }


    public void setSelectedDeck(DeckModel selectedDeck) {
        this.selectedDeck = selectedDeck;
    }

    public JButton getBack() {
        return back;
    }


    public JButton getExit() {
        return exit;
    }

    public JButton getDeleteAccount() {
        return deleteAccount;
    }

}
