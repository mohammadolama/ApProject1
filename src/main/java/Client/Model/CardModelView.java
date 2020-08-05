package Client.Model;

import Client.Model.Enums.Attribute;
import Client.Model.Enums.Type;

import java.awt.image.BufferedImage;
import java.util.List;

public class CardModelView {

    private BufferedImage image;
    private int manaCost;
    private int damage;
    private int hp;
    private Type type;
    private String name;
    private boolean sleep;
    private boolean canBeAttacked;
    private boolean needFriendlyTarget;
    private boolean needEnemyTarget;
    private List<Attribute> attributes;

    public CardModelView(BufferedImage image, String name, int manaCost, int damage, int hp, Type type, List<Attribute> attributes, boolean sleep, boolean canBeAttacked, boolean needFriendlyTarget, boolean needEnemyTarget) {
        this.image = image;
        this.manaCost = manaCost;
        this.damage = damage;
        this.hp = hp;
        this.type = type;
        this.name = name;
        this.attributes = attributes;
        this.sleep = sleep;
        this.canBeAttacked = canBeAttacked;
        this.needFriendlyTarget = needFriendlyTarget;
        this.needEnemyTarget = needEnemyTarget;
    }

    public CardModelView(BufferedImage image, String name, int manaCost, Type type, boolean needFriendlyTarget, boolean needEnemyTarget) {
        this.image = image;
        this.manaCost = manaCost;
        this.name = name;
        this.type = Type.Spell;
        this.needFriendlyTarget = needFriendlyTarget;
        this.needEnemyTarget = needEnemyTarget;
    }

    public CardModelView(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public CardModelView() {
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

    public boolean isSleep() {
        return sleep;
    }

    public void setSleep(boolean sleep) {
        this.sleep = sleep;
    }

    public boolean isCanBeAttacked() {
        return canBeAttacked;
    }

    public void setCanBeAttacked(boolean canBeAttacked) {
        this.canBeAttacked = canBeAttacked;
    }

    public boolean isNeedFriendlyTarget() {
        return needFriendlyTarget;
    }

    public void setNeedFriendlyTarget(boolean needFriendlyTarget) {
        this.needFriendlyTarget = needFriendlyTarget;
    }

    public boolean isNeedEnemyTarget() {
        return needEnemyTarget;
    }

    public void setNeedEnemyTarget(boolean needEnemyTarget) {
        this.needEnemyTarget = needEnemyTarget;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
}
