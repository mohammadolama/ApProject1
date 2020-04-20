package GUI;

import AllCards.Cards;
import Enums.Carts;
import G_L_Interface.Update;
import Main.Deck;
import Main.Gamestate;
import Main.JsonBuilders;
import Main.Shop;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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

public class CollectionPanel extends JPanel implements ActionListener, MouseListener,ChangeListener {

    private static final CollectionPanel col=new CollectionPanel();

    private JButton backButton = new JButton();
    private JButton allCards = new JButton("All");
    private JButton lockedCards = new JButton("Locked");
    private JButton unlockedCards = new JButton("Unlocked");
    private JButton neutralCards = new JButton("Neutral");
    private JButton specialCards = new JButton("Special");
    private JButton newDeck = new JButton("New Deck");
    private JButton changeButton = new JButton("Change");
    private JButton exit=new JButton();
    private JLabel errorLabel=new JLabel();
    private JButton buyButton;
    private JTextField searchField = new JTextField();
    private JSlider manaFilter = new JSlider();
    private JScrollPane scrollPane;
    private ArrayList<Images> images;
    private ArrayList<Cards> cards;
    private CollectionDrawingPanel collectionDrawingPanel;

    private static ArrayList<BufferedImage> bufferedImages;
    private static ArrayList<BufferedImage> purchasedCards;
    private static ArrayList<BufferedImage> notPurchasedCards;
    private static ArrayList<JButton> buttons;
    private static HashMap<String, Deck> decks;

    private Deck selectedDeck;

    private int searchHeight = 50;
    private int searchX = 180;
    private int searchY = 20;
    private int allCardsX = 560;
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
    private int cardsY = searchY + searchHeight + 20;
    private int cardWidth = 130;
    private int cardHeight = 195;

    private String name;

    private boolean clicked;
    private boolean mate;
    private boolean specialSelected;





    private CollectionPanel(){
        setSize(1600,1000);
        setPreferredSize(new Dimension(1600,1000));

        setLayout(null);

        buttons=new ArrayList<>();


        searchField = new JTextField();
        searchField.setFont(f2.deriveFont(25.0f));
        searchField.setBounds(searchX, searchY, 350, searchHeight);
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

            public void warn() {
                if (searchField.getText() == null || searchField.getText() == "") {
                    return;
                }
                ArrayList<Cards> ar = Cards.allCards();    ////////////////////////////////////////////////////////////////////
                cards = new ArrayList<>();
                for (Cards cards1 : ar) {
                    if (cards1.getName().toLowerCase().contains(searchField.getText())) {
                        cards.add(cards1);
                    }
                }
                CollectionDrawingPanel.getInstance().updateContent(cards);
                repaint();

            }
        });

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
        manaFilter.addChangeListener(this);
        manaFilter.setFont(f2.deriveFont(30.0f));
        manaFilter.setBounds(manaX, manaY, manaWidth, searchHeight);

        changeButton.addMouseListener(this);
        changeButton.setFont(f2);
        changeButton.addActionListener(this);
        changeButton.setBounds(manaX + manaWidth+ 30, manaY, deckWidth, deckHeight);
        changeButton.setFocusable(false);
        changeButton.setEnabled(false);

        errorLabel.setFont(f2);
        errorLabel.setForeground(Color.red);
        errorLabel.setFocusable(false);
        errorLabel.setBounds(changeButton.getX() +deckWidth+60 , manaY , 400,deckHeight);



        backButton.addMouseListener(this);
        backButton.addActionListener(this);
        backButton.setBounds(deckX+75, 880, 60, 60);
        backButton.setFocusable(false);
        backButton.setIcon(backIcon);
        backButton.setContentAreaFilled(false);
        backButton.setRolloverEnabled(false);
        backButton.setBorderPainted(false);

        exit.addActionListener(this);
        exit.addMouseListener(this);
        exit.setIcon(exitIcon);
        exit.setBounds(deckX + 150,880,60,60);
        exit.setFocusable(false);
        exit.setContentAreaFilled(false);
        exit.setRolloverEnabled(false);
        exit.setBorderPainted(false);


        setLayout(null);
        collectionDrawingPanel =CollectionDrawingPanel.getInstance();
        collectionDrawingPanel.setSize(1000, 1600);
        collectionDrawingPanel.setPreferredSize(new Dimension(1000, 1600));
        collectionDrawingPanel.setBounds(0, 0, 1000, 1600);
        collectionDrawingPanel.setFocusable(true);
        collectionDrawingPanel.requestFocus();


        scrollPane = new JScrollPane(collectionDrawingPanel);
        scrollPane.setSize(1000, 600);
        scrollPane.setPreferredSize(new Dimension(1000, 600));
        scrollPane.setBounds(0, searchHeight + 35, 1350, manaY - 100);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scrollPane.setFocusable(false);





        add(scrollPane);
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
        add(errorLabel);
        add(exit);
        showDecksButtons();


//

        Col_Change col_change=Col_Change.getInstance();
        MyFrame.panel.add(col_change,"col");


        setFocusable(true);
//        revalidate();
        repaint();
//
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
            buttons.clear();
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

    public void refresh(){
        removeAll();
        add(scrollPane);
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
        add(errorLabel);
        add(exit);
        showDecksButtons();

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


        public static CollectionPanel getInstance(){
        return col;
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2d=(Graphics2D) g;
        g.setColor(Color.YELLOW);
        g.drawImage(status, 0, 0, 1600, 1000, null);
        g.drawLine(1350, 0, 1350, 1000);
        g.drawLine(0, searchY + searchHeight + 10, 1350, searchY + searchHeight + 10);
        g.drawLine(0, manaY - 10, 1350, manaY - 10);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(f2.deriveFont(28.0f));
        g2d.drawString("Search :", 75, 55);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src=(JButton) e.getSource();
        if (src == backButton) {
            Update.saveAndUpdate();
            MyFrame.getInstance().changePanel("menu");
        }else if (src == exit){
            JsonBuilders.PlayerJsonBuilder(Gamestate.getPlayer().getUsername() , Gamestate.getPlayer());
            System.exit(0);
        }
        else if (src == newDeck) {
            if (Gamestate.getPlayer().getAllDecks().size()<10)
            MyFrame.getInstance().changePanel("col");
            else {
                errorLabel.setText("Can not create more than 10 decks");
                revalidate();
                return;
            }
        } else if (src == allCards) {
            CollectionDrawingPanel.getInstance().setSpecialSelected(false);
            changeButton.setEnabled(false);
            cards = new ArrayList<>();
            cards = Cards.allCards();
            CollectionDrawingPanel.getInstance().updateContent(cards);
            repaint();
        } else if (src == lockedCards) {
            CollectionDrawingPanel.getInstance().setSpecialSelected(false);
            changeButton.setEnabled(false);
            cards = new ArrayList<>();
            cards = Cards.lockedCards();
            CollectionDrawingPanel.getInstance().updateContent(cards);
        } else if (src == unlockedCards) {
            CollectionDrawingPanel.getInstance().setSpecialSelected(false);
            changeButton.setEnabled(false);
            cards = new ArrayList<>();
            cards = Cards.purchasedCards();
            CollectionDrawingPanel.getInstance().updateContent(cards);
        } else if (src == neutralCards) {
            CollectionDrawingPanel.getInstance().setSpecialSelected(false);
            cards=new ArrayList<>();
            cards=Cards.neutralCardsFilter();
            CollectionDrawingPanel.getInstance().updateContent(cards);
        } else if (src == specialCards) {
            CollectionDrawingPanel.getInstance().setSpecialSelected(true);
            cards = new ArrayList<>();
            cards = Cards.specialCardsFilter();
            CollectionDrawingPanel.getInstance().updateContent(cards);
        }else if (src == changeButton){
            Col_Change.getInstance().getDeckName().setText(selectedDeck.getName());
            Col_Change.getInstance().updateSelectedDeck(selectedDeck.getHero().getName());
            Col_Change.getInstance().setHeroSelected(true);
            Col_Change.getInstance().setSelectedCards(selectedDeck.getDeck());
            Col_Change.getInstance().setSelectedDeck(selectedDeck);
            Col_Change.getInstance().changeButtonAction();
            MyFrame.getInstance().changePanel("col");
        }else {
            changeButton.setEnabled(true);
            for (JButton button : buttons) {
                CollectionDrawingPanel.getInstance().setSpecialSelected(false);
                if (src.getName().equalsIgnoreCase( button.getName())) {
                    updateSelectedDeck(button.getName());
                    CollectionDrawingPanel.getInstance().updateContent(cards);

                }
            }
        }


    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
        int value = manaFilter.getValue();
        if (value == 11) {
            cards = new ArrayList<>();
            cards = Cards.allCards();
            CollectionDrawingPanel.getInstance().updateContent(cards);
        } else {
            ArrayList<Cards> ar;
            ar = Cards.allCards();
            cards = new ArrayList<>();
            for (Cards cards1 : ar) {
                if (cards1.getManaCost() == value) {
                    cards.add(cards1);
                }
            }
            CollectionDrawingPanel.getInstance().updateContent(cards);
        }
        repaint();
    }

    static int calculateHeight(int rows , int cardHeight){
        int height=0;
        height=rows * cardHeight ;
        if (height<=800){
            return 800;
        }
       return height;
    }

    void updateDimension(int rows , int cardHeight){
        Dimension d=collectionDrawingPanel.getPreferredSize();
        d.height=calculateHeight(rows,cardHeight);;
        collectionDrawingPanel.setPreferredSize(d);
        repaint();
        revalidate();
    }
}
