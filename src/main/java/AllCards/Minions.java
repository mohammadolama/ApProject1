package AllCards;

import Enums.Attribute;

import java.util.ArrayList;

public class Minions extends Cards {
    private int Attack;
    private int Health;
    private ArrayList<Enums.Attribute> Attribute;
    private String title;
    private boolean sleep;
    private boolean canBeAttacked;

    public Minions() {
        sleep=true;
        canBeAttacked=false;
    }

    public boolean isCanBeAttacked() {
        return canBeAttacked;
    }

    public void setCanBeAttacked(boolean canBeAttacked) {
        this.canBeAttacked = canBeAttacked;
    }

    public int getAttack() {
        return Attack;
    }

    public void setAttack(int attack) {
        Attack = attack;
    }

    public int getHealth() {
        return Health;
    }

    public void setHealth(int health) {
        Health = health;
    }

    public ArrayList<Enums.Attribute> getAttribute() {
        return Attribute;
    }

    public void setAttribute(ArrayList<Enums.Attribute> attribute) {
        Attribute = attribute;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void act(){
        System.out.println("action from minion");

    }

    public boolean isSleep() {
        return sleep;
    }

    public void setSleep(boolean sleep) {
        this.sleep = sleep;
    }
}
