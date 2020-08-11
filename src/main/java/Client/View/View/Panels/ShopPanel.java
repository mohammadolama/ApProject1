package Client.View.View.Panels;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.*;
import Client.Controller.Responses;
import Client.Model.CardModelView;
import Client.Model.Enums.Type;
import Client.Model.Images;
import Client.View.Configs.ConfigsLoader;
import Client.View.Configs.ShopConfig;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


import static Client.View.View.Panels.Constants.*;

public class ShopPanel extends JPanel implements ChangeListener, MouseListener, ActionListener {

    private static final ShopPanel shoppanel = new ShopPanel();


    private static ArrayList<BufferedImage> bufferedImages;


    private JButton minionButton = new JButton("Minion");
    private JButton spellButton = new JButton("Spell");
    private JButton weaponButton = new JButton("Weapon");
    private JButton allbutton = new JButton("All");
    private JButton backButton = new JButton();
    private JButton buyActivatedButton = new JButton("Buy");
    private JButton sellActivatedButton = new JButton("Sell");
    private JButton exit = new JButton();
    private JButton buyButton;
    private JButton sellButton;
    private ArrayList<Images> images;
    private ArrayList<CardModelView> cards;
    private JLabel walletLabel;
    private JTextField searchField;
    private JSlider manaFilter;


    private boolean buyActivated = true;
    private boolean clicked;
    private boolean mate;
    private long wallet;


    private String name;

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


        allbutton.addActionListener(this);
        allbutton.addMouseListener(this);
        allbutton.setBounds(config.getStartX1(), config.getStartY1(), config.getMinionWidth(), config.getMininHeight());
        allbutton.setFont(f2);
        allbutton.setFocusable(false);

        minionButton.addActionListener(this);
        minionButton.addMouseListener(this);
        minionButton.setBounds(config.getStartX1(), config.getStartY1() + config.getSpacing1(), config.getMinionWidth(), config.getMininHeight());
        minionButton.setFont(f2);
        minionButton.setFocusable(false);


        spellButton.addActionListener(this);
        spellButton.addMouseListener(this);
        spellButton.setBounds(config.getStartX1(), config.getStartY1() + 2 * config.getSpacing1(), config.getMinionWidth(), config.getMininHeight());
        spellButton.setFocusable(false);
        spellButton.setFont(f2);


        weaponButton.addActionListener(this);
        weaponButton.addMouseListener(this);
        weaponButton.setBounds(config.getStartX1(), config.getStartY1() + 3 * config.getSpacing1(), config.getMinionWidth(), config.getMininHeight());
        weaponButton.setFocusable(false);
        weaponButton.setFont(f2);


        backButton.setIcon(gameIcon.get("back"));
        backButton.addActionListener(this);
        backButton.setBounds(config.getStartX1() + 75, config.getStartY1() + 6 * config.getSpacing1(), config.getBacksize(), config.getBacksize());
        backButton.setFocusable(false);
        backButton.setContentAreaFilled(false);
        backButton.setRolloverEnabled(false);
        backButton.setBorderPainted(false);

        exit.addActionListener(this);
        exit.setIcon(gameIcon.get("exit"));
        exit.setBounds(config.getStartX1() + 150, config.getStartY1() + 6 * config.getSpacing1(), config.getBacksize(), config.getBacksize());
        exit.setFocusable(false);
        exit.setContentAreaFilled(false);
        exit.setRolloverEnabled(false);
        exit.setBorderPainted(false);


        buyActivatedButton.addActionListener(this);
        buyActivatedButton.addMouseListener(this);
        buyActivatedButton.setBounds(700, 880, config.getMinionWidth(), config.getMininHeight());
        buyActivatedButton.setFocusable(false);
        buyActivatedButton.setBackground(Color.YELLOW);
        buyActivatedButton.setFont(f2);


        sellActivatedButton.addActionListener(this);
        sellActivatedButton.addMouseListener(this);
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
        manaFilter.addChangeListener(this);
        manaFilter.setFont(f2.deriveFont(30.0f));
        manaFilter.setBounds(30, 880, 600, 50);


        searchField = new JTextField();
        searchField.setFont(f2.deriveFont(25.0f));
        searchField.setBounds(150, 20, 350, 50);
        searchField.setFocusable(true);
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                warn();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                warn();
            }

            void warn() {
                searchField.requestFocus();
                if (searchField.getText() == null || searchField.getText().equals("")) {
                    return;
                }
                RequestHandler.getInstance().sendRequest(new ProperCardsRequest(buyActivated ? 1 : 2));
                ArrayList<CardModelView> ar = Responses.getInstance().getModelviewList();
                cards = new ArrayList<>();
                for (CardModelView cards1 : ar) {
                    if (cards1.getName().toLowerCase().contains(searchField.getText())) {
                        cards.add(cards1);
                    }
                }
                pictures(cards);
                repaint();
            }
        });
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
        buyButton.addActionListener(this);

        sellButton = new JButton("Sell");
        sellButton.setFocusable(false);
        sellButton.setEnabled(true);
        sellButton.setFont(f2.deriveFont(30.0f));
        sellButton.setBackground(Color.orange);
        sellButton.setBounds(790, 550, 200, 50);
        sellButton.addActionListener(this);

        addMouseListener(this);
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
            RequestHandler.getInstance().sendRequest(new PriceRequest(name.toLowerCase()));
            long price = Responses.getInstance().getPrice();
            String className = Responses.getInstance().getClassName();
            g2d.drawString("Price : " + price, 720, config.getPriceY());
            g2d.drawString("Class : ", 720, config.getPriceY() - 40);
            g2d.setColor(Color.BLUE);
            g2d.drawString(className, 850, config.getPriceY() - 40);
        }
        g2d.drawImage(cardPics.get(name), 300, 220, null);
        drawCardInfo(g2d, name, 300, 220);

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

    private void pictures(ArrayList<CardModelView> ar) {
        bufferedImages = new ArrayList<>();

        for (CardModelView cards1 : ar) {
            BufferedImage bf = cardPics.get(cards1.getName().toLowerCase());
            bufferedImages.add(bf);
        }
    }


    private void drawBigger(String st) {
        clicked = true;
        name = st;
        repaint();
    }


    private void BuySellChanger() {
        images.clear();
        RequestHandler.getInstance().sendRequest(new ProperCardsRequest(buyActivated ? 1 : 2));
        cards = Responses.getInstance().getModelviewList();
        pictures(cards);
        repaint();
    }

    private void revalidateCards(Boolean buyActivated) {
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


    @Override
    public void stateChanged(ChangeEvent e) {
        int value = manaFilter.getValue();
        if (value == 11) {
            RequestHandler.getInstance().sendRequest(new ProperCardsRequest(buyActivated ? 1 : 2));
            cards = Responses.getInstance().getModelviewList();
        } else {
            images.clear();
            RequestHandler.getInstance().sendRequest(new ProperCardsRequest(buyActivated ? 1 : 2));
            ArrayList<CardModelView> ar = Responses.getInstance().getModelviewList();
            cards = new ArrayList<>();
            for (CardModelView cards1 : ar) {
                if (cards1.getManaCost() == value) {
                    cards.add(cards1);
                }
            }
        }
        pictures(cards);
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton src = (JButton) e.getSource();
        if (src == backButton) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Exit Button"));
            RequestHandler.getInstance().sendRequest(new LogRequest("Navigate : Main Menu"));
            RequestHandler.getInstance().sendRequest(new SaveRequest());
            RequestHandler.getInstance().sendRequest(new VisiblePanelRequest("menu"));
        } else if (src == exit) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Exit Button"));
            RequestHandler.getInstance().sendRequest(new ExitRequest());
        } else if (src == buyActivatedButton) {
            buyActivatedButton.setBackground(Color.yellow);
            sellActivatedButton.setBackground(Color.WHITE);
            buyActivated = true;
            BuySellChanger();
        } else if (src == sellActivatedButton) {
            buyActivatedButton.setBackground(Color.white);
            sellActivatedButton.setBackground(Color.yellow);
            buyActivated = false;
            BuySellChanger();
        } else if (src == buyButton) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Buy Button"));
            RequestHandler.getInstance().sendRequest(new BuyCardRequest(name));
            revalidateCards(true);
        } else if (src == sellButton) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Sell Button"));
            RequestHandler.getInstance().sendRequest(new SellCardRequest(name));
            revalidateCards(false);
        } else {
            images.clear();
            RequestHandler.getInstance().sendRequest(new ProperCardsRequest(buyActivated ? 1 : 2));
            ArrayList<CardModelView> ar = Responses.getInstance().getModelviewList();
            cards = new ArrayList<>();
            for (CardModelView cards1 : ar) {
                if (src == allbutton) {
                    cards.add(cards1);
                } else if ((src == minionButton && cards1.getType().equals(Type.Minion)) ||
                        (src == spellButton && cards1.getType().equals(Type.Spell)) ||
                        (src == weaponButton && cards1.getType().equals(Type.Weapon))) {
                    cards.add(cards1);
                }
            }
            pictures(cards);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        String st = null;
        for (Images images : images) {
            if ((x >= images.getX() && x <= images.getX() + images.getWidth()) && (y >= images.getY() && y <= images.getY() + images.getHeigth())) {
                st = images.getName();
                break;
            }
        }
        if (st == null || st.equals("")) {
            clicked = false;
            repaint();
            return;
        }
        drawBigger(st);
        revalidate();
        repaint();
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
