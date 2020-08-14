package Client.View.View.Panels;

import Client.Controller.RequestHandler;
import Client.Controller.Requests.*;
import Client.Controller.Responses;
import Client.Model.CardModelView;
import Client.Model.Enums.Type;
import Client.Model.Images;
import Client.View.Configs.CollectionDrawingConfig;
import Client.View.Configs.ConfigsLoader;
import Client.View.View.Panels.Listeners.CollectionDrawingListeners.CDAction;
import Client.View.View.Panels.Listeners.CollectionDrawingListeners.CDMouse;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static Client.View.View.Panels.CollectionPanel.calculateHeight;
import static Client.View.View.Panels.Constants.*;

public class CollectionDrawingPanel extends JPanel {

    private ArrayList<CardModelView> cards;
    private ArrayList<Images> images;
    private static ArrayList<BufferedImage> bufferedImages;
    private static ArrayList<BufferedImage> purchasedCards;
    private static ArrayList<BufferedImage> notPurchasedCards;

    private final JButton buyButton;
    private CollectionDrawingConfig config;
    private int panelHeight = 1000;
    private String name1;
    private boolean clicked;
    private boolean mate;
    private boolean specialSelected;
    private final CDMouse cm = new CDMouse(this);
    private final CDAction ca = new CDAction(this);

    private void initConfig() {
        config = ConfigsLoader.getInstance().getCollectionDrawingConfig();
    }

    private static final CollectionDrawingPanel col = new CollectionDrawingPanel();

    private CollectionDrawingPanel() {
        initConfig();
        cards = new ArrayList<>();
        images = new ArrayList<>();
        bufferedImages = new ArrayList<>();
        RequestHandler.getInstance().sendRequest(new ProperCardsRequest(3));
        cards = Responses.getInstance().getModelviewList();
        pictures(cards);
        addMouseListener(cm);

        buyButton = new JButton("Buy");
        buyButton.setFocusable(false);
        buyButton.setEnabled(true);
        buyButton.setFont(f2.deriveFont(30.0f));
        buyButton.setBackground(Color.orange);
        buyButton.setBounds(720, panelHeight / 2, 200, 50);
        buyButton.addActionListener(ca);

        update();
    }

    public static CollectionDrawingPanel getInstance() {
        return col;
    }

    public void drawBigger(String st) {
        clicked = true;
        name1 = st;
        repaint();
    }


    public void pictures(ArrayList<CardModelView> ar) {
        bufferedImages = new ArrayList<>();
        for (CardModelView cards1 : ar) {
            BufferedImage bf = cardPics.get(cards1.getName().toLowerCase());
            bufferedImages.add(bf);
        }

    }

    public void updateContent(ArrayList<CardModelView> cards) {
        images.clear();
        this.cards = cards;
        pictures(this.cards);
        revalidate();
        this.repaint();

    }


    public void update() {
        purchasedCards = new ArrayList<>();
        notPurchasedCards = new ArrayList<>();
        RequestHandler.getInstance().sendRequest(new PurchasedCardsRequest());
        RequestHandler.getInstance().sendRequest(new NotPurchasedCardsRequest());

        ArrayList<CardModelView> list1 = Responses.getInstance().getPurchasedCards();
        ArrayList<CardModelView> list2 = Responses.getInstance().getNotPurchasedCards();

        for (CardModelView purchasedCard : list1) {
            BufferedImage bf = cardPics.get(purchasedCard.getName().toLowerCase());
            purchasedCards.add(bf);
        }
        for (CardModelView lockedCard : list2) {
            BufferedImage bf = cardPics.get(lockedCard.getName().toLowerCase());
            notPurchasedCards.add(bf);
        }
    }

    private boolean contains(BufferedImage bufferedImage) {
        if (purchasedCards == null && notPurchasedCards == null) {
            purchasedCards = new ArrayList<>();
            notPurchasedCards = new ArrayList<>();
        }
        for (BufferedImage notPurchasedCard : notPurchasedCards) {
            if (notPurchasedCard.equals(bufferedImage)) {
                return true;
            }
        }
        return false;
    }


    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        removeAll();
        update();
        g.setColor(Color.YELLOW);
        g.drawImage(gamePics.get("collection"), 0, 0, 1600, panelHeight + 20, null);
        DrawImage(g2d);
        if (specialSelected) {
            g2d.drawImage(heroPics.get("rogue"), 30, config.getCardsY(), config.getCardWidth() * 3 / 2, config.getCardHeight() * 3 / 2, null);
            g2d.drawImage(heroPics.get("mage"), 330, config.getCardsY(), config.getCardWidth() * 3 / 2, config.getCardHeight() * 3 / 2, null);
            g2d.drawImage(heroPics.get("warlock"), 580, config.getCardsY(), config.getCardWidth() * 3 / 2, config.getCardHeight() * 3 / 2, null);
            g2d.drawImage(heroPics.get("hunter"), 850, config.getCardsY(), config.getCardWidth() * 3 / 2, config.getCardHeight() * 3 / 2, null);
            g2d.drawImage(heroPics.get("priest"), 1100, config.getCardsY(), config.getCardWidth() * 3 / 2, config.getCardHeight() * 3 / 2, null);
        }
        mate = false;

        if (clicked) {
            drawBiggerImage(g2d);
        }
    }

    private void DrawImage(Graphics2D g2d) {
        int i = 0;
        int rows = 0;
        while (i < bufferedImages.size()) {
            if (specialSelected) {
                config.setCardsY(config.getSearchY() + config.getSearchHeight() + 300);
                if (i > 1 && i % 2 == 0) {
                    g2d.setColor(Color.yellow);
                    g2d.drawLine(config.getCardsX(), config.getSearchY() + config.getSearchHeight() + 10, config.getCardsX(), config.getManaY() - 10);
                }
            }

            g2d.drawImage(bufferedImages.get(i), config.getCardsX(), config.getCardsY(), config.getCardWidth(), config.getCardHeight(), null);
            images.add(new Images(cards.get(i).getName().toLowerCase(), config.getCardsX(), config.getCardsY(), config.getCardWidth(), config.getCardHeight() , i));


            g2d.setColor(new Color(50, 50, 50, 180));
            if (contains(bufferedImages.get(i))) {
                g2d.fillRect(config.getCardsX() + 10, config.getCardsY() + 10, config.getCardWidth() - 15, config.getCardHeight() - 15);
            }


            if (i < bufferedImages.size() - 1 && cards.get(i).getName().equalsIgnoreCase(cards.get(i + 1).getName())) {
                g2d.setFont(f2.deriveFont(30.f));
                g2d.setColor(Color.yellow);
                g2d.drawString("X2", (config.getCardsX() + 50), (config.getCardsY() + config.getCardHeight()));
                i++;
            }
            config.setCardsX(config.getCardsX() + config.getCardWidth());
            if (config.getCardsX() >= 1200) {
                config.setCardsX(15);
                config.setCardsY(config.getCardsY() + (config.getCardHeight()));
                rows++;
            }

            i++;
        }
        config.setCardsX(15);
        config.setCardsY(20);
        CollectionPanel.getInstance().updateDimension(rows + 1, config.getCardHeight());
        panelHeight = calculateHeight(rows + 1, config.getCardHeight());
    }

    private void drawBiggerImage(Graphics2D g2d) {
        RequestHandler.getInstance().sendRequest(new PriceRequest(name1.toLowerCase()));
        long price = Responses.getInstance().getPrice();
        String className = Responses.getInstance().getClassName();
        if (!mate) {
            removeAll();
            images.clear();
            g2d.setColor(new Color(222, 222, 222, 200));
            g2d.fillRect(0, 0, 1600, panelHeight);
            g2d.setColor(Color.white);
            mate = true;
            if (contains(cardPics.get(name1))) {
                buyButton.setEnabled(true);
                buyButton.setBackground(Color.ORANGE);
                g2d.setFont(f2.deriveFont(40.0f));
                g2d.setColor(Color.red);
                g2d.drawString("Price : " + price, 720, panelHeight / 2);
                buyButton.setBounds(720, panelHeight / 2 + 100, 200, 50);
                add(buyButton);
            }
        }
        g2d.setFont(f2.deriveFont(40.0f));
        g2d.setColor(Color.BLUE);
        g2d.drawString(className, 850, panelHeight / 2 - 50);
        g2d.setColor(Color.red);
        g2d.drawString("Class : ", 720, panelHeight / 2 - 50);
        g2d.drawImage(cardPics.get(name1), 300, panelHeight / 2 - 200, null);
        drawCardInfo(g2d, name1, 300, panelHeight / 2 - 200);

    }

    private void drawCardInfo(Graphics2D g, String cards, int xe, int ye) {
        g.setFont(fantasy.deriveFont(60.0f));
        g.setColor(Color.white);
        RequestHandler.getInstance().sendRequest(new PureModelViewRequest(cards));
        CardModelView view = Responses.getInstance().getCardModelView();
        if (view.getType().equals(Type.Minion)) {
            int x = xe + 40;
            int y = ye + 95;
            g.drawString(view.getManaCost() + "", x, y);
            g.drawString((view.getDamage() + ""), x, y + 445);
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


    public void setSpecialSelected(boolean specialSelected) {
        this.specialSelected = specialSelected;
    }

    public ArrayList<CardModelView> getCards() {
        return cards;
    }

    public void setCards(ArrayList<CardModelView> cards) {
        this.cards = cards;
    }

    public ArrayList<Images> getImages() {
        return images;
    }

    public void setImages(ArrayList<Images> images) {
        this.images = images;
    }


    public JButton getBuyButton() {
        return buyButton;
    }

    public String getName1() {
        return name1;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

}
