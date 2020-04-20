package GUI;

import AllCards.Cards;
import Main.Gamestate;
import Main.Shop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;

import static GUI.CollectionPanel.calculateHeight;
import static GUI.Constants.*;
import static GUI.Constants.heroPics;

public class CollectionDrawingPanel extends JPanel implements MouseListener, ActionListener {

    private ArrayList<Cards> cards;
    private ArrayList<Images> images;
    private static ArrayList<BufferedImage> bufferedImages;
    private static ArrayList<BufferedImage> purchasedCards;
    private static ArrayList<BufferedImage> notPurchasedCards;
    private Cards selectedCard;

    private JButton buyButton;

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
    private int cardsY =  20;
    private int cardHeight = 195;
    private int cardWidth = 130;






    private int panelHeight=1000;

    private String name;

    private boolean clicked;
    private boolean mate;
    private boolean specialSelected;




    private static final CollectionDrawingPanel col=new CollectionDrawingPanel();

    private CollectionDrawingPanel(){
        cards=new ArrayList<>();
        images=new ArrayList<>();
        bufferedImages=new ArrayList<>();
        cards=Cards.allCards();
        pictures(cards);
        setBackground(Color.RED);
        addMouseListener(this);


        buyButton = new JButton("Buy");
        buyButton.setFocusable(false);
        buyButton.setEnabled(true);
        buyButton.setFont(f2.deriveFont(30.0f));
        buyButton.setBackground(Color.orange);
        buyButton.setBounds(720, panelHeight/2, 200, 50);
        buyButton.addActionListener(this);

        update();
    }

    public static CollectionDrawingPanel getInstance(){
        return col;
    }

    private void drawBigger(String st) {
        clicked = true;
        name = st;
        repaint();
    }


    public void pictures(ArrayList<Cards> ar) {
        bufferedImages = new ArrayList();
        for (Cards cards1 : ar) {
            BufferedImage bf = cardPics.get(cards1.getName().toLowerCase());
            bufferedImages.add(bf);
        }

    }

    void updateContent(ArrayList<Cards> cards) {
        images.clear();
        this.cards = new ArrayList<>();
        this.cards = cards;
        pictures(this.cards);
        revalidate();
        this.repaint();

    }

    String getClass(String string){
        for (Cards card : cards) {
            if (card.getName().equalsIgnoreCase(string)){
                return card.getHeroClass();
            }
        }
        return "Neutral";
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

    boolean contains(BufferedImage bufferedImage) {
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
        Graphics2D g2d=(Graphics2D) g;
        removeAll();
        g.setColor(Color.YELLOW);
        g.drawImage(status, 0, 0, 1600, panelHeight+20, null);
        int i = 0;
        int rows=0;
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
            }





            cardsX = cardsX + cardWidth;
            if (cardsX >= 1200) {
                cardsX = 15;
                cardsY = cardsY + (cardHeight);
                rows++;
            }
            i++;
        }
        cardsX = 15;
        cardsY =  20;

        if (specialSelected) {
            g2d.drawImage(heroPics.get("rogue"), 30, cardsY, cardWidth * 3 / 2, cardHeight * 3 / 2, null);
            g2d.drawImage(heroPics.get("mage"), 330, cardsY, cardWidth * 3 / 2, cardHeight * 3 / 2, null);
            g2d.drawImage(heroPics.get("warlock"), 580, cardsY, cardWidth * 3 / 2, cardHeight * 3 / 2, null);
            g2d.drawImage(heroPics.get("hunter"), 850, cardsY, cardWidth * 3 / 2, cardHeight * 3 / 2, null);
            g2d.drawImage(heroPics.get("priest"), 1100, cardsY, cardWidth * 3 / 2, cardHeight * 3 / 2, null);
        }
        mate = false;

        if (clicked){
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
                    g2d.drawString("Price : " + Shop.Price(name.toLowerCase()), 720, panelHeight/2);
                    if (Shop.Price((name.toLowerCase())) > Gamestate.getPlayer().getMoney()) {
                        buyButton.setEnabled(false);
                        buyButton.setBackground(Color.LIGHT_GRAY);
                    }
                    buyButton.setBounds(720, panelHeight/2+100, 200, 50);
                    add(buyButton);
                }
            }
            g2d.setFont(f2.deriveFont(40.0f));
            g2d.drawImage(cardPics.get(name), 300, panelHeight/2-200, null);
            g2d.setColor(Color.green);
            g2d.drawString( getClass(name.toLowerCase()), 850, panelHeight/2-50);
            g2d.setColor(Color.red);
            g2d.drawString("Class : " , 720, panelHeight/2-50);


        }
        CollectionPanel.getInstance().updateDimension(rows+1,cardHeight);
        panelHeight=calculateHeight(rows+1,cardHeight);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        panelHeight+=20;
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
        JButton src=(JButton) e.getSource();
        if (src == buyButton){
            Shop.Buy(name.toLowerCase());
            cards = new ArrayList<>();
            cards = Cards.allCards();
            pictures(cards);
            images.clear();
            update();
            clicked = false;
            repaint();
        }
    }
}
