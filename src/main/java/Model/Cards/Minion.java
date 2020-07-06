package Model.Cards;

import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "model")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AghaHaghi.class, name = "aghahaghi"),
        @JsonSubTypes.Type(value = Ali.class, name = "ali"),
        @JsonSubTypes.Type(value = Benyamin.class, name = "benyamin"),
        @JsonSubTypes.Type(value = Cat.class, name = "cat"),
        @JsonSubTypes.Type(value = Faeze.class, name = "faeze"),
        @JsonSubTypes.Type(value = HighMasterSaman.class, name = "highmastersaman"),
        @JsonSubTypes.Type(value = HosseinHima.class, name = "hosseinhima"),
        @JsonSubTypes.Type(value = Hossein.class, name = "hossein"),
        @JsonSubTypes.Type(value = Javad.class, name = "javad"),
        @JsonSubTypes.Type(value = Khashayar.class, name = "khashayar"),
        @JsonSubTypes.Type(value = Lachin.class, name = "lachin"),
        @JsonSubTypes.Type(value = Matin.class, name = "matin"),
        @JsonSubTypes.Type(value = Mobin.class, name = "mobin"),
        @JsonSubTypes.Type(value = Nima.class, name = "nima"),
        @JsonSubTypes.Type(value = Shahryar.class, name = "shahryar"),
        @JsonSubTypes.Type(value = Yasaman.class, name = "yasaman"),
})
public abstract class Minion extends Card implements Cloneable {
    private int damage;
    private int health;
    private int maxHealth;
    private String title;
    private boolean sleep;
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




