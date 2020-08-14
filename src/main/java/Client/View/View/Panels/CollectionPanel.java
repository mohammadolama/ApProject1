package Client.View.View.Panels;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.PlayerDecksRequest;
import Client.Controller.Responses;
import Client.Model.CardModelView;
import Client.Model.DeckModel;
import Client.View.Configs.CollectionConfig;
import Client.View.Configs.ConfigsLoader;
import Client.View.Configs.CustomScrollBarUI;
import Client.View.View.Panels.Listeners.CollectionListeners.CollectionAction;
import Client.View.View.Panels.Listeners.CollectionListeners.CollectionChange;
import Client.View.View.Panels.Listeners.CollectionListeners.CollectionDocument;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CollectionPanel extends JPanel {

    private static final CollectionPanel col = new CollectionPanel();

    private final JButton backButton = new JButton();
    private final JButton allCards = new JButton("All");
    private final JButton lockedCards = new JButton("Locked");
    private final JButton unlockedCards = new JButton("Unlocked");
    private final JButton neutralCards = new JButton("Neutral");
    private final JButton specialCards = new JButton("Special");
    private final JButton newDeck = new JButton("New Deck");
    private final JButton changeButton = new JButton("Change");
    private final JButton exitButton = new JButton();
    private final JButton selectButton = new JButton();
    private final JLabel errorLabel = new JLabel();
    private final JTextField searchField;
    private final JSlider manaFilter;
    private final JScrollPane scrollPane;
    private ArrayList<CardModelView> cards;
    private final CollectionDrawingPanel collectionDrawingPanel;
    private final ArrayList<JButton> buttons;
    private DeckModel selectedDeck;
    private CollectionConfig config;
    private final CollectionChange cc = new CollectionChange(this);
    private final CollectionAction ca = new CollectionAction(this);
    private final CollectionDocument cd = new CollectionDocument(this);

    private void initConfig() {
        config = ConfigsLoader.getInstance().getCollectionConfig();
    }

    private CollectionPanel() {
        initConfig();
        setLayout(null);

        buttons = new ArrayList<>();

        searchField = new JTextField();
        searchField.setFont(Constants.f2.deriveFont(25.0f));
        searchField.setBounds(config.getSearchX(), config.getSearchY(), 350, config.getSearchHeight());
        searchField.setFocusable(true);
        searchField.getDocument().addDocumentListener(cd);

        allCards.setFont(Constants.f2);
        allCards.addActionListener(ca);
        allCards.setBounds(config.getAllCardsX(), config.getSearchY(), config.getAllCardWidth(), config.getSearchHeight());
        allCards.setFocusable(false);

        lockedCards.setFont(Constants.f2);
        lockedCards.addActionListener(ca);
        lockedCards.setBounds(config.getAllCardsX() + config.getAllCardWidth() + 20, config.getSearchY(), config.getAllCardWidth(), config.getSearchHeight());
        lockedCards.setFocusable(false);

        unlockedCards.setFont(Constants.f2);
        unlockedCards.addActionListener(ca);
        unlockedCards.setBounds(config.getAllCardsX() + (2 * config.getAllCardWidth()) + 40, config.getSearchY(), config.getAllCardWidth(), config.getSearchHeight());
        unlockedCards.setFocusable(false);

        neutralCards.setFont(Constants.f2);
        neutralCards.addActionListener(ca);
        neutralCards.setBounds(config.getAllCardsX() + (3 * config.getAllCardWidth()) + 60, config.getSearchY(), config.getAllCardWidth(), config.getSearchHeight());
        neutralCards.setFocusable(false);

        specialCards.setFont(Constants.f2);
        specialCards.addActionListener(ca);
        specialCards.setBounds(config.getAllCardsX() + (4 * config.getAllCardWidth()) + 80, config.getSearchY(), config.getAllCardWidth(), config.getSearchHeight());
        specialCards.setFocusable(false);

        newDeck.setFont(Constants.f2);
        newDeck.addActionListener(ca);
        newDeck.setBounds(config.getDeckX(), config.getDeckY(), config.getDeckWidth(), config.getDeckHeight());
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
        manaFilter.setLabelTable(Constants.getTable());
        manaFilter.setPaintLabels(true);
        manaFilter.addChangeListener(cc);
        manaFilter.setFont(Constants.f2.deriveFont(30.0f));
        manaFilter.setBounds(config.getManaX(), config.getManaY(), config.getManaWidth(), config.getSearchHeight());

        changeButton.setFont(Constants.f2);
        changeButton.addActionListener(ca);
        changeButton.setBounds(config.getManaX() + config.getManaWidth() + 30, config.getManaY(), config.getDeckWidth(), config.getDeckHeight());
        changeButton.setFocusable(false);
        changeButton.setEnabled(false);

        errorLabel.setFont(Constants.f2);
        errorLabel.setForeground(Color.red);
        errorLabel.setFocusable(false);
        errorLabel.setBounds(changeButton.getX() + config.getDeckWidth() + 60, config.getManaY(), 400, config.getDeckHeight());


        backButton.addActionListener(ca);
        backButton.setBounds(config.getDeckX() + 75, 880, 60, 60);
        backButton.setFocusable(false);
        backButton.setIcon(Constants.gameIcon.get("back"));
        backButton.setContentAreaFilled(false);
        backButton.setRolloverEnabled(false);
        backButton.setBorderPainted(false);

        exitButton.addActionListener(ca);
        exitButton.setIcon(Constants.gameIcon.get("exit"));
        exitButton.setBounds(config.getDeckX() + 150, 880, 60, 60);
        exitButton.setFocusable(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setRolloverEnabled(false);
        exitButton.setBorderPainted(false);


        selectButton.addActionListener(ca);
        selectButton.setIcon(Constants.gameIcon.get("select"));
        selectButton.setBounds(config.getDeckX(), 880, 60, 60);
        selectButton.setFocusable(false);
        selectButton.setContentAreaFilled(false);
        selectButton.setRolloverEnabled(false);
        selectButton.setBorderPainted(false);


        collectionDrawingPanel = CollectionDrawingPanel.getInstance();
        collectionDrawingPanel.setSize(1000, 1600);
        collectionDrawingPanel.setPreferredSize(new Dimension(1000, 1600));
        collectionDrawingPanel.setBounds(0, 0, 1000, 1600);
        collectionDrawingPanel.setFocusable(true);
        collectionDrawingPanel.requestFocus();


        scrollPane = new JScrollPane(collectionDrawingPanel);
        scrollPane.setSize(1000, 600);
        scrollPane.setPreferredSize(new Dimension(1000, 600));
        scrollPane.setBounds(0, config.getSearchHeight() + 35, 1350, config.getManaY() - 100);
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
        int i = 1;
        buttons.clear();
        RequestHandler.getInstance().sendRequest(new PlayerDecksRequest());
        HashMap<String, DeckModel> map = Responses.getInstance().getDecks();
        for (Map.Entry<String, DeckModel> entry : map.entrySet()) {
            String s = entry.getKey();
            JButton button = new JButton(s);
            button.setName(s);
            button.setBounds(config.getDeckX(), config.getDeckY() + (i * config.getDeckSpacing()), config.getDeckWidth(), config.getDeckHeight());
            button.setFont(Constants.f2.deriveFont(16.0f));
            button.setFocusable(false);
            button.addActionListener(ca);
            this.add(button);
            buttons.add(button);
            i++;
        }
    }

    public void refresh() {
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


    public static CollectionPanel getInstance() {
        return col;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.YELLOW);
        g2d.setFont(Constants.f2.deriveFont(28.0f));
        g.drawImage(Constants.gamePics.get("collection"), 0, 0, 1600, 1000, null);
        g.drawLine(1350, 0, 1350, 1000);
        g.drawLine(0, config.getSearchY() + config.getSearchHeight() + 10, 1350, config.getSearchY() + config.getSearchHeight() + 10);
        g.drawLine(0, config.getManaY() - 10, 1350, config.getManaY() - 10);
        g2d.drawString("Search :", 75, 55);
    }






    static int calculateHeight(int rows, int cardHeight) {
        int height = rows * cardHeight;
        if (height <= 800) {
            return 800;
        }
        return height + 50;
    }

    void updateDimension(int rows, int cardHeight) {
        Dimension d = collectionDrawingPanel.getPreferredSize();
        d.height = calculateHeight(rows, cardHeight);
        collectionDrawingPanel.setPreferredSize(d);
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

    public JButton getExitButton() {
        return exitButton;
    }

    public JButton getSelectButton() {
        return selectButton;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JSlider getManaFilter() {
        return manaFilter;
    }


    public ArrayList<CardModelView> getCards() {
        return cards;
    }

    public void setCards(ArrayList<CardModelView> cards) {
        this.cards = cards;
    }

    public ArrayList<JButton> getButtons() {
        return buttons;
    }


    public DeckModel getSelectedDeck() {
        return selectedDeck;
    }

    public JButton getChangeButton() {
        return changeButton;
    }

    public void setSelectedDeck(DeckModel selectedDeck) {
        this.selectedDeck = selectedDeck;
    }
}
