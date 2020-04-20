package GUI;

import AllCards.Cards;
import Enums.Carts;
import Enums.Heroes;
import Enums.NeutralCarts;
import G_L_Interface.Update;
import Heros.Mage;
import Heros.Rogue;
import Heros.Warlock;
import Main.Deck;
import Main.Gamestate;
import Main.JsonBuilders;
import Main.JsonReaders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

import static GUI.Constants.*;


public class Col_Change extends JPanel implements ActionListener, MouseListener {
    private static final Col_Change col_change = new Col_Change();

    private ArrayList<JButton> buttons = new ArrayList<>();
    private ArrayList<Images> images;
    private ArrayList<Cards> cards;
    private ArrayList<BufferedImage> allBufferedImages;
    private ArrayList<BufferedImage> selectedBuferredImages;
    private JTextField deckName = new JTextField();

    private Deck selectedDeck;

    private JButton backButton = new JButton("Back");
    private JButton createButton = new JButton("Create");
    private JButton changeButton = new JButton("Change");
    private JButton removeButton = new JButton("Remove");
    private JButton deckRemoveButton=new JButton("Remove Deck");
    private JButton addButton = new JButton("Add");

    private ArrayList<Carts> selectedCards = new ArrayList<>();

    private int deckX = 1360;
    private int deckY = 130;
    private int deckWidth = 200;
    private int deckHeight = 60;
    private int deckSpacing = 75;

    private int searchHeight = 50;
    private int nameX = 600;
    private int nameY = 20;

    private int cardsX = 15;
    private int cardsY = 90;
    private int cardWidth = 130;
    private int cardHeight = 195;

    private int maxCardInDeck = 15;
    private int minCardInDeck = 8;

    private boolean heroSelected;
    private boolean clicked;
    private boolean mate;
    private boolean easyModeEnabled;

    private JLabel errorLabel = new JLabel();

    private String name;
    private String heroName;

    private Col_Change() {
        images = new ArrayList<>();
        cards = Cards.purchasedCards();
        allCardPictures(cards);
        showHeroButtons();
        setLayout(null);
        addMouseListener(this);

        deckName = new JTextField();
        deckName.setFont(f2.deriveFont(25.0f));
        deckName.setBounds(nameX, nameY, 350, searchHeight);
        deckName.setFocusable(true);

        backButton.addMouseListener(this);
        backButton.setFont(f2);
        backButton.addActionListener(this);
        backButton.setBounds(deckX, 880, deckWidth, deckHeight);
        backButton.setFocusable(false);

        createButton.addMouseListener(this);
        createButton.setFont(f2);
        createButton.addActionListener(this);
        createButton.setBounds((gameWidth - deckWidth) / 2, 885, deckWidth, deckHeight);
        createButton.setFocusable(false);
        createButton.setEnabled(false);

        changeButton.addMouseListener(this);
        changeButton.setFont(f2);
        changeButton.addActionListener(this);
        changeButton.setBounds((gameWidth - deckWidth) / 2, 885, deckWidth, deckHeight);
        changeButton.setFocusable(false);
        changeButton.setEnabled(false);
        changeButton.setVisible(false);

        deckRemoveButton.addMouseListener(this);
        deckRemoveButton.setFont(f2);
        deckRemoveButton.addActionListener(this);
        deckRemoveButton.setBounds(50, 885, deckWidth, deckHeight);
        deckRemoveButton.setFocusable(false);
//        deckRemoveButton.setEnabled(false);
//        deckRemoveButton.setVisible(false);



        removeButton.addMouseListener(this);
        removeButton.setFont(f2.deriveFont(30.0f));
        removeButton.addActionListener(this);
        removeButton.setBounds(920, 550, 200, 50);
        removeButton.setFocusable(false);
        removeButton.setEnabled(true);
        removeButton.setBackground(Color.orange);

        addButton.setFocusable(false);
        addButton.setEnabled(true);
        addButton.setFont(f2.deriveFont(30.0f));
        addButton.setBackground(Color.orange);
        addButton.setBounds(720, 550, 200, 50);
        addButton.addActionListener(this);

        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(f2.deriveFont(30.0f));
        errorLabel.setFocusable(false);
        errorLabel.setBounds(50, 865, 600, 80);



        JPanel jPanel=new JPanel();;
        jPanel.setBackground(Color.ORANGE);
        jPanel.setBounds(0,nameY + searchHeight + 10,1200,850);
        jPanel.setPreferredSize(new Dimension(1200,850));
        add(jPanel);


    }

    public static Col_Change getInstance() {
        return col_change;
    }

    private void showHeroButtons() {
        if (Gamestate.getPlayer() != null) {
            int i = 1;
            for (Heroes playerHero : Gamestate.getPlayer().getPlayerHeroes()) {
                String s = playerHero.name().toLowerCase();
                JButton button = new JButton(s);
                button.setName(s);
                button.setBounds(deckX, deckY + (i * deckSpacing) + 5, deckWidth, deckHeight);
                button.setFont(f2.deriveFont(21.0f));
                button.setFocusable(false);
                button.addActionListener(this);
                this.add(button);
                buttons.add(button);
                i++;
            }
        }
    }

    public void allCardPictures(ArrayList<Cards> ar) {
        allBufferedImages = new ArrayList();
        for (Cards cards1 : ar) {
            BufferedImage bf = cardPics.get(cards1.getName().toLowerCase());
            allBufferedImages.add(bf);
        }
    }


    public void selectedCardPictures(ArrayList<Carts> ar) {
        selectedBuferredImages = new ArrayList();
        for (Carts carts : ar) {
            BufferedImage bf = cardPics.get(carts.toString());
            selectedBuferredImages.add(bf);
        }
    }

    private boolean canBeAddedToDeck(Carts cart) {
        if (selectedCards.size() >= maxCardInDeck)
            return false;
        int i = 0;
        for (Carts selectedCard : selectedCards) {
            if (selectedCard.equals(cart))
                i++;
        }
        if (i > 1)
            return false;
        return true;
    }

    private boolean canBeRemoved(Carts cart) {
        if (selectedCards == null) {
            selectedCards = new ArrayList<>();
        }
        if (selectedCards.contains(cart)) {
            return true;
        }
        return false;
    }


    private void addSelectedCard(String name) {
        if (selectedCards == null) {
            selectedCards = new ArrayList<>();
        }
        if (canBeAddedToDeck(Carts.valueOf(name.toLowerCase()))) {
            selectedCards.add(Carts.valueOf(name.toLowerCase()));
            selectedCardPictures(selectedCards);
        }
    }

    private void removeSelectedCard(String name) {
        selectedCards.remove(Carts.valueOf(name.toLowerCase()));
        selectedCardPictures(selectedCards);
    }

    private boolean deckCanBeCreated(boolean change) {
        if (deckName.getText() == null || deckName.getText().equals("")) {
            errorLabel.setText("Choose a name for your deck");
            revalidate();
            return false;
        }
        if (!change) {
            for (Map.Entry<String, Deck> entry : Gamestate.getPlayer().getAllDecks().entrySet()) {
                String st = entry.getKey();
                if (deckName.getText().equalsIgnoreCase(st)) {
                    errorLabel.setText("Name had been taken before !");
                    revalidate();
                    return false;
                }
            }
        }
        if (selectedCards.size() > maxCardInDeck || selectedCards.size() < minCardInDeck) {

            errorLabel.setText("Number of cards in your deck is invalid.");
            revalidate();
            return false;
        }
        return true;
    }

    private void clear(){
        selectedCards=new ArrayList<>();
        selectedBuferredImages=new ArrayList<>();
        deckName.setText("");
        clicked=false;
        cards=new ArrayList<>();
        images=new ArrayList<>();
        heroSelected=false;
        showHeroButtons();
    }

    private void ReinitializeDeck(){

    }

    private void createDeck() {
        Deck deck = new Deck(0, 0, deckName.getText());
        deck.setDeck(selectedCards);
        deck.setHero(JsonReaders.HeroJsonReader(Gamestate.getPlayer(),heroName));
        deck.setMostUsedCard(selectedCards.get(0));
        Gamestate.getPlayer().getAllDecks().put(deck.getName() , deck);
        JsonBuilders.PlayerJsonBuilder(Gamestate.getPlayer().getUsername(),Gamestate.getPlayer());
    }

    private void changeDeck(){
        Deck deck=selectedDeck;
        deck.setDeck(selectedCards);
        deck.setHero(JsonReaders.HeroJsonReader(Gamestate.getPlayer(),heroName));
         //  method for most used card //
        Gamestate.getPlayer().getAllDecks().replace(selectedDeck.getName(),selectedDeck,deck);
        JsonBuilders.PlayerJsonBuilder(Gamestate.getPlayer().getUsername(),Gamestate.getPlayer());
    }


     void updateSelectedDeck(String name) {
        if (Gamestate.getPlayer() != null) {
            ArrayList<Carts> ar1 = Gamestate.getPlayer().getPlayerCarts();
            ArrayList<Carts> ar2 = new ArrayList<>();
            ArrayList<Carts> ar3 = new ArrayList<>();
            for (Carts carts : ar1) {
                for (NeutralCarts value : NeutralCarts.values()) {
                    if (carts.toString().equalsIgnoreCase(value.toString())) {
                        ar2.add(carts);
                    }
                }
            }
            switch (name.toLowerCase()) {
                case "mage":
                    heroName = "mage";
                    ar3 = Mage.Spcards();
                    break;
                case "rogue":
                    heroName = "rogue";
                    ar3 = Rogue.Spcards();
                    break;
                case "warlock":
                    heroName = "warlock";
                    ar3 = Warlock.Spcards();
                    break;
//                case "priest":
//                      heroName="priest";
//                    ar3=Priest.Spcards();break;
//                case "hunter" :
//                heroName="hunter";
//                    ar3=Hunter.Spcards();break;
            }
            for (Carts carts : ar3) {
                if (Gamestate.getPlayer().getPlayerCarts().contains(carts)) {
                    ar2.add(carts);
                }
            }

            cards = Deck.UpdateDeck(ar2);
            allBufferedImages = new ArrayList<>();
            for (Carts carts : ar2) {
                BufferedImage bf = cardPics.get(carts.toString());
                allBufferedImages.add(bf);
            }
        }
    }

    private void drawBigger(String st) {
        clicked = true;
        name = st;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        if (!clicked) {
            removeAll();
            for (JButton button : buttons) {
                add(button);
            }
            add(deckName);
            add(createButton);
            add(backButton);
            add(errorLabel);
            add(deckRemoveButton);
            add(changeButton);
            g.setColor(Color.YELLOW);
            g.drawImage(status, 0, 0, 1600, 1000, null);
            g.drawLine(1350, nameY + searchHeight + 10, 1350, 680);
            g.drawLine(0, nameY + searchHeight + 10, 1600, nameY + searchHeight + 10);

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setFont(f2.deriveFont(40.0f));
            g2d.setColor(Color.YELLOW);
            g2d.setFont(f2);
            g2d.setColor(Color.yellow);
            g2d.drawString("Name :", nameX - 80, 55);
            g2d.drawLine(0, 875, 1600, 875);

            g2d.drawLine(0, 680, 1600, 680);
            if (heroSelected) {
                int i = 0;
                while (i < allBufferedImages.size()) {
                    g2d.drawImage(allBufferedImages.get(i), cardsX, cardsY, cardWidth, cardHeight, null);
                    images.add(new Images(cards.get(i).getName().toLowerCase(), cardsX, cardsY, cardWidth, cardHeight));
                    if (selectedCards != null && selectedCards.contains(Carts.valueOf(cards.get(i).getName().toLowerCase()))) {
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
                cardsY = 90;

                if (selectedBuferredImages != null) {
                    i = 0;
                    while (i < selectedBuferredImages.size()) {
                        g2d.drawImage(selectedBuferredImages.get(i), cardsX, 700, cardWidth / 3 * 2, cardHeight / 3 * 2, null);
                        cardsX = cardsX + cardWidth / 3 * 2;
                        i++;
                    }
                    cardsX = 15;
                    cardsY = 90;
                }
            }
//            errorLabel.setText("");
            if (selectedCards.size() >= maxCardInDeck) {
                errorLabel.setText("Deck is full");
            }
            mate = false;
            revalidate();
        } else {
            if (!mate) {
                removeAll();
                images.clear();
                g2d.setColor(new Color(222, 222, 222, 200));
                g2d.fillRect(0, 0, 1600, 1000);
                g2d.setColor(Color.white);
                mate = true;
                g2d.setFont(f2.deriveFont(50.0f));
                g2d.setColor(Color.red);
                addButton.setEnabled(false);
                removeButton.setEnabled(false);
                add(addButton);
                add(removeButton);
                if (canBeRemoved(Carts.valueOf(name))) {
                    removeButton.setEnabled(true);
                }
                if (canBeAddedToDeck(Carts.valueOf(name))) {
                    addButton.setEnabled(true);
                }

            }
            g2d.drawImage(cardPics.get(name), 300, 220, null);
        }
        deckName.requestFocus();

    }


     void changeButtonAction(){
        createButton.setVisible(false);
        changeButton.setVisible(true);
        changeButton.setEnabled(true);
        updatePictures();
        revalidate();
        repaint();

    }











    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        if (src == backButton) {
            clear();
            MyFrame.getInstance().changePanel("collection");
        } else if (src == addButton) {
            addSelectedCard(name.toLowerCase());
            clicked = false;
            repaint();
        } else if (src == removeButton) {
            removeSelectedCard(name.toLowerCase());
            clicked = false;
            repaint();
        }else if (src == deckRemoveButton){
            if (selectedDeck != null){
                Gamestate.getPlayer().getAllDecks().remove(selectedDeck.getName());
                Update.saveAndUpdate();
                MyFrame.getInstance().changePanel("collection");
            }
        }
        else if (src == createButton) {
                if (deckCanBeCreated(false)){
                    createDeck();
                    Update.refresh();
                    backButton.doClick();
                }
        }else if (src == changeButton){
            if (deckCanBeCreated(true)){
                changeDeck();
                Update.refresh();
                backButton.doClick();
            }
        }
        else {
            createButton.setEnabled(true);
            heroSelected = true;
            for (JButton button : buttons) {
                if (src == button) {
                    selectedCards=new ArrayList<>();
                    selectedBuferredImages=new ArrayList<>();
                    updateSelectedDeck(button.getName());
                    repaint();
                }
            }
        }
        revalidate();
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

    public JTextField getDeckName() {
        return deckName;
    }

    void updatePictures(){
    selectedCardPictures(selectedCards);
    repaint();
    }


    public void setHeroSelected(boolean heroSelected) {
        this.heroSelected = heroSelected;
    }

    public void setSelectedCards(ArrayList<Carts> selectedCards) {
        this.selectedCards = selectedCards;
    }

    public void setSelectedDeck(Deck selectedDeck) {
        this.selectedDeck = selectedDeck;
    }
}
