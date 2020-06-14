package Model;

import Enums.Type;

import java.awt.image.BufferedImage;

public class CardModelView {

    private BufferedImage image;
    private int manaCost;
    private int damage;
    private int hp;
    private Type type;
    private String name;

    public CardModelView(BufferedImage image,String name, int manaCost, int damage, int hp , Type type) {
        this.image = image;
        this.manaCost = manaCost;
        this.damage = damage;
        this.hp = hp;
        this.type=type;
        this.name=name;
    }

    public CardModelView(BufferedImage image,String name, int manaCost) {
        this.image = image;
        this.manaCost = manaCost;
        this.name=name;
        this.type=Type.Spell;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
