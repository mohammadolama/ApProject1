package GUI.DrawingPanels;

import AllCards.Cards;
import Enums.Carts;
import GUI.Images;
import Main.Deck;
import Main.Gamestate;
import Main.Shop;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import static GUI.Constants.*;

public class CollectionDrawingPanel2 extends JPanel implements ActionListener, MouseListener, ChangeListener {


    private JButton backButton = new JButton("Back");
    private JButton allCards = new JButton("All");
    private JButton lockedCards = new JButton("Locked");
    private JButton unlockedCards = new JButton("Unlocked");
    private JButton neutralCards = new JButton("Neutral");
    private JButton specialCards = new JButton("Special");
    private JButton newDeck = new JButton("New Deck");
    private JButton changeButton = new JButton("Change");
    private JButton buyButton;
    private JTextField searchField = new JTextField();
    private JSlider manaFilter = new JSlider();

    private ArrayList<Images> images;
    private ArrayList<Cards> cards;

    private static ArrayList<BufferedImage> bufferedImages;
    private static ArrayList<BufferedImage> purchasedCards;
    private static ArrayList<BufferedImage> notPurchasedCards;
    private static ArrayList<JButton> buttons = new ArrayList<>();
    private static HashMap<String, Deck> decks;

    private Deck selectedDeck;

    private int searchHeight = 50;
    private int searchX = 150;
    private int searchY = 20;
    private int allCardsX = 520;
    private int allCardWidth = 140;
    private int manaX = 30;
    private int manaY = 885;
    private int manaWidth = 600;

    private int deckX = 1360;
    private int deckY = 20;
    private int deckWidth = 200;
    private int deckHeight = 60;
    private int deckSpacing = 75;

    private int cardsX = 15;
    private int cardsY = 15;
    private int cardWidth = 130;
    private int cardHeight = 195;

    private String name;

    private boolean clicked;
    private boolean mate;
    private boolean specialSelected;

    private boolean flag = false;

    private static final CollectionDrawingPanel2 collectionDrawingPanel = new CollectionDrawingPanel2();

    public CollectionDrawingPanel2() {
        images = new ArrayList<>();
        cards = new ArrayList<>();
        bufferedImages = new ArrayList<>();
//        if(!flag) {
//            cards = Cards.allCards();
//            pictures(cards);
//            flag=true;
//        }
        showDecksButtons();
//        buttons.get(0).doClick();
        setLayout(null);
//        allCards.doClick();
        addMouseListener(this);
        update();

        searchField = new JTextField();
        searchField.setFont(f2.deriveFont(25.0f));
        searchField.setBounds(searchX, searchY, 350, searchHeight);
        searchField.setFocusable(true);
//        searchField.getDocument().addDocumentListener(new DocumentListener() {
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                warn();
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                warn();
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                warn();
//            }
//
//            public void warn() {
//                searchField.requestFocus();
//                if (searchField.getText() == null || searchField.getText() == "") {
//                    return;
//                }
//                ArrayList<Cards> ar = Cards.allCards();    ////////////////////////////////////////////////////////////////////
//                cards = new ArrayList<>();
//                for (Cards cards1 : ar) {
//                    if (cards1.getName().toLowerCase().contains(searchField.getText())) {
//                        cards.add(cards1);
//                    }
//                }
//                pictures(cards);
//                repaint();
//                searchField.requestFocus();
//
//            }
//        });

        allCards.addMouseListener(this);
        allCards.setFont(f2);
        allCards.addActionListener(this);
        allCards.setBounds(allCardsX, searchY, allCardWidth, searchHeight);
        allCards.setFocusable(false);

        lockedCards.addMouseListener(this);
        lockedCards.setFont(f2);
        lockedCards.addActionListener(this);
        lockedCards.setBounds(allCardsX + allCardWidth + 20, searchY, allCardWidth, searchHeight);
        lockedCards.setFocusable(false);

        unlockedCards.addMouseListener(this);
        unlockedCards.setFont(f2);
        unlockedCards.addActionListener(this);
        unlockedCards.setBounds(allCardsX + (2 * allCardWidth) + 40, searchY, allCardWidth, searchHeight);
        unlockedCards.setFocusable(false);

        neutralCards.addMouseListener(this);
        neutralCards.setFont(f2);
        neutralCards.addActionListener(this);
        neutralCards.setBounds(allCardsX + (3 * allCardWidth) + 60, searchY, allCardWidth, searchHeight);
        neutralCards.setFocusable(false);

        specialCards.addMouseListener(this);
        specialCards.setFont(f2);
        specialCards.addActionListener(this);
        specialCards.setBounds(allCardsX + (4 * allCardWidth) + 80, searchY, allCardWidth, searchHeight);
        specialCards.setFocusable(false);

        newDeck.addMouseListener(this);
        newDeck.setFont(f2);
        newDeck.addActionListener(this);
        newDeck.setBounds(deckX, deckY, deckWidth, deckHeight);
        newDeck.setBackground(Color.yellow);
        newDeck.setFocusable(false);


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
//        manaFilter.addChangeListener(this);
        manaFilter.setFont(f2.deriveFont(30.0f));
        manaFilter.setBounds(manaX, manaY, manaWidth, searchHeight);

        changeButton.addMouseListener(this);
        changeButton.setFont(f2);
        changeButton.addActionListener(this);
        changeButton.setBounds(manaX + manaWidth + 250, manaY, deckWidth, deckHeight);
        changeButton.setFocusable(false);
        changeButton.setEnabled(false);


        backButton.addMouseListener(this);
        backButton.setFont(f2);
        backButton.addActionListener(this);
        backButton.setBounds(deckX, 880, deckWidth, deckHeight);
        backButton.setFocusable(false);

        buyButton = new JButton("Buy");
        buyButton.setFocusable(false);
        buyButton.setEnabled(true);
        buyButton.setFont(f2.deriveFont(30.0f));
        buyButton.setBackground(Color.orange);
        buyButton.setBounds(720, 550, 200, 50);
        buyButton.addActionListener(this);


        requestFocus();
        setFocusable(true);
        setBackground(Color.LIGHT_GRAY);
        revalidate();
        repaint();

    }

    public static CollectionDrawingPanel2 getInstance() {
        return collectionDrawingPanel;
    }

    public Hashtable<Integer, JComponent> getTable() {
        Hashtable<Integer, JComponent> table =
                new Hashtable<Integer, JComponent>();
        table.put(new Integer(1), new JLabel("1"));
        table.put(new Integer(2), new JLabel("2"));
        table.put(new Integer(3), new JLabel("3"));
        table.put(new Integer(4), new JLabel("4"));
        table.put(new Integer(5), new JLabel("5"));
        table.put(new Integer(6), new JLabel("6"));
        table.put(new Integer(7), new JLabel("7"));
        table.put(new Integer(8), new JLabel("8"));
        table.put(new Integer(9), new JLabel("9"));
        table.put(new Integer(10), new JLabel("10"));
        table.put(new Integer(11), new JLabel("All"));
        return table;
    }

    private void showDecksButtons() {
        if (Gamestate.getPlayer() != null) {
            int i = 1;
            for (Map.Entry<String, Deck> entry : Gamestate.getPlayer().getAllDecks().entrySet()) {
                String s = entry.getKey();
                JButton button = new JButton(s);
                button.setName(s);
                button.setBounds(deckX, deckY + (i * deckSpacing) + 5, deckWidth, deckHeight);
                button.setFont(f2.deriveFont(16.0f));
                button.setFocusable(false);
                button.addActionListener(this);
                this.add(button);
                buttons.add(button);
                i++;
            }

        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
//        if (!clicked) {
        System.out.println(this.cards.size());
        removeAll();
        for (JButton button : buttons) {
            add(button);
        }
        add(searchField);
        add(allCards);
        add(backButton);
        add(allCards);
        add(lockedCards);
        add(unlockedCards);
        add(manaFilter);
        add(newDeck);
        add(changeButton);
        add(neutralCards);
        add(specialCards);
        update();
        g.setColor(Color.YELLOW);
        g.drawImage(status, 0, 0, 1600, 1000, null);
        g.drawLine(1350, 0, 1350, 1000);
        g.drawLine(0, searchY + searchHeight + 10, 1350, searchY + searchHeight + 10);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(f2.deriveFont(40.0f));
        g2d.setColor(Color.YELLOW);
        g2d.setFont(f2);
        g2d.setColor(Color.yellow);
        g2d.drawString("Search :", 30, 55);
        g2d.drawLine(0, manaY - 10, 1350, manaY - 10);
        int i = 0;
        while (i < bufferedImages.size()) {
            if (specialSelected) {
                cardsY = searchY + searchHeight + 300;
                if (i > 1 && i % 2 == 0) {
                    g2d.setColor(Color.yellow);
                    g2d.drawLine(cardsX, searchY + searchHeight + 10, cardsX, manaY - 10);
                }
            }

            g2d.drawImage(bufferedImages.get(i), cardsX, cardsY, cardWidth, cardHeight, null);
            images.add(new Images(cards.get(i).getName().toLowerCase(), cardsX, cardsY, cardWidth, cardHeight));

            g2d.setColor(new Color(50, 50, 50, 180));
            if (contains(bufferedImages.get(i))) {
                g2d.fillRect(cardsX + 10, cardsY + 10, cardWidth - 15, cardHeight - 15);
            } else {
                g2d.drawImage(tick, cardsX + 50, cardsY, null);
            }
            cardsX = cardsX + cardWidth;
            if (cardsX >= 1200) {
                cardsX = 15;
                cardsY = cardsY + (cardHeight);
            }
            i++;
        }
        cardsX = 15;
        cardsY = searchY + searchHeight + 20;
        if (specialSelected) {
            g2d.drawImage(heroPics.get("rogue"), 30, cardsY, cardWidth * 3 / 2, cardHeight * 3 / 2, null);
            g2d.drawImage(heroPics.get("mage"), 330, cardsY, cardWidth * 3 / 2, cardHeight * 3 / 2, null);
            g2d.drawImage(heroPics.get("warlock"), 580, cardsY, cardWidth * 3 / 2, cardHeight * 3 / 2, null);
            g2d.drawImage(heroPics.get("hunter"), 850, cardsY, cardWidth * 3 / 2, cardHeight * 3 / 2, null);
            g2d.drawImage(heroPics.get("priest"), 1100, cardsY, cardWidth * 3 / 2, cardHeight * 3 / 2, null);
            specialSelected=false;
        }
        mate = false;
        revalidate();
//        }
        if (clicked) {
            if (!mate) {
                removeAll();
                images.clear();
                g2d.setColor(new Color(222, 222, 222, 200));
                g2d.fillRect(0, 0, 1600, 1000);
                g2d.setColor(Color.white);
                mate = true;
                if (contains(cardPics.get(name))) {
                    buyButton.setEnabled(true);
                    buyButton.setBackground(Color.ORANGE);
                    g2d.setFont(f2.deriveFont(50.0f));
                    g2d.setColor(Color.red);
                    g2d.drawString("Price : " + Shop.Price(name.toLowerCase()), 720, 480);
                    if (Shop.Price((name.toLowerCase())) > Gamestate.getPlayer().getMoney()) {
                        buyButton.setEnabled(false);
                        buyButton.setBackground(Color.LIGHT_GRAY);
                    }
                    add(buyButton);
                }
            }
            g2d.drawImage(cardPics.get(name), 300, 220, null);
        }
//        searchField.requestFocus();
//        }
    }


    public void pictures(ArrayList<Cards> ar) {
        bufferedImages = new ArrayList();
        for (Cards cards1 : ar) {
            BufferedImage bf = cardPics.get(cards1.getName().toLowerCase());
            bufferedImages.add(bf);
        }

    }

    private void updateSelectedDeck(String name) {
        if (Gamestate.getPlayer() != null) {
            selectedDeck = Deck.changeSelectedDeck(Gamestate.getPlayer().getAllDecks().get(name));
            cards = Deck.UpdateDeck(selectedDeck.getDeck());
            bufferedImages = new ArrayList<>();
            ArrayList<Carts> ar3 = selectedDeck.getDeck();
            for (Carts carts : ar3) {
                BufferedImage bf = cardPics.get(carts.toString());
                bufferedImages.add(bf);
            }
        }
    }

    boolean contains(BufferedImage bufferedImage) {
        for (BufferedImage notPurchasedCard : notPurchasedCards) {
            if (notPurchasedCard.equals(bufferedImage)) {
                return true;
            }
        }
        return false;
    }

    void update() {
        purchasedCards = new ArrayList<>();
        notPurchasedCards = new ArrayList<>();

        for (Cards purchasedCard : Cards.purchasedCards()) {
            BufferedImage bf = cardPics.get(purchasedCard.getName().toLowerCase());
            purchasedCards.add(bf);
        }
        for (Cards lockedCard : Cards.lockedCards()) {
            BufferedImage bf = cardPics.get(lockedCard.getName().toLowerCase());
            notPurchasedCards.add(bf);
        }
    }

    private void drawBigger(String st) {
        clicked = true;
        name = st;
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        specialSelected = false;
//        JButton src = (JButton) e.getSource();
//        if (src == backButton) {
//            MyFrame.getInstance().changePanel("menu");
//        } else if (src == newDeck) {
//            Col_Change col_change=Col_Change.getInstance();
//            MyFrame.panel.add(col_change,"col");
//            MyFrame.getInstance().changePanel("col");
//        } else if (src == allCards) {
//            changeButton.setEnabled(false);
//            cards = new ArrayList<>();
//            cards = Cards.allCards();
//            pictures(cards);
//            images.clear();
//            repaint();
//        } else if (src == lockedCards) {
//            changeButton.setEnabled(false);
//            cards = new ArrayList<>();
//            cards = Cards.lockedCards();
//            pictures(cards);
//            images.clear();
//            repaint();
//        } else if (src == unlockedCards) {
//            changeButton.setEnabled(false);
//            cards = new ArrayList<>();
//            cards = Cards.purchasedCards();
//            pictures(cards);
//            images.clear();
//            repaint();
//        } else if (src == neutralCards) {
//            changeButton.setEnabled(false);
//            cards = new ArrayList<>();
//            cards = Cards.neutralCardsFilter();
//            pictures(cards);
//            images.clear();
//            repaint();
//        } else if (src == specialCards) {
//            specialSelected = true;
//            changeButton.setEnabled(false);
//            cards = new ArrayList<>();
//            cards = Cards.specialCardsFilter();
//            pictures(cards);
//            images.clear();
//            repaint();
//        } else if (src == buyButton) {
//            Shop.Buy(name.toLowerCase());
//            cards = new ArrayList<>();
//            cards = Cards.allCards();
//            pictures(cards);
//            images.clear();
//            clicked = false;
//            repaint();
//        } else {
//            changeButton.setEnabled(true);
//            for (JButton button : buttons) {
//                if (src == button) {
//                    updateSelectedDeck(button.getName());
//                    System.out.println(selectedDeck.getName());
//                    revalidate();
//                    repaint();
//
//                }
//            }
//        }

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

        if (st == null || st == "") {
            clicked = false;
            repaint();
            return;
        }
        System.out.println(st);
        drawBigger(st);
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

    @Override
    public void stateChanged(ChangeEvent e) {
//        int value = manaFilter.getValue();
//        if (value == 11) {
//            cards = new ArrayList<>();
//            cards = Cards.allCards();
//            pictures(cards);
//
//        } else {
//            images.clear();
//            ArrayList<Cards> ar;
//            ar = Cards.allCards();
//            cards = new ArrayList<>();
//            for (Cards cards1 : ar) {
//                if (cards1.getManaCost() == value) {
//                    cards.add(cards1);
//                }
//            }
//            pictures(cards);
//        }
//        repaint();
    }


    void updateContent(ArrayList<Cards> cards) {
        images.clear();

        this.cards = new ArrayList<>();
        this.cards = cards;

        pictures(this.cards);

        revalidate();

        this.repaint();

    }


    public JButton getBackButton() {
        return backButton;
    }

    public JButton getAllCards() {
        return allCards;
    }

    public JButton getLockedCards() {
        return lockedCards;
    }

    public JButton getUnlockedCards() {
        return unlockedCards;
    }

    public JButton getNeutralCards() {
        return neutralCards;
    }

    public JButton getSpecialCards() {
        return specialCards;
    }

    public JButton getNewDeck() {
        return newDeck;
    }

    public JButton getChangeButton() {
        return changeButton;
    }

    public JButton getBuyButton() {
        return buyButton;
    }
}
