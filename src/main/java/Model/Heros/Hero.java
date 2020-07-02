package Model.Heros;


import Model.Cards.*;
import Model.HeroPowers.HeroPower;
import Model.Enums.Carts;
import Model.Enums.Heroes;
import Main.Player;
import Model.Interface.Character;
import Model.SpecialPowers.SpecialPower;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import java.util.ArrayList;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "model")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Hunter.class, name = "hunter"),
        @JsonSubTypes.Type(value = Warlock.class, name = "warlock"),
        @JsonSubTypes.Type(value = Mage.class, name = "mage"),
        @JsonSubTypes.Type(value = Rogue.class, name = "rogue"),
        @JsonSubTypes.Type(value = Priest.class, name = "priest"),
})
@JsonIgnoreProperties({"specialPower", "heroPower"})
public class Hero implements Character {
    private String name;
    private int health;
    private int maxHealth;
    private Boolean canAttack;
    private int damage;
    private SpecialPower specialPower;
    private HeroPower heroPower;
    private int heroPowerManaCost;
    private int defence;

    public Hero() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void HeroAdder(Player player) {
        ArrayList<Heroes> ar = player.getPlayerHeroes();
        if (!ar.contains(Heroes.mage))
            ar.add(Heroes.mage);
        if (!ar.contains(Heroes.warlock))
            ar.add(Heroes.warlock);
        if (!ar.contains(Heroes.rogue))
            ar.add(Heroes.rogue);
        if (!ar.contains(Heroes.priest))
            ar.add(Heroes.priest);
        if (!ar.contains(Heroes.hunter))
            ar.add(Heroes.hunter);
        player.setPlayerHeroes(ar);
        ArrayList<Carts> ar2 = player.getPlayerCarts();
        if (!ar2.contains(Carts.benyamin))
            ar2.add(Carts.benyamin);
        if (!ar2.contains(Carts.polymorph))
            ar2.add(Carts.polymorph);
        if (!ar2.contains(Carts.aylar))
            ar2.add(Carts.aylar);
        if (!ar2.contains(Carts.faeze))
            ar2.add(Carts.faeze);
        if (!ar2.contains(Carts.shahryar))
            ar2.add(Carts.shahryar);
        player.setPlayerCarts(ar2);
    }

    public int getHealth() {
        return health;
    }

    public Boolean getCanAttack() {
        return canAttack;
    }

    public void setCanAttack(Boolean canAttack) {
        this.canAttack = canAttack;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public SpecialPower getSpecialPower() {
        return specialPower;
    }

    public void setSpecialPower(SpecialPower specialPower) {
        this.specialPower = specialPower;
    }

    public HeroPower getHeroPower() {
        return heroPower;
    }

    public void setHeroPower(HeroPower heroPower) {
        this.heroPower = heroPower;
    }

    public int getHeroPowerManaCost() {
        return heroPowerManaCost;
    }

    public void setHeroPowerManaCost(int heroPowerManaCost) {
        this.heroPowerManaCost = heroPowerManaCost;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    @Override
    public int getAttack() {
        return getDamage();
    }

    @Override
    public void setAttack(int i) {
        setDamage(i);
    }

    @Override
    public int getLife() {
        return getHealth();
    }

    @Override
    public void setLife(int i) {
        setHealth(i);
    }
}

