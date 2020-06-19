package View.GUI.Panels;

import AllCards.Cards;
import AllCards.Minions;
import AllCards.Spell;
import AllCards.Weapon;
import Model.Images;
import View.GUI.Configs.ConfigsLoader;
import Main.Gamestate;
import Main.Shop;
import Util.Admin;
import View.GUI.Configs.CollectionDrawingConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


import static View.GUI.Panels.CollectionPanel.calculateHeight;
import static View.GUI.Panels.Constants.*;
import static View.GUI.Panels.Constants.heroPics;

public class CollectionDrawingPanel extends JPanel implements MouseListener, ActionListener {

    private ArrayList<Cards> cards;
    private ArrayList<Images> images;
    private static ArrayList<BufferedImage> bufferedImages;
    private static ArrayList<BufferedImage> purchasedCards;
    private static ArrayList<BufferedImage> notPurchasedCards;

    private JButton buyButton;
    private CollectionDrawingConfig config;
    private Admin admin;
    private int panelHeight = 1000;
    private String name;
    private boolean clicked;
    private boolean mate;
    private boolean specialSelected;

    private void initConfig() {
        config = ConfigsLoader.getInstance().getCollectionDrawingConfig();
    }

    private static final CollectionDrawingPanel col = new CollectionDrawingPanel();

    private CollectionDrawingPanel() {
        initConfig();
        admin = Admin.getInstance();
        cards = new ArrayList<>();
        images = new ArrayList<>();
        bufferedImages = new ArrayList<>();
        cards = admin.properCards(3);
        pictures(cards);
        addMouseListener(this);

        buyButton = new JButton("Buy");
        buyButton.setFocusable(false);
        buyButton.setEnabled(true);
        buyButton.setFont(f2.deriveFont(30.0f));
        buyButton.setBackground(Color.orange);
        buyButton.setBounds(720, panelHeight / 2, 200, 50);
        buyButton.addActionListener(this);

        update();
    }

    public static CollectionDrawingPanel getInstance() {
        return col;
    }

    private void drawBigger(String st) {
        clicked = true;
        name = st;
        repaint();
    }


    private void pictures(ArrayList<Cards> ar) {
        bufferedImages = new ArrayList<>();
        for (Cards cards1 : ar) {
            BufferedImage bf = cardPics.get(cards1.getName().toLowerCase());
            bufferedImages.add(bf);
        }

    }

    public void updateContent(ArrayList<Cards> cards) {
        images.clear();
        this.cards = new ArrayList<>();
        this.cards = cards;
        pictures(this.cards);
        revalidate();
        this.repaint();

    }

    private String getClass(String string) {
        for (Cards card : cards) {
            if (card.getName().equalsIgnoreCase(string)) {
                return card.getHeroClass();
            }
        }
        return "Neutral";
    }


    private void update() {
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
        if (!mate) {
            removeAll();
            images.clear();
            g2d.setColor(new Color(222, 222, 222, 200));
            g2d.fillRect(0, 0, 1600, panelHeight);
            g2d.setColor(Color.white);
            mate = true;
            if (contains(cardPics.get(name))) {
                buyButton.setEnabled(true);
                buyButton.setBackground(Color.ORANGE);
                g2d.setFont(f2.deriveFont(40.0f));
                g2d.setColor(Color.red);
                g2d.drawString("Price : " + Shop.Price(name.toLowerCase()), 720, panelHeight / 2);
                if (Shop.Price((name.toLowerCase())) > Gamestate.getPlayer().getMoney()) {
                    admin.playSound("gold");
                    buyButton.setEnabled(false);
                    buyButton.setBackground(Color.LIGHT_GRAY);
                }
                buyButton.setBounds(720, panelHeight / 2 + 100, 200, 50);
                add(buyButton);
            }
        }
        g2d.setFont(f2.deriveFont(40.0f));
        g2d.setColor(Color.BLUE);
        g2d.drawString(getClass(name.toLowerCase()), 850, panelHeight / 2 - 50);
        g2d.setColor(Color.red);
        g2d.drawString("Class : ", 720, panelHeight / 2 - 50);
        g2d.drawImage(cardPics.get(name), 300, panelHeight / 2 - 200, null);
        drawCardInfo(g2d, name, 300, panelHeight / 2 - 200);

    }

    private void drawCardInfo(Graphics2D g, String cards, int xe, int ye) {
        g.setFont(fantasy.deriveFont(60.0f));
        g.setColor(Color.white);
        if (admin.getCardOf(cards) instanceof Minions) {
            int x = xe + 40;
            int y = ye + 95;
            g.drawString(admin.defaultCardMana(admin.getCardOf(cards)) + "", x, y);
            g.drawString((admin.cardMAttack((Minions) admin.getCardOf(cards)) + ""), x, y + 445);
            g.drawString((admin.cardHp((Minions) admin.getCardOf(cards)) + ""), x + 300, y + 440);
        } else if (admin.getCardOf(cards) instanceof Weapon) {
            int x = xe + 45;
            int y = ye + 65;
            g.drawString(admin.defaultCardMana(admin.getCardOf(cards)) + "", x, y);
            g.drawString((admin.cardWAttack((Weapon) admin.getCardOf(cards)) + ""), x, y + 445);
            g.drawString((admin.cardDurability((Weapon) admin.getCardOf(cards)) + ""), x + 300, y + 440);
        } else if (admin.getCardOf(cards) instanceof Spell) {
            int x = xe + 45;
            int y = ye + 60;
            g.drawString(admin.defaultCardMana(admin.getCardOf(cards)) + "", x, y);
        }
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

        if (st == null || st.equals("")) {
            clicked = false;
            repaint();
            return;
        }
        drawBigger(st);
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

    public void setSpecialSelected(boolean specialSelected) {
        this.specialSelected = specialSelected;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton src = (JButton) e.getSource();
        if (src == buyButton) {
            admin.buyCard(name);
            cards = admin.properCards(3);
            pictures(cards);
            images.clear();
            update();
            clicked = false;
            repaint();
        }
    }
}
