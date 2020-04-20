//package GUI.DrawingPanels;
//
//import AllCards.Cards;
//import Enums.Carts;
//import GUI.Col_Change;
//import GUI.CustomScrollBarUI;
//import GUI.Images;
//import GUI.MyFrame;
//import Main.Deck;
//import Main.Gamestate;
//import Main.Shop;
//
//import javax.swing.*;
//import javax.swing.event.ChangeEvent;
//import javax.swing.event.ChangeListener;
//import javax.swing.event.DocumentEvent;
//import javax.swing.event.DocumentListener;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.image.BufferedImage;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Hashtable;
//import java.util.Map;
//
//import static GUI.Constants.*;
//
//public class TestPanel extends JPanel implements ActionListener, MouseListener, ChangeListener {
//
//
//    private JButton backButton = new JButton("Back");
//    private JButton allCards = new JButton("All");
//    private JButton lockedCards = new JButton("Locked");
//    private JButton unlockedCards = new JButton("Unlocked");
//    private JButton neutralCards = new JButton("Neutral");
//    private JButton specialCards = new JButton("Special");
//    private JButton newDeck = new JButton("New Deck");
//    private JButton changeButton = new JButton("Change");
//    private JButton buyButton;
//    private JTextField searchField = new JTextField();
//    private JSlider manaFilter = new JSlider();
//    private JScrollPane scrollPane;
//    private ArrayList<Images> images;
//    private ArrayList<Cards> cards;
//
//    private static ArrayList<BufferedImage> bufferedImages;
//    private static ArrayList<BufferedImage> purchasedCards;
//    private static ArrayList<BufferedImage> notPurchasedCards;
//    private static ArrayList<JButton> buttons;
//    private static HashMap<String, Deck> decks;
//
//    private Deck selectedDeck;
//
//    private int searchHeight = 50;
//    private int searchX = 150;
//    private int searchY = 20;
//    private int allCardsX = 520;
//    private int allCardWidth = 140;
//    private int manaX = 30;
//    private int manaY = 885;
//    private int manaWidth = 600;
//
//    private int deckX = 1360;
//    private int deckY = 20;
//    private int deckWidth = 200;
//    private int deckHeight = 60;
//    private int deckSpacing = 75;
//
//    private int cardsX = 15;
//    private int cardsY = searchY + searchHeight + 20;
//    private int cardWidth = 130;
//    private int cardHeight = 195;
//
//    private String name;
//
//    private boolean clicked;
//    private boolean mate;
//    private boolean specialSelected;
//
//    public TestPanel() {
//        setLayout(null);
//        images = new ArrayList<>();
//        buttons = new ArrayList<>();
////        cards = Cards.allCards();
////        pictures(cards);
//        showDecksButtons();
////        buttons.get(0).doClick();
//        allCards.doClick();
//        addMouseListener(this);
////        update();
//
//        searchField = new JTextField();
//        searchField.setFont(f2.deriveFont(25.0f));
//        searchField.setBounds(searchX, searchY, 350, searchHeight);
//        searchField.setFocusable(true);
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
//
//            }
//        });
//
//        allCards.addMouseListener(this);
//        allCards.setFont(f2);
//        allCards.addActionListener(this);
//        allCards.setBounds(allCardsX, searchY, allCardWidth, searchHeight);
//        allCards.setFocusable(false);
//
//        lockedCards.addMouseListener(this);
//        lockedCards.setFont(f2);
//        lockedCards.addActionListener(this);
//        lockedCards.setBounds(allCardsX + allCardWidth + 20, searchY, allCardWidth, searchHeight);
//        lockedCards.setFocusable(false);
//
//        unlockedCards.addMouseListener(this);
//        unlockedCards.setFont(f2);
//        unlockedCards.addActionListener(this);
//        unlockedCards.setBounds(allCardsX + (2 * allCardWidth) + 40, searchY, allCardWidth, searchHeight);
//        unlockedCards.setFocusable(false);
//
//        neutralCards.addMouseListener(this);
//        neutralCards.setFont(f2);
//        neutralCards.addActionListener(this);
//        neutralCards.setBounds(allCardsX + (3 * allCardWidth) + 60, searchY, allCardWidth, searchHeight);
//        neutralCards.setFocusable(false);
//
//        specialCards.addMouseListener(this);
//        specialCards.setFont(f2);
//        specialCards.addActionListener(this);
//        specialCards.setBounds(allCardsX + (4 * allCardWidth) + 80, searchY, allCardWidth, searchHeight);
//        specialCards.setFocusable(false);
//
//        newDeck.addMouseListener(this);
//        newDeck.setFont(f2);
//        newDeck.addActionListener(this);
//        newDeck.setBounds(deckX, deckY, deckWidth, deckHeight);
//        newDeck.setBackground(Color.yellow);
//        newDeck.setFocusable(false);
//
//
//        manaFilter = new JSlider(1, 11, 11);
//        manaFilter.setMinimum(1);
//        manaFilter.setMaximum(11);
//        manaFilter.setBackground(new Color(220, 222, 136));
//        manaFilter.setFocusable(false);
//        manaFilter.setPaintTicks(true);
//        manaFilter.setMajorTickSpacing(10);
//        manaFilter.setMinorTickSpacing(5);
//        manaFilter.setLabelTable(getTable());
//        manaFilter.setPaintLabels(true);
//        manaFilter.addChangeListener(this);
//        manaFilter.setFont(f2.deriveFont(30.0f));
//        manaFilter.setBounds(manaX, manaY, manaWidth, searchHeight);
//
//        changeButton.addMouseListener(this);
//        changeButton.setFont(f2);
//        changeButton.addActionListener(this);
//        changeButton.setBounds(manaX + manaWidth + 250, manaY, deckWidth, deckHeight);
//        changeButton.setFocusable(false);
//        changeButton.setEnabled(false);
//
//
//        backButton.addMouseListener(this);
//        backButton.setFont(f2);
//        backButton.addActionListener(this);
//        backButton.setBounds(deckX, 880, deckWidth, deckHeight);
//        backButton.setFocusable(false);
//
//        buyButton = new JButton("Buy");
//        buyButton.setFocusable(false);
//        buyButton.setEnabled(true);
//        buyButton.setFont(f2.deriveFont(30.0f));
//        buyButton.setBackground(Color.orange);
//        buyButton.setBounds(720, 550, 200, 50);
//        buyButton.addActionListener(this);
//
//
//        setLayout(null);
//        CollectionDrawingPanel collectionDrawingPanel = new CollectionDrawingPanel();
//        collectionDrawingPanel.setSize(1000, 1000);
//        collectionDrawingPanel.setPreferredSize(new Dimension(1000, 1000));
//        collectionDrawingPanel.setBounds(0, 0, 1000, 1000);
//        collectionDrawingPanel.setFocusable(true);
//        collectionDrawingPanel.requestFocus();
//
////        add(collectionDrawingPanel);
//        scrollPane = new JScrollPane(collectionDrawingPanel);
//        scrollPane.setSize(1000, 600);
//        scrollPane.setPreferredSize(new Dimension(1000, 600));
//        scrollPane.setBounds(0, searchHeight + 35, 1350, manaY - 100);
//        scrollPane.setBorder(null);
//        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
//        scrollPane.setFocusable(false);
//
//        add(scrollPane);
//
//
//        setFocusable(true);
////        revalidate();
//        repaint();
////
//
//    }
//
//    private void showDecksButtons() {
//        if (Gamestate.getPlayer() != null) {
//            int i = 1;
//            for (Map.Entry<String, Deck> entry : Gamestate.getPlayer().getAllDecks().entrySet()) {
//                String s = entry.getKey();
//                JButton button = new JButton(s);
//                button.setName(s);
//                button.setBounds(deckX, deckY + (i * deckSpacing) + 5, deckWidth, deckHeight);
//                button.setFont(f2.deriveFont(16.0f));
//                button.setFocusable(false);
//                button.addActionListener(this);
//                this.add(button);
//                buttons.add(button);
//                i++;
//            }
//
//        }
//    }
//
//
//    public Hashtable<Integer, JComponent> getTable() {
//        Hashtable<Integer, JComponent> table =
//                new Hashtable<Integer, JComponent>();
//        table.put(new Integer(1), new JLabel("1"));
//        table.put(new Integer(2), new JLabel("2"));
//        table.put(new Integer(3), new JLabel("3"));
//        table.put(new Integer(4), new JLabel("4"));
//        table.put(new Integer(5), new JLabel("5"));
//        table.put(new Integer(6), new JLabel("6"));
//        table.put(new Integer(7), new JLabel("7"));
//        table.put(new Integer(8), new JLabel("8"));
//        table.put(new Integer(9), new JLabel("9"));
//        table.put(new Integer(10), new JLabel("10"));
//        table.put(new Integer(11), new JLabel("All"));
//        return table;
//    }
//
//    public void pictures(ArrayList<Cards> ar) {
//        bufferedImages = new ArrayList();
//        for (Cards cards1 : ar) {
//            BufferedImage bf = cardPics.get(cards1.getName().toLowerCase());
//            bufferedImages.add(bf);
//        }
//
//    }
//
//    private void updateSelectedDeck(String name) {
//        if (Gamestate.getPlayer() != null) {
//            selectedDeck = Deck.changeSelectedDeck(Gamestate.getPlayer().getAllDecks().get(name));
//            cards = Deck.UpdateDeck(selectedDeck.getDeck());
//            bufferedImages = new ArrayList<>();
//            ArrayList<Carts> ar3 = selectedDeck.getDeck();
//            for (Carts carts : ar3) {
//                BufferedImage bf = cardPics.get(carts.toString());
//                bufferedImages.add(bf);
//            }
//        }
//    }
//
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        showDecksButtons();
//        for (JButton button : buttons) {
//            add(button);
//        }
//        add(searchField);
//        add(allCards);
//        add(backButton);
//        add(allCards);
//        add(lockedCards);
//        add(unlockedCards);
//        add(manaFilter);
//        add(newDeck);
//        add(changeButton);
//        add(neutralCards);
//        add(specialCards);
//
//
//        g.setColor(Color.YELLOW);
//        g.drawImage(status, 0, 0, 1600, 1000, null);
//        g.drawLine(1350, 0, 1350, 1000);
//        g.drawLine(0, searchY + searchHeight + 10, 1350, searchY + searchHeight + 10);
//        g.drawLine(0, manaY - 10, 1350, manaY - 10);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        specialSelected = false;
//        JButton src = (JButton) e.getSource();
//        if (src == backButton) {
//            MyFrame.getInstance().changePanel("menu");
//        } else if (src == newDeck) {
//            Col_Change col_change = Col_Change.getInstance();
//            MyFrame.panel.add(col_change, "col");
//            MyFrame.getInstance().changePanel("col");
//        } else if (src == allCards) {
//            changeButton.setEnabled(false);
//            cards = new ArrayList<>();
//            cards = Cards.allCards();
//            pictures(cards);
//            images.clear();
//            CollectionDrawingPanel.getInstance().updateContent(cards);
//        } else if (src == lockedCards) {
//            changeButton.setEnabled(false);
//            cards = new ArrayList<>();
//            cards = Cards.lockedCards();
//            pictures(cards);
//            images.clear();
//            CollectionDrawingPanel.getInstance().updateContent(cards);
//
//        } else if (src == unlockedCards) {
//            changeButton.setEnabled(false);
//            cards = new ArrayList<>();
//            cards = Cards.purchasedCards();
//            pictures(cards);
//            images.clear();
//            CollectionDrawingPanel.getInstance().updateContent(cards);
//
//        } else if (src == neutralCards) {
//            changeButton.setEnabled(false);
//            cards = new ArrayList<>();
//            cards = Cards.neutralCardsFilter();
//            pictures(cards);
//            images.clear();
//            CollectionDrawingPanel.getInstance().updateContent(cards);
//
//        } else if (src == specialCards) {
//            specialSelected = true;
//            changeButton.setEnabled(false);
//            cards = new ArrayList<>();
//            cards = Cards.specialCardsFilter();
//            pictures(cards);
//            images.clear();
//            CollectionDrawingPanel.getInstance().updateContent(cards);
//
//        } else if (src == buyButton) {
//            Shop.Buy(name.toLowerCase());
//            cards = new ArrayList<>();
//            cards = Cards.allCards();
//            pictures(cards);
//            images.clear();
//            clicked = false;
//
//        } else {
//            changeButton.setEnabled(true);
//            for (JButton button : buttons) {
//                if (src == button) {
//                    updateSelectedDeck(button.getName());
//                    revalidate();
//                    repaint();
//
//                }
//            }
//        }
//    }
//
//    @Override
//    public void mouseClicked(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
//
//    }
//
//    @Override
//    public void stateChanged(ChangeEvent e) {
//
//    }
//}
