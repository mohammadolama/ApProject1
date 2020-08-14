package Client.View.View.Panels;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.*;
import Client.Controller.Responses;
import Client.Model.CardModelView;
import Client.Model.DeckModel;
import Client.Model.Enums.Carts;
import Client.Model.Enums.Heroes;
import Client.Model.Images;
import Client.View.Configs.Col_ChangeConfig;
import Client.View.Configs.ConfigsLoader;
import Client.View.View.Panels.Listeners.Col_ChangeListeners.Col_ChangeAction;
import Client.View.View.Panels.Listeners.Col_ChangeListeners.Col_ChangeMouse;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static Client.View.View.Panels.Constants.*;


public class Col_Change extends JPanel {
    private static final Col_Change col_change = new Col_Change();

    private final ArrayList<JButton> buttons = new ArrayList<>();
    private ArrayList<Images> images;
    private ArrayList<CardModelView> cards;
    private ArrayList<BufferedImage> allBufferedImages;
    private ArrayList<BufferedImage> selectedBuferredImages;
    private final JTextField deckName;
    private String previousName;

    private boolean createMode = true;

    private DeckModel selectedDeck;

    private final JButton backButton = new JButton("Back");
    private final JButton createButton = new JButton("Create");
    private final JButton changeButton = new JButton("Change");
    private final JButton removeButton = new JButton("Remove");
    private final JButton deckRemoveButton = new JButton("Remove Deck");
    private final JButton addButton = new JButton("Add");

    private ArrayList<Carts> selectedCards = new ArrayList<>();

    private boolean heroSelected;
    private boolean clicked;
    private boolean mate;


    private String name1;
    private String heroName;
    private Col_ChangeConfig config;
    private final Col_ChangeAction ca = new Col_ChangeAction(this);
    private final Col_ChangeMouse cm = new Col_ChangeMouse(this);

    private void initConfig() {
        config = ConfigsLoader.getInstance().getCol_changeConfig();
    }

    private Col_Change() {
        initConfig();
        images = new ArrayList<>();
        RequestHandler.getInstance().sendRequest(new PurchasedCardsRequest());
        cards = Responses.getInstance().getPurchasedCards();
        allCardPictures(cards);
        showHeroButtons();
        setLayout(null);
        addMouseListener(cm);

        deckName = new JTextField();
        deckName.setFont(f2.deriveFont(25.0f));
        deckName.setBounds(config.getNameX(), config.getNameY(), 350, config.getSearchHeight());
        deckName.setFocusable(true);

        backButton.addMouseListener(cm);
        backButton.setFont(f2);
        backButton.addActionListener(ca);
        backButton.setBounds(config.getDeckX(), 880, config.getDeckWidth(), config.getDeckHeight());
        backButton.setFocusable(false);

        createButton.addMouseListener(cm);
        createButton.setFont(f2);
        createButton.addActionListener(ca);
        createButton.setBounds((gameWidth - config.getDeckWidth()) / 2, 885, config.getDeckWidth(), config.getDeckHeight());
        createButton.setFocusable(false);

        changeButton.addMouseListener(cm);
        changeButton.setFont(f2);
        changeButton.addActionListener(ca);
        changeButton.setBounds((gameWidth - config.getDeckWidth()) / 2, 885, config.getDeckWidth(), config.getDeckHeight());
        changeButton.setFocusable(false);

        deckRemoveButton.addMouseListener(cm);
        deckRemoveButton.setFont(f2);
        deckRemoveButton.addActionListener(ca);
        deckRemoveButton.setBounds(50, 885, config.getDeckWidth(), config.getDeckHeight());
        deckRemoveButton.setFocusable(false);


        removeButton.addMouseListener(cm);
        removeButton.setFont(f2.deriveFont(30.0f));
        removeButton.addActionListener(ca);
        removeButton.setBounds(config.getAddButtonX() + 200, config.getAddButtonY(), config.getAddButtonWidth(), config.getAddButtonHeight());
        removeButton.setFocusable(false);
        removeButton.setBackground(Color.orange);

        addButton.setFocusable(false);
        addButton.setEnabled(true);
        addButton.setFont(f2.deriveFont(30.0f));
        addButton.setBackground(Color.orange);
        addButton.setBounds(config.getAddButtonX(), config.getAddButtonY(), config.getAddButtonWidth(), config.getAddButtonHeight());
        addButton.addActionListener(ca);


        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.ORANGE);
        jPanel.setBounds(0, config.getNameY() + config.getSearchHeight() + 10, 1200, 850);
        jPanel.setPreferredSize(new Dimension(1200, 850));
        add(jPanel);


    }

    public static Col_Change getInstance() {
        return col_change;
    }

    private void showHeroButtons() {
        int i = 1;
        RequestHandler.getInstance().sendRequest(new PlayerHerosRequest());
        ArrayList<Heroes> list = Responses.getInstance().getHeroesList();
        for (Heroes playerHero : list) {
            String s = playerHero.name().toLowerCase();
            JButton button = new JButton(s);
            button.setName(s);
            button.setBounds(config.getDeckX(), config.getDeckY() + (i * config.getDeckSpacing()) + 5, config.getDeckWidth(), config.getDeckHeight());
            button.setFont(f2.deriveFont(21.0f));
            button.setFocusable(false);
            button.addActionListener(ca);
            this.add(button);
            buttons.add(button);
            i++;
        }

    }

    private void allCardPictures(ArrayList<CardModelView> ar) {
        allBufferedImages = new ArrayList<>();
        for (CardModelView cards1 : ar) {
            BufferedImage bf = cardPics.get(cards1.getName().toLowerCase());
            allBufferedImages.add(bf);
        }
    }


    private void selectedCardPictures(ArrayList<Carts> ar) {
        selectedBuferredImages = new ArrayList<>();
        for (Carts carts : ar) {
            BufferedImage bf = cardPics.get(carts.toString());
            selectedBuferredImages.add(bf);
        }
    }

    private boolean canBeAddedToDeck(Carts cart) {
        if (selectedCards.size() >= config.getMaxCardInDeck())
            return false;
        int i = 0;
        for (Carts selectedCard : selectedCards) {
            if (selectedCard.equals(cart))
                i++;
        }
        return i <= 1;
    }

    private boolean canBeRemoved(Carts cart) {
        if (selectedCards == null) {
            selectedCards = new ArrayList<>();
        }
        return selectedCards.contains(cart);
    }


    public void addSelectedCard(String name) {
        if (selectedCards == null) {
            selectedCards = new ArrayList<>();
        }
        if (canBeAddedToDeck(Carts.valueOf(name.toLowerCase()))) {
            if (selectedCards.contains(Carts.valueOf(name.toLowerCase()))) {
                int i = selectedCards.indexOf(Carts.valueOf(name.toLowerCase()));
                selectedCards.add(i + 1, Carts.valueOf(name.toLowerCase()));
            } else {
                selectedCards.add(Carts.valueOf(name.toLowerCase()));
            }
            selectedCardPictures(selectedCards);
            repaint();
        }
    }

    public void removeSelectedCard(String name) {
        selectedCards.remove(Carts.valueOf(name.toLowerCase()));
        selectedCardPictures(selectedCards);
    }

    public void clear() {
        selectedCards = new ArrayList<>();
        selectedBuferredImages = new ArrayList<>();
        deckName.setText("");
        clicked = false;
        cards = new ArrayList<>();
        images = new ArrayList<>();
        heroSelected = false;
        showHeroButtons();
    }

    public void createDeck() {
        RequestHandler.getInstance().sendRequest(new CreateDeckRequest(deckName.getText(), selectedCards, heroName));
    }

    public void changeDeck() {
        RequestHandler.getInstance().sendRequest(new ChangeDeckRequest(selectedDeck, heroName, previousName, deckName.getText(), selectedCards));
    }

    private void createMode() {
        remove(changeButton);
        remove(deckRemoveButton);
        add(createButton);
    }

    private void changeMode() {
        remove(createButton);
        add(deckRemoveButton);
        add(changeButton);
    }


    public void updateSelectedDeck(String name) {
        RequestHandler.getInstance().sendRequest(new CollectionRequest(name));
        ArrayList<Carts> ar2 = Responses.getInstance().getCollectionList();
        heroName = Responses.getInstance().getHeroName();
        cards = Responses.getInstance().getCollectionModels();
        allBufferedImages = new ArrayList<>();
        for (Carts carts : ar2) {
            BufferedImage bf = cardPics.get(carts.toString());
            allBufferedImages.add(bf);
        }
    }

    public void drawBigger(String st) {
        clicked = true;
        name1 = st;
        repaint();
    }

    public void update() {
        showHeroButtons();
    }

    private void refresh() {
        removeAll();
        for (JButton button : buttons) {
            add(button);
        }
        add(deckName);
        add(backButton);
        if (createMode) {
            createMode();
        } else changeMode();
        revalidate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        refresh();
        g.setColor(Color.YELLOW);
        g.drawImage(gamePics.get("status"), 0, 0, 1600, 1000, null);
        g.drawLine(1350, config.getNameY() + config.getSearchHeight() + 10, 1350, 680);
        g.drawLine(0, config.getNameY() + config.getSearchHeight() + 10, 1600, config.getNameY() + config.getSearchHeight() + 10);
        g2d.setFont(f2);
        g2d.drawString("Name :", config.getNameX() - 80, 55);
        g2d.drawLine(0, 875, 1600, 875);
        g2d.drawLine(0, 680, 1600, 680);
        if (heroSelected) {
            int i = 0;
            while (i < allBufferedImages.size()) {

                g2d.drawImage(allBufferedImages.get(i), config.getCardsX(), config.getCardsY(), config.getCardWidth(), config.getCardHeight(), null);
                images.add(new Images(cards.get(i).getName().toLowerCase(), config.getCardsX(), config.getCardsY(), config.getCardWidth(), config.getCardHeight(), i));
                if (selectedCards != null && selectedCards.contains(Carts.valueOf(cards.get(i).getName().toLowerCase()))) {
                    g2d.drawImage(gamePics.get("tick"), config.getCardsX() + 50, config.getCardsY(), null);
                }
                config.setCardsX(config.getCardsX() + config.getCardWidth());
                if (config.getCardsX() >= 1200) {
                    config.setCardsX(15);
                    config.setCardsY(config.getCardsY() + (config.getCardHeight()));
                }

                i++;
            }
            config.setCardsX(15);
            config.setCardsY(90);

            if (selectedBuferredImages != null) {
                i = 0;
                while (i < selectedBuferredImages.size()) {
                    g2d.drawImage(selectedBuferredImages.get(i), config.getCardsX(), config.getSelectedY(), config.getCardWidth() / 2, config.getCardHeight() / 2, null);
                    if (i < selectedBuferredImages.size() - 1 && selectedCards.get(i).toString().equalsIgnoreCase(selectedCards.get(i + 1).toString())) {
                        g2d.setFont(f2.deriveFont(20.0f));
                        g2d.setColor(Color.yellow);
                        g2d.drawString("X2", (config.getCardsX() + 30), (700 + config.getCardHeight() / 2));
                        i++;
                    }
                    config.setCardsX(config.getCardsX() + config.getCardWidth() / 2);
                    if (config.getCardsX() >= 1400) {
                        config.setCardsX(15);
                        config.setSelectedY(config.getSelectedY() + (config.getCardHeight() / 2));
                    }
                    i++;
                }
                config.setSelectedY(680);
                config.setCardsX(15);
                config.setCardsY(90);
            }
        }
        if (selectedCards.size() >= config.getMaxCardInDeck()) {
            JOptionPane.showMessageDialog(this, "Deck is full");
        }
        mate = false;
        revalidate();
        if (clicked) {
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
                if (canBeRemoved(Carts.valueOf(name1))) {
                    removeButton.setEnabled(true);
                }
                if (canBeAddedToDeck(Carts.valueOf(name1))) {
                    addButton.setEnabled(true);
                }

            }
            g2d.drawImage(cardPics.get(name1), 300, 220, null);
        }

        deckName.requestFocus();
    }

    public static Col_Change getCol_change() {
        return col_change;
    }

    public ArrayList<JButton> getButtons() {
        return buttons;
    }

    public ArrayList<Images> getImages() {
        return images;
    }

    public ArrayList<CardModelView> getCards() {
        return cards;
    }

    public void setSelectedBuferredImages(ArrayList<BufferedImage> selectedBuferredImages) {
        this.selectedBuferredImages = selectedBuferredImages;
    }

    public void setPreviousName(String previousName) {
        this.previousName = previousName;
    }

    public void setCreateMode(boolean createMode) {
        this.createMode = createMode;
    }

    public DeckModel getSelectedDeck() {
        return selectedDeck;
    }

    public void setSelectedDeck(DeckModel selectedDeck) {
        this.selectedDeck = selectedDeck;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getCreateButton() {
        return createButton;
    }

    public JButton getChangeButton() {
        return changeButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public JButton getDeckRemoveButton() {
        return deckRemoveButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public void setSelectedCards(ArrayList<Carts> selectedCards) {
        this.selectedCards = selectedCards;
    }

    public void setHeroSelected(boolean heroSelected) {
        this.heroSelected = heroSelected;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public String getName1() {
        return name1;
    }


    public JTextField getDeckName() {
        return deckName;
    }
}
