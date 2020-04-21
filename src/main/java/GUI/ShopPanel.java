package GUI;

import AllCards.Cards;
import Main.Gamestate;

import Util.Admin;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


import static GUI.Constants.*;

public class ShopPanel extends JPanel implements ChangeListener, MouseListener, ActionListener {

    private static final ShopPanel shoppanel = new ShopPanel();


    private static ArrayList<BufferedImage> bufferedImages;


    private JButton minionButton = new JButton("Minion");
    private JButton spellButton = new JButton("Spell");
    private JButton weaponButton = new JButton("Weapon");
    private JButton allbutton=new JButton("All");
    private JButton backButton = new JButton();
    private JButton buyActivatedButton = new JButton("Buy");
    private JButton sellActivatedButton = new JButton("Sell");
    private JButton exit=new JButton();
    private JButton buyButton;
    private JButton sellButton;
    private ArrayList<Images> images;
    private ArrayList<Cards> cards;
    private JLabel walletLabel;
    private JTextField searchField;
    private JSlider manaFilter;

    private boolean buyActivated = true;
    private boolean clicked;
    private boolean mate;

    private String name;

private Admin admin;

    private int startX1 = 1350;
    private int startY1 = 280;
    private int spacing1 = 100;

    private int startX2 = 20;
    private int startY2 = 80;
    private int width = 130;
    private int height = 195;

    private int priceY =480;

    private ShopPanel() {
        setLayout(null);
        admin=Admin.getInstance();
        images = new ArrayList<>();
        cards = admin.properCards(1);
        pictures(cards);


        allbutton.addActionListener(this);
        allbutton.addMouseListener(this);
        allbutton.setBounds(startX1,startY1,200,50);
        allbutton.setFont(f2);
        allbutton.setFocusable(false);

        minionButton.addActionListener(this);
        minionButton.addMouseListener(this);
        minionButton.setBounds(startX1, startY1+spacing1, 200, 50);
        minionButton.setFont(f2);
        minionButton.setFocusable(false);


        spellButton.addActionListener(this);
        spellButton.addMouseListener(this);
        spellButton.setBounds(startX1, startY1 + 2*spacing1, 200, 50);
        spellButton.setFocusable(false);
        spellButton.setFont(f2);


        weaponButton.addActionListener(this);
        weaponButton.addMouseListener(this);
        weaponButton.setBounds(startX1, startY1 + 3 * spacing1, 200, 50);
        weaponButton.setFocusable(false);
        weaponButton.setFont(f2);


        backButton.setIcon(backIcon);
        backButton.addActionListener(this);
        backButton.setBounds(startX1+75, startY1 + 6*spacing1, 60, 60);
        backButton.setFocusable(false);
        backButton.setContentAreaFilled(false);
        backButton.setRolloverEnabled(false);
        backButton.setBorderPainted(false);

        exit.addActionListener(this);
        exit.setIcon(exitIcon);
        exit.setBounds(startX1+150, startY1 + 6*spacing1, 60, 60);
        exit.setFocusable(false);
        exit.setContentAreaFilled(false);
        exit.setRolloverEnabled(false);
        exit.setBorderPainted(false);


        buyActivatedButton.addActionListener(this);
        buyActivatedButton.addMouseListener(this);
        buyActivatedButton.setBounds(700, 880, 200, 50);
        buyActivatedButton.setFocusable(false);
        buyActivatedButton.setBackground(Color.YELLOW);
        buyActivatedButton.setFont(f2);


        sellActivatedButton.addActionListener(this);
        sellActivatedButton.addMouseListener(this);
        sellActivatedButton.setBounds(950, 880, 200, 50);
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
                ArrayList<Cards> ar=admin.properCards(buyActivated ? 1:2);
                cards = new ArrayList<>();
                for (Cards cards1 : ar) {
                    if (cards1.getName().toLowerCase().contains(searchField.getText())) {
                        cards.add(cards1);
                    }
                }
                pictures(cards);
                repaint();
            }
        });
        add(searchField);

        walletLabel = new JLabel(Gamestate.getPlayer().getMoney() + "   AP");
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




    private String getClass(String string){
        for (Cards card : cards) {
            if (card.getName().equalsIgnoreCase(string)){
                return card.getHeroClass();
            }
        }
        return "Neutral";
    }

    private void refresh(){
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
            g2d.drawImage(status, 0, 0, 1600, 1000, null);
            g2d.drawLine(0, startY1 - 200, startX1 - 10, startY1 - 197);
            g2d.drawLine(startX1 - 10, 0, startX1 - 10, 1000);
            g2d.drawLine(0, 870, startX1 - 10, 870);
            g2d.setColor(Color.yellow);
            g2d.drawString("Search :", 30, 55);
            drawImages(g2d);

        if (clicked){
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
                    if (admin.price((name.toLowerCase())) > Gamestate.getPlayer().getMoney()) {
                        buyButton.setEnabled(false);
                        buyButton.setBackground(Color.LIGHT_GRAY);
                    }
                    add(buyButton);
                }
                else {
                    sellButton.setEnabled(true);
                    sellButton.setBackground(Color.ORANGE);
                    if (!admin.canBeSold(name.toLowerCase())) {
                        g2d.setFont(f2);
                        g2d.setColor(Color.RED);
                        g2d.drawString("Can't be sold,It's in one of your decks.",700, priceY +40);
                        sellButton.setEnabled(false);
                        sellButton.setBackground(Color.LIGHT_GRAY);
                    }
                    add(sellButton);
                }
                g2d.setFont(f2.deriveFont(40.0f));
                g2d.setColor(Color.red);
                g2d.drawString("Price : " + admin.price(name.toLowerCase()), 720, priceY);
                g2d.drawString("Class : " , 720, priceY -40);
                g2d.setColor(Color.BLUE);
                g2d.drawString( getClass(name.toLowerCase()), 850, priceY -40);

            }
            g2d.drawImage(cardPics.get(name), 300, 220, null);

        }
        searchField.requestFocus();
    }


    private void drawImages(Graphics2D g){
        int i = 0;
        while (i < cards.size()) {
            g.drawImage(bufferedImages.get(i), startX2, startY2, width, height, null);
            images.add(new Images(cards.get(i).getName().toLowerCase(), startX2, startY2, width, height));
            startX2 = startX2 + width;
            if (startX2 >= 1300) {
                startX2 = 20;
                startY2 = startY2 + (height);
            }
            i++;
        }
        startX2 = 20;
        startY2 = 80;
        mate = false;
    }

    private void pictures(ArrayList<Cards> ar) {
        bufferedImages = new ArrayList<>();
        for (Cards cards1 : ar) {
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
        cards = Admin.getInstance().properCards(buyActivated ? 1:2);
        pictures(cards);
        repaint();
    }

    private void revalidateCards(Boolean buyActivated){
        cards = new ArrayList<>();
        cards=Admin.getInstance().properCards(buyActivated ? 1:2);
        pictures(cards);
        images.clear();
        walletLabel.setText(Gamestate.getPlayer().getMoney() + "   AP");
        clicked = false;
        repaint();
    }


    @Override
    public void stateChanged(ChangeEvent e) {
        int value = manaFilter.getValue();
        if (value == 11) {
            cards = Admin.getInstance().properCards(buyActivated ? 1:2);
        } else {
            images.clear();
            ArrayList<Cards> ar=Admin.getInstance().properCards(buyActivated ? 1:2);
            cards = new ArrayList<>();
            for (Cards cards1 : ar) {
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
            Admin.getInstance().saveAndUpdate();
            MyFrame.getInstance().changePanel("menu");
        }else if (src == exit){
            Admin.getInstance().exit();
        }
        else if (src == buyActivatedButton) {
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
            admin.buyCard(name);
           revalidateCards(true);
        } else if (src == sellButton) {
            admin.sellCard(name);
            revalidateCards(false);
        } else {
            images.clear();
            ArrayList<Cards> ar=Admin.getInstance().properCards(buyActivated ? 1:2);
            cards = new ArrayList<>();
            for (Cards cards1 : ar) {
                if (src==allbutton){
                    cards.add(cards1);
                }
                else if ((src == minionButton && cards1.getType().equalsIgnoreCase("minion")) ||
                        (src == spellButton && cards1.getType().equalsIgnoreCase("spell")) ||
                        (src == weaponButton && cards1.getType().equalsIgnoreCase("weapon"))) {
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
        System.out.println(x + "  " + y);
        String st = null;
        for (Images images : images) {
            if ((x >= images.getX() && x <= images.getX() + images.getWidth()) && (y >= images.getY() && y <= images.getY() + images.getHeigth())) {
                st = images.getName();
                break;
            }
        }
        if (st == null || st.equals("")) {
            System.out.println("here");
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
