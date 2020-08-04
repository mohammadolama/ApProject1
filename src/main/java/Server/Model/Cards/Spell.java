package Server.Model.Cards;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public abstract class Spell extends Card {
    @Column
    private int usageTimes;
    @Column
    private int manaSpendOnSth;
    @Column
    private int maxManaSpendOnSth;

    public int getUsageTimes() {
        return usageTimes;
    }

    public void setUsageTimes(int usageTimes) {
        this.usageTimes = usageTimes;
    }

    public int getManaSpendOnSth() {
        return manaSpendOnSth;
    }

    public void setManaSpendOnSth(int manaSpendOnSth) {
        this.manaSpendOnSth = manaSpendOnSth;
    }

    public int getMaxManaSpendOnSth() {
        return maxManaSpendOnSth;
    }

    public void setMaxManaSpendOnSth(int maxManaSpendOnSth) {
        this.maxManaSpendOnSth = maxManaSpendOnSth;
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
        return 0;
    }
}
