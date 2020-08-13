package Server.Controller.Response;

import Server.Model.CardModelView;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("notifysummon")
public class NotifySummon implements Response {
    private CardModelView view;
    private int mode;
    private int damage;
    private int hp;

    public NotifySummon(CardModelView view, int mode, int damage, int hp) {
        this.view = view;
        this.mode = mode;
        this.damage = damage;
        this.hp = hp;
    }

    public NotifySummon() {
    }

    public CardModelView getView() {
        return view;
    }

    public void setView(CardModelView view) {
        this.view = view;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
