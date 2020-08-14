package Client.View.View.Panels;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.PriceRequest;
import Client.Controller.Requests.ProperCardsRequest;
import Client.Controller.Requests.PureModelViewRequest;
import Client.Controller.Requests.WalletRequest;
import Client.Controller.Responses;
import Client.Model.CardModelView;
import Client.Model.Enums.Type;
import Client.Model.Images;
import Client.View.Configs.ConfigsLoader;
import Client.View.Configs.ShopConfig;
import Client.View.View.Panels.Listeners.ShopListeners.ShopAction;
import Client.View.View.Panels.Listeners.ShopListeners.ShopChange;
import Client.View.View.Panels.Listeners.ShopListeners.ShopDocument;
import Client.View.View.Panels.Listeners.ShopListeners.ShopMouse;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static Client.View.View.Panels.Constants.*;

public class ShopPanel extends JPanel {

    private static final ShopPanel shoppanel = new ShopPanel();


    private static ArrayList<BufferedImage> bufferedImages;


    private final JButton minionButton = new JButton("Minion");
    private final JButton spellButton = new JButton("Spell");
    private final JButton weaponButton = new JButton("Weapon");
    private final JButton allbutton = new JButton("All");
    private final JButton backButton = new JButton();
    private final JButton buyActivatedButton = new JButton("Buy");
    private final JButton sellActivatedButton = new JButton("Sell");
    private JButton exit = new JButton();
    private final JButton buyButton;
    private final JButton sellButton;
    private final ArrayList<Images> images;
    private ArrayList<CardModelView> cards;
    private final JLabel walletLabel;
    private final JTextField searchField;
    private final JSlider manaFilter;
    private final ShopAction sa = new ShopAction(this);
    private final ShopChange sc = new ShopChange(this);
    private final ShopMouse sm = new ShopMouse(this);


    private boolean buyActivated = true;
    private boolean clicked;
    private boolean mate;
    private long wallet;


    private String name1;

    private ShopConfig config;

    private void initConfig() {
        config = ConfigsLoader.getInstance().getShopConfig();
    }

    private ShopPanel() {
        initConfig();
        setLayout(null);
        images = new ArrayList<>();
        RequestHandler.getInstance().sendRequest(new ProperCardsRequest(1));
        cards = Responses.getInstance().getModelviewList();
        pictures(cards);


        allbutton.addActionListener(sa);
        allbutton.addMouseListener(sm);
        allbutton.setBounds(config.getStartX1(), config.getStartY1(), config.getMinionWidth(), config.getMininHeight());
        allbutton.setFont(f2);
        allbutton.setFocusable(false);

        minionButton.addActionListener(sa);
        minionButton.addMouseListener(sm);
        minionButton.setBounds(config.getStartX1(), config.getStartY1() + config.getSpacing1(), config.getMinionWidth(), config.getMininHeight());
        minionButton.setFont(f2);
        minionButton.setFocusable(false);


        spellButton.addActionListener(sa);
        spellButton.addMouseListener(sm);
        spellButton.setBounds(config.getStartX1(), config.getStartY1() + 2 * config.getSpacing1(), config.getMinionWidth(), config.getMininHeight());
        spellButton.setFocusable(false);
        spellButton.setFont(f2);


        weaponButton.addActionListener(sa);
        weaponButton.addMouseListener(sm);
        weaponButton.setBounds(config.getStartX1(), config.getStartY1() + 3 * config.getSpacing1(), config.getMinionWidth(), config.getMininHeight());
        weaponButton.setFocusable(false);
        weaponButton.setFont(f2);


        backButton.setIcon(gameIcon.get("back"));
        backButton.addActionListener(sa);
        backButton.setBounds(config.getStartX1() + 75, config.getStartY1() + 6 * config.getSpacing1(), config.getBacksize(), config.getBacksize());
        backButton.setFocusable(false);
        backButton.setContentAreaFilled(false);
        backButton.setRolloverEnabled(false);
        backButton.setBorderPainted(false);

        exit.addActionListener(sa);
        exit.setIcon(gameIcon.get("exit"));
        exit.setBounds(config.getStartX1() + 150, config.getStartY1() + 6 * config.getSpacing1(), config.getBacksize(), config.getBacksize());
        exit.setFocusable(false);
        exit.setContentAreaFilled(false);
        exit.setRolloverEnabled(false);
        exit.setBorderPainted(false);


        buyActivatedButton.addActionListener(sa);
        buyActivatedButton.addMouseListener(sm);
        buyActivatedButton.setBounds(700, 880, config.getMinionWidth(), config.getMininHeight());
        buyActivatedButton.setFocusable(false);
        buyActivatedButton.setBackground(Color.YELLOW);
        buyActivatedButton.setFont(f2);


        sellActivatedButton.addActionListener(sa);
        sellActivatedButton.addMouseListener(sm);
        sellActivatedButton.setBounds(950, 880, config.getMinionWidth(), config.getMininHeight());
        sellActivatedButton.setFocusable(false);
        sellActivatedButton.setFont(f2);


        manaFilter = new JSlider(1, 11, 11);
        manaFilter.setMinimum(1);
        manaFilter.setMaximum(11);
        manaFilter.setBackground(new Color(220, 222, 136));
        manaFilter.setFocusable(false);
        manaFilter.setPaintTicks(true);
        manaFilter.setMajorTickSpacing(10);
        manaFilter.setMinorTickSpacing(5);
        manaFilter.setLabelTable(getTable());
        manaFilter.setPaintLabels(true);
        manaFilter.addChangeListener(sc);
        manaFilter.setFont(f2.deriveFont(30.0f));
        manaFilter.setBounds(30, 880, 600, 50);


        searchField = new JTextField();
        searchField.setFont(f2.deriveFont(25.0f));
        searchField.setBounds(150, 20, 350, 50);
        searchField.setFocusable(true);
        searchField.getDocument().addDocumentListener(new ShopDocument(this));
        add(searchField);
        RequestHandler.getInstance().sendRequest(new WalletRequest());
        wallet = Responses.getInstance().getWallet();
        walletLabel = new JLabel(wallet + "   AP");
        walletLabel.setBackground(Color.LIGHT_GRAY);
        walletLabel.setFont(f2.deriveFont(28.0f));
        walletLabel.setForeground(Color.yellow);
        walletLabel.setBounds(1100, 20, 350, 50);
        walletLabel.setFocusable(false);


        buyButton = new JButton("Buy");
        buyButton.setFocusable(false);
        buyButton.setEnabled(true);
        buyButton.setFont(f2.deriveFont(30.0f));
        buyButton.setBackground(Color.orange);
        buyButton.setBounds(720, 550, 200, 50);
        buyButton.addActionListener(sa);

        sellButton = new JButton("Sell");
        sellButton.setFocusable(false);
        sellButton.setEnabled(true);
        sellButton.setFont(f2.deriveFont(30.0f));
        sellButton.setBackground(Color.orange);
        sellButton.setBounds(790, 550, 200, 50);
        sellButton.addActionListener(sa);

        addMouseListener(sm);
    }

    public static ShopPanel getInstance() {
        return shoppanel;
    }

    private void refresh() {
        removeAll();
        add(allbutton);
        add(minionButton);
        add(spellButton);
        add(weaponButton);
        add(backButton);
        add(buyActivatedButton);
        add(sellActivatedButton);
        add(manaFilter);
        add(searchField);
        add(walletLabel);
        add(exit);
    }


    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        refresh();
        g.clearRect(0, 0, 1600, 1000);
        g2d.setFont(f2.deriveFont(30.0f));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.white);
        BufferedImage image=gamePics.get("store");
        g2d.drawImage(image, 0, 0, 1600, 1000, null);
        g2d.drawLine(0, config.getStartY1() - 200, config.getStartX1() - 10, config.getStartY1() - 197);
        g2d.drawLine(config.getStartX1() - 10, 0, config.getStartX1() - 10, 1000);
        g2d.drawLine(0, 870, config.getStartX1() - 10, 870);
        g2d.setColor(Color.yellow);
        g2d.drawString("Search :", 30, 55);
        drawImages(g2d);

        if (clicked) {
            drawBiggerImage(g2d);
        }
        searchField.requestFocus();
    }

    private void drawBiggerImage(Graphics2D g2d) {
        if (!mate) {
            removeAll();
            images.clear();
            g2d.setColor(new Color(222, 222, 222, 200));
            g2d.fillRect(0, 0, 1600, 1000);
            g2d.setColor(Color.white);
            mate = true;
            if (buyActivated) {
                buyButton.setEnabled(true);
                buyButton.setBackground(Color.ORANGE);
                g2d.setFont(f2.deriveFont(50.0f));
                add(buyButton);
            } else {
                sellButton.setEnabled(true);
                sellButton.setBackground(Color.ORANGE);
                add(sellButton);
            }
            g2d.setFont(f2.deriveFont(40.0f));
            g2d.setColor(Color.red);
            RequestHandler.getInstance().sendRequest(new PriceRequest(name1.toLowerCase()));
            long price = Responses.getInstance().getPrice();
            String className = Responses.getInstance().getClassName();
            g2d.drawString("Price : " + price, 720, config.getPriceY());
            g2d.drawString("Class : ", 720, config.getPriceY() - 40);
            g2d.setColor(Color.BLUE);
            g2d.drawString(className, 850, config.getPriceY() - 40);
        }
        g2d.drawImage(cardPics.get(name1), 300, 220, null);
        drawCardInfo(g2d, name1, 300, 220);

    }

    private void drawCardInfo(Graphics2D g, String cards, int xe, int ye) {
        g.setFont(fantasy.deriveFont(60.0f));
        g.setColor(Color.WHITE);
        RequestHandler.getInstance().sendRequest(new PureModelViewRequest(cards));
        CardModelView view = Responses.getInstance().getCardModelView();
        if (view.getType().equals(Type.Minion)) {
            int x = xe + 40;
            int y = ye + 95;
            g.drawString(view.getManaCost() + "", x, y);
            g.drawString(view.getDamage() + "", x, y + 445);
            g.drawString((view.getHp() + ""), x + 300, y + 440);
        } else if (view.getType().equals(Type.Weapon)) {
            int x = xe + 45;
            int y = ye + 65;
            g.drawString(view.getManaCost() + "", x, y);
            g.drawString((view.getDamage() + ""), x, y + 445);
            g.drawString((view.getHp() + ""), x + 300, y + 440);
        } else if (view.getType().equals(Type.Spell)) {
            int x = xe + 45;
            int y = ye + 60;
            g.drawString(view.getManaCost() + "", x, y);
        }
    }


    private void drawImages(Graphics2D g) {
        int i = 0;
        while (i < cards.size()) {
            g.drawImage(bufferedImages.get(i), config.getStartX2(), config.getStartY2(), config.getWidth(), config.getHeight(), null);
            images.add(new Images(cards.get(i).getName().toLowerCase(), config.getStartX2(), config.getStartY2(), config.getWidth(), config.getHeight() , i));
            config.setStartX2(config.getStartX2() + config.getWidth());
            if (config.getStartX2() >= 1300) {
                config.setStartX2(20);
                config.setStartY2(config.getStartY2() + (config.getHeight()));
            }
            i++;
        }
        config.setStartX2(20);
        config.setStartY2(80);
        mate = false;
    }

    public void pictures(ArrayList<CardModelView> ar) {
        bufferedImages = new ArrayList<>();

        for (CardModelView cards1 : ar) {
            BufferedImage bf = cardPics.get(cards1.getName().toLowerCase());
            bufferedImages.add(bf);
        }
    }


    public void drawBigger(String st) {
        clicked = true;
        name1 = st;
        repaint();
    }


    public void BuySellChanger() {
        images.clear();
        RequestHandler.getInstance().sendRequest(new ProperCardsRequest(buyActivated ? 1 : 2));
        cards = Responses.getInstance().getModelviewList();
        pictures(cards);
        repaint();
    }

    public void revalidateCards(Boolean buyActivated) {
        RequestHandler.getInstance().sendRequest(new ProperCardsRequest(buyActivated ? 1 : 2));
        cards = Responses.getInstance().getModelviewList();
        pictures(cards);
        images.clear();
        RequestHandler.getInstance().sendRequest(new WalletRequest());
        wallet = Responses.getInstance().getWallet();
        walletLabel.setText(wallet + "   AP");
        clicked = false;
        repaint();
    }

    public JButton getMinionButton() {
        return minionButton;
    }

    public JButton getSpellButton() {
        return spellButton;
    }

    public JButton getWeaponButton() {
        return weaponButton;
    }

    public JButton getAllbutton() {
        return allbutton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getBuyActivatedButton() {
        return buyActivatedButton;
    }

    public JButton getSellActivatedButton() {
        return sellActivatedButton;
    }

    public JButton getExit() {
        return exit;
    }

    public void setExit(JButton exit) {
        this.exit = exit;
    }

    public JButton getBuyButton() {
        return buyButton;
    }

    public JButton getSellButton() {
        return sellButton;
    }


    public ArrayList<Images> getImages() {
        return images;
    }

    public ArrayList<CardModelView> getCards() {
        return cards;
    }

    public void setCards(ArrayList<CardModelView> cards) {
        this.cards = cards;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JSlider getManaFilter() {
        return manaFilter;
    }

    public boolean isBuyActivated() {
        return buyActivated;
    }

    public void setBuyActivated(boolean buyActivated) {
        this.buyActivated = buyActivated;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public String getName1() {
        return name1;
    }
}
