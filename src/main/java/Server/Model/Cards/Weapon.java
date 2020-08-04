package Server.Model.Cards;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public abstract class Weapon extends Card {
    @Column
    private int damage;
    @Column
    private int durability;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    @Override
    public int getAttack() {
        return this.getAttack();
    }

    @Override
    public void setAttack(int i) {
        this.setAttack(i);
    }

    @Override
    public int getLife() {
        return this.getLife();
    }

    @Override
    public void setLife(int i) {
        this.setLife(i);
    }

    @Override
    public int getMaxLife() {
        return getMaxLife();
    }
}
