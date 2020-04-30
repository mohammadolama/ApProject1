package GUI.Panels;

import AllCards.Minions;
import AllCards.Spell;
import AllCards.Weapon;
import Util.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import static GUI.Panels.Constants.fantasy;

public class CardPreview extends JPanel implements ActionListener {

    private BufferedImage bufferedImage;
    private String  cards;

    private Admin admin;
    private int cardwidth = 250;
    private int cardheight =350;

    private static CardPreview cardPreview=new CardPreview();

    private CardPreview(){
        admin=Admin.getInstance();
    }

    public static CardPreview getInstance(){
        return cardPreview;
    }


    @Override
    protected void paintComponent(Graphics gx) {
        super.paintComponent(gx);
        Graphics2D g=(Graphics2D) gx;
        g.setFont(fantasy.deriveFont(50.0f));
        g.setColor(Color.white);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING , RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        if (bufferedImage!=null)
        g.drawImage(bufferedImage,5,5,cardwidth , cardheight,null);
        drawCardInfo(g);
    }


    private void drawCardInfo(Graphics2D g){
        if (admin.getCardOf(cards) instanceof Minions){
            g.drawString(admin.cardMana(admin.getCardOf(cards))+"" , 32,79);
            g.drawString((admin.cardMAttack((Minions) admin.getCardOf(cards))+"") ,35 ,340);
            g.drawString((admin.cardHp((Minions) admin.getCardOf(cards))+""),212 ,340);
        }else if (admin.getCardOf(cards) instanceof Weapon){
            g.drawString(admin.cardMana(admin.getCardOf(cards))+"" , 32,60);
            g.drawString((admin.cardWAttack((Weapon) admin.getCardOf(cards))+""),32 ,335);
            g.drawString((admin.cardDurability((Weapon) admin.getCardOf(cards))+"") ,205 ,335);
        }else if (admin.getCardOf(cards) instanceof Spell){
            g.drawString(admin.cardMana(admin.getCardOf(cards))+"" , 32,60);
        }
    }

    void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }


    public String  getCards() {
        return cards;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
