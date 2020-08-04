package Client.View.View.Panels;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.*;
import Client.Controller.Responses;
import Client.Model.CardModelView;
import Client.Model.DeckModel;
import Client.View.Configs.CollectionConfig;
import Client.View.Configs.ConfigsLoader;
import Client.View.Configs.CustomScrollBarUI;
import Server.Model.Cards.Card;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CollectionPanel extends JPanel implements ActionListener, MouseListener, ChangeListener {

    private static final CollectionPanel col = new CollectionPanel();

    private JButton backButton = new JButton();
    private JButton allCards = new JButton("All");
    private JButton lockedCards = new JButton("Locked");
    private JButton unlockedCards = new JButton("Unlocked");
    private JButton neutralCards = new JButton("Neutral");
    private JButton specialCards = new JButton("Special");
    private JButton newDeck = new JButton("New Deck");
    private JButton changeButton = new JButton("Change");
    private JButton exitButton = new JButton();
    private JButton selectButton = new JButton();
    private JLabel errorLabel = new JLabel();
    private JTextField searchField;
    private JSlider manaFilter;
    private JScrollPane scrollPane;
    private ArrayList<CardModelView> cards;
    private CollectionDrawingPanel collectionDrawingPanel;

    private static ArrayList<JButton> buttons;

    private DeckModel selectedDeck;

    private CollectionConfig config;


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
                RequestHandler.getInstance().sendRequest(new ProperCardsRequest(3));
                ArrayList<CardModelView> ar = Responses.getInstance().getModelviewList();
                cards = new ArrayList<>();
                for (CardModelView cards1 : ar) {
                    if (cards1.getName().toLowerCase().contains(searchField.getText())) {
                        cards.add(cards1);
                    }
                }
                CollectionDrawingPanel.getInstance().updateContent(cards);
                repaint();

            }
        });

        allCards.addMouseListener(this);
        allCards.setFont(Constants.f2);
        allCards.addActionListener(this);
        allCards.setBounds(config.getAllCardsX(), config.getSearchY(), config.getAllCardWidth(), config.getSearchHeight());
        allCards.setFocusable(false);

        lockedCards.addMouseListener(this);
        lockedCards.setFont(Constants.f2);
        lockedCards.addActionListener(this);
        lockedCards.setBounds(config.getAllCardsX() + config.getAllCardWidth() + 20, config.getSearchY(), config.getAllCardWidth(), config.getSearchHeight());
        lockedCards.setFocusable(false);

        unlockedCards.addMouseListener(this);
        unlockedCards.setFont(Constants.f2);
        unlockedCards.addActionListener(this);
        unlockedCards.setBounds(config.getAllCardsX() + (2 * config.getAllCardWidth()) + 40, config.getSearchY(), config.getAllCardWidth(), config.getSearchHeight());
        unlockedCards.setFocusable(false);

        neutralCards.addMouseListener(this);
        neutralCards.setFont(Constants.f2);
        neutralCards.addActionListener(this);
        neutralCards.setBounds(config.getAllCardsX() + (3 * config.getAllCardWidth()) + 60, config.getSearchY(), config.getAllCardWidth(), config.getSearchHeight());
        neutralCards.setFocusable(false);

        specialCards.addMouseListener(this);
        specialCards.setFont(Constants.f2);
        specialCards.addActionListener(this);
        specialCards.setBounds(config.getAllCardsX() + (4 * config.getAllCardWidth()) + 80, config.getSearchY(), config.getAllCardWidth(), config.getSearchHeight());
        specialCards.setFocusable(false);

        newDeck.addMouseListener(this);
        newDeck.setFont(Constants.f2);
        newDeck.addActionListener(this);
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
        manaFilter.addChangeListener(this);
        manaFilter.setFont(Constants.f2.deriveFont(30.0f));
        manaFilter.setBounds(config.getManaX(), config.getManaY(), config.getManaWidth(), config.getSearchHeight());

        changeButton.addMouseListener(this);
        changeButton.setFont(Constants.f2);
        changeButton.addActionListener(this);
        changeButton.setBounds(config.getManaX() + config.getManaWidth() + 30, config.getManaY(), config.getDeckWidth(), config.getDeckHeight());
        changeButton.setFocusable(false);
        changeButton.setEnabled(false);

        errorLabel.setFont(Constants.f2);
        errorLabel.setForeground(Color.red);
        errorLabel.setFocusable(false);
        errorLabel.setBounds(changeButton.getX() + config.getDeckWidth() + 60, config.getManaY(), 400, config.getDeckHeight());


        backButton.addMouseListener(this);
        backButton.addActionListener(this);
        backButton.setBounds(config.getDeckX() + 75, 880, 60, 60);
        backButton.setFocusable(false);
        backButton.setIcon(Constants.gameIcon.get("back"));
        backButton.setContentAreaFilled(false);
        backButton.setRolloverEnabled(false);
        backButton.setBorderPainted(false);

        exitButton.addActionListener(this);
        exitButton.addMouseListener(this);
        exitButton.setIcon(Constants.gameIcon.get("exit"));
        exitButton.setBounds(config.getDeckX() + 150, 880, 60, 60);
        exitButton.setFocusable(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setRolloverEnabled(false);
        exitButton.setBorderPainted(false);


        selectButton.addActionListener(this);
        selectButton.addMouseListener(this);
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
            button.addActionListener(this);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        if (src == backButton) {
            allCards.doClick();
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Exit Button"));
            RequestHandler.getInstance().sendRequest(new LogRequest("Navigate : Main Menu"));
            RequestHandler.getInstance().sendRequest(new SaveRequest());
            RequestHandler.getInstance().sendRequest(new VisiblePanelRequest("menu"));

        } else if (src == exitButton) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Exit Button"));
            RequestHandler.getInstance().sendRequest(new ExitRequest());
        } else if (src == newDeck) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : New_Deck Button"));
            RequestHandler.getInstance().sendRequest(new CreateNewDeckRequest());
        } else if (src == allCards) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : AllCards Button"));
            RequestHandler.getInstance().sendRequest(new UpdateDrawingPanelRequest("all"));
        } else if (src == lockedCards) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : LockedCards Button"));
            RequestHandler.getInstance().sendRequest(new UpdateDrawingPanelRequest("locked"));
        } else if (src == unlockedCards) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : UnlockedCards Button"));
            RequestHandler.getInstance().sendRequest(new UpdateDrawingPanelRequest("unlocked"));
        } else if (src == neutralCards) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : NeutralCards Button"));
            RequestHandler.getInstance().sendRequest(new UpdateDrawingPanelRequest("neutral"));
        } else if (src == specialCards) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : SpecialCards Button"));
            RequestHandler.getInstance().sendRequest(new UpdateDrawingPanelRequest("special"));
        } else if (src == changeButton) {
            RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Change_Deck Button"));
            allCards.doClick();
            Col_Change.getInstance().setCreateMode(false);
            Col_Change.getInstance().getDeckName().setText(selectedDeck.getName());
            Col_Change.getInstance().updateSelectedDeck(selectedDeck.getHero());
            Col_Change.getInstance().setHeroSelected(true);
            Col_Change.getInstance().setSelectedCards(selectedDeck.getList());
            Col_Change.getInstance().setSelectedDeck(selectedDeck);
            Col_Change.getInstance().setCreateMode(false);
            MyFrame.getInstance().changePanel("col");
        } else if (src == selectButton) {
            if (selectedDeck != null) {
                RequestHandler.getInstance().sendRequest(new SelectDeckRequest(selectedDeck));
                RequestHandler.getInstance().sendRequest(new LogRequest("Click_Button : Select Button"));
                RequestHandler.getInstance().sendRequest(new LogRequest(String.format("Deck : choose \"%s\" as selected deck.", selectedDeck.getName())));
                JOptionPane.showMessageDialog(this, String.format("%s is your selected deck now.", selectedDeck.getName()));
            }
        } else {
            changeButton.setEnabled(true);
            for (JButton button : buttons) {
                CollectionDrawingPanel.getInstance().setSpecialSelected(false);
                if (src.getName().equalsIgnoreCase(button.getName())) {
                    RequestHandler.getInstance().sendRequest(new DeckModelRequest(button.getName()));
                    selectedDeck = Responses.getInstance().getDeckModel();
                    RequestHandler.getInstance().sendRequest(new UpdateDrawingPanelRequest(button.getName()));
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
            RequestHandler.getInstance().sendRequest(new ProperCardsRequest(3));
            cards = Responses.getInstance().getModelviewList();
        } else {
            RequestHandler.getInstance().sendRequest(new ProperCardsRequest(3));
            ArrayList<CardModelView> ar = Responses.getInstance().getModelviewList();
            cards = new ArrayList<>();
            for (CardModelView cards1 : ar) {
                if (cards1.getManaCost() == value) {
                    cards.add(cards1);
                }
            }
        }
        CollectionDrawingPanel.getInstance().updateContent(cards);
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

    public JButton getChangeButton() {
        return changeButton;
    }

    public void setSelectedDeck(DeckModel selectedDeck) {
        this.selectedDeck = selectedDeck;
    }
}
