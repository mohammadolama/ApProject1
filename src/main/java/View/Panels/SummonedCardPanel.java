package View.Panels;

import Model.CardModelView;

import javax.swing.*;
import java.awt.*;

import static View.Panels.Constants.f2;

public class SummonedCardPanel extends JPanel {

    private CardModelView view;
    private int damage;
    private int HP;
    private int mode;

    public SummonedCardPanel() {
        setSize(new Dimension(350, 400));
        setPreferredSize(new Dimension(350, 400));
    }

    public CardModelView getView() {
        return view;
    }

    public void setView(CardModelView view) {
        this.view = view;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    @Override
    protected void paintComponent(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setFont(f2.deriveFont(50.0f));
        if (view != null) {
            g.drawImage(view.getImage(), 0, 0, 350, 400, null);
            if (mode != 0) {
                g.setColor(Color.green);
                g.drawString(damage + "", 40, 380);
                g.drawString(HP + "", 290, 380);
            }
        }
    }

}
