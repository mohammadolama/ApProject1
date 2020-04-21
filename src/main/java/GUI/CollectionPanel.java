package GUI;

import AllCards.Cards;
import Main.Deck;
import Main.Gamestate;
import Util.Admin;
import Util.CustomScrollBarUI;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
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
    private JButton exitButton =new JButton();
    private JButton selectButton=new JButton();
    private JLabel errorLabel=new JLabel();
    private JTextField searchField;
    private JSlider manaFilter ;
    private JScrollPane scrollPane;
    private ArrayList<Cards> cards;
    private CollectionDrawingPanel collectionDrawingPanel;

    private static ArrayList<BufferedImage> bufferedImages;
    private static ArrayList<JButton> buttons;

    private Deck selectedDeck;

    private Admin admin;

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





    private CollectionPanel(){
        admin=Admin.getInstance();
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

            void warn() {
                if (searchField.getText() == null || searchField.getText().equals("")) {
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

        exitButton.addActionListener(this);
        exitButton.addMouseListener(this);
        exitButton.setIcon(exitIcon);
        exitButton.setBounds(deckX + 150,880,60,60);
        exitButton.setFocusable(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setRolloverEnabled(false);
        exitButton.setBorderPainted(false);


        selectButton.addActionListener(this);
        selectButton.addMouseListener(this);
        selectButton.setIcon(selectIcon);
        selectButton.setBounds(deckX ,880,60,60);
        selectButton.setFocusable(false);
        selectButton.setContentAreaFilled(false);
        selectButton.setRolloverEnabled(false);
        selectButton.setBorderPainted(false);



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
        add(exitButton);
        add(selectButton);
        showDecksButtons();
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
        add(exitButton);
        add(selectButton);
        showDecksButtons();

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
            admin.saveAndUpdate();
           admin.setVisiblePanel("menu");
        }else if (src == exitButton){
            admin.exit();
        }
        else if (src == newDeck) {
            admin.createNewDeck();
        } else if (src == allCards) {
            admin.updateDrawingPanel("all");
        } else if (src == lockedCards) {
            admin.updateDrawingPanel("locked");
        } else if (src == unlockedCards) {
            admin.updateDrawingPanel("unlocked");
        } else if (src == neutralCards) {
            admin.updateDrawingPanel("neutral");
        } else if (src == specialCards) {
            admin.updateDrawingPanel("special");
        }else if (src == changeButton){
            Col_Change.getInstance().getDeckName().setText(selectedDeck.getName());
            Col_Change.getInstance().updateSelectedDeck(selectedDeck.getHero().getName());
            Col_Change.getInstance().setHeroSelected(true);
            Col_Change.getInstance().setSelectedCards(selectedDeck.getDeck());
            Col_Change.getInstance().setSelectedDeck(selectedDeck);
            Col_Change.getInstance().changeButtonAction();
            MyFrame.getInstance().changePanel("col");
        }else if (src == selectButton){
            if (selectedDeck != null){
                admin.setSelectedDeck(selectedDeck);
            }
        }
        else {
            changeButton.setEnabled(true);
            for (JButton button : buttons) {
                CollectionDrawingPanel.getInstance().setSpecialSelected(false);
                if (src.getName().equalsIgnoreCase( button.getName())) {
                    selectedDeck=Gamestate.getPlayer().getAllDecks().get(button.getName());
                    System.out.println(selectedDeck.getName());
                    admin.updateDrawingPanel(button.getName());
                    changeButton.setEnabled(true);
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
            cards = admin.properCards(3);

        } else {
            ArrayList<Cards> ar=admin.properCards(3);
            cards = new ArrayList<>();
            for (Cards cards1 : ar) {
                if (cards1.getManaCost() == value) {
                    cards.add(cards1);
                }
            }
        }
        CollectionDrawingPanel.getInstance().updateContent(cards);
    }

    static int calculateHeight(int rows , int cardHeight){
        int height=rows * cardHeight ;
        if (height<=800){
            return 800;
        }
       return height+50;
    }

    void updateDimension(int rows , int cardHeight){
        Dimension d=collectionDrawingPanel.getPreferredSize();
        d.height=calculateHeight(rows,cardHeight);
        collectionDrawingPanel.setPreferredSize(d);
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }

    public JButton getChangeButton() {
        return changeButton;
    }

    public void setSelectedDeck(Deck selectedDeck) {
        this.selectedDeck = selectedDeck;
    }
}
