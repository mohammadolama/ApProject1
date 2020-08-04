package Client.View.View.Panels;

import Client.Model.CardModelView;
import Client.Model.Enums.Type;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Client.View.View.Panels.Constants.fantasy;

public class CardPreview extends JPanel implements ActionListener {

    private CardModelView cardModelView;
    private int cardwidth = 250;
    private int cardheight =350;

    private static CardPreview cardPreview=new CardPreview();

    private CardPreview() {

    }

    public static CardPreview getInstance(){
        return cardPreview;
    }


    @Override
    protected void paintComponent(Graphics gx) {
        super.paintComponent(gx);
        Graphics2D g=(Graphics2D) gx;
        g.setFont(fantasy.deriveFont(50.0f));
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING , RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        if (cardModelView!=null) {
            g.drawImage(cardModelView.getImage(), 5, 5, cardwidth, cardheight, null);
            drawCardInfo(g);
        }
    }


    private void drawCardInfo(Graphics2D g) {
        g.setColor(Color.white);
        if (cardModelView.getType() == null) {

        } else if (cardModelView.getType().equals(Type.Minion)) {
            String  mana=cardModelView.getManaCost()+"";
            String  damage=cardModelView.getDamage()+"";
            String  hp=cardModelView.getHp()+"";
            g.drawString(mana , 32,79);
            g.drawString(damage ,35 ,340);
            g.drawString(hp,212 ,340);
        } else if (cardModelView.getType().equals(Type.Spell)){
            String  mana=cardModelView.getManaCost()+"";
            g.drawString(mana , 32,60);
        }else if (cardModelView.getType().equals(Type.Weapon)){
            String  mana=cardModelView.getManaCost()+"";
            String  damage=cardModelView.getDamage()+"";
            String  durability=cardModelView.getHp()+"";
            g.drawString(mana , 32,60);
            g.drawString(damage,32 ,335);
            g.drawString(durability ,205 ,335);
        }
    }

    public void setCardModelView(CardModelView cardModelView) {
        this.cardModelView = cardModelView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
