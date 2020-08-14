package Client.View.View.Panels;

import Client.Model.CardModelView;
import Client.View.Configs.ConfigsLoader;
import Client.View.Configs.SummonedConfig;

import javax.swing.*;
import java.awt.*;

import static Client.View.View.Panels.Constants.cardPics;
import static Client.View.View.Panels.Constants.f2;

public class SummonedCardPanel extends JPanel {

    private final SummonedConfig config;

    private CardModelView view;
    private int damage;
    private int HP;
    private int mode;

    SummonedCardPanel() {
        config = ConfigsLoader.getInstance().getSummonedConfig();
        setSize(new Dimension(config.getWidth(), config.getHeight()));
        setPreferredSize(new Dimension(config.getWidth(), config.getHeight()));
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

    void setHP(int HP) {
        this.HP = HP;
    }

    void setMode(int mode) {
        this.mode = mode;
    }

    @Override
    protected void paintComponent(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setFont(f2.deriveFont(50.0f));
        if (view != null) {
            g.drawImage(cardPics.get(view.getName().toLowerCase()), 0, 0, config.getWidth(), config.getHeight(), null);
            if (mode != 0) {
                g.setColor(Color.green);
                g.drawString(damage + "", config.getDamageX(), config.getDamageYl());
                g.drawString(HP + "", config.getHpX(), config.getDamageYl());
            }
        }
    }

}
