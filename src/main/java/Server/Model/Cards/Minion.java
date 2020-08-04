package Server.Model.Cards;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@Entity
public abstract class Minion extends Card implements Cloneable {
    @Column
    private int damage;
    @Column
    private int health;
    @Column
    private int maxHealth;
    @Column
    private String title;
    @Column
    private boolean sleep;
    @Column
    private boolean canBeAttacked;

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

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSleep() {
        return sleep;
    }

    public void setSleep(boolean sleep) {
        this.sleep = sleep;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    @Override
    public int getAttack() {
        return this.getDamage();
    }

    @Override
    public void setAttack(int i) {
        this.setDamage(i);
    }

    @Override
    public int getLife() {
        return this.getHealth();
    }

    @Override
    public void setLife(int i) {
        this.setHealth(i);
    }

    @Override
    public int getMaxLife() {
        return this.getMaxHealth();
    }

    public <T> T cloneMinion(T t) {
        Class cls = t.getClass();
        T t1 = null;
        try {
            Constructor cons = cls.getConstructor();
            t1 = (T) cons.newInstance();
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(t1, field.get(t));
            }
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return t1;
    }
}




