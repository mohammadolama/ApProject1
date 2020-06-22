package Model.Cards;

import Model.Interface.Character;

import java.util.ArrayList;

public class Minion extends Card implements Character {
    private int Attack;
    private int Health;
    private ArrayList<Model.Enums.Attribute> Attribute;
    private String title;
    private boolean sleep;
    private boolean canBeAttacked;
    private int healthRestore;

    public Minion() {
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

    public ArrayList<Model.Enums.Attribute> getAttribute() {
        return Attribute;
    }

    public void setAttribute(ArrayList<Model.Enums.Attribute> attribute) {
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

    @Override
    public int getat() {
        return getAttack();
    }

    @Override
    public int getHE() {
        return getHealth();
    }

    @Override
    public void setHE(int i) {
        setHealth(i);
    }

    @Override
    public void setat(int i) {
            setAttack(i);
    }

    public int getHealthRestore() {
        return healthRestore;
    }

    public void setHealthRestore(int healthRestore) {
        this.healthRestore = healthRestore;
    }
}
