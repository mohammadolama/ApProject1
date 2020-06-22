package Model.Heros;


import Model.Cards.HeroPower;
import Model.Enums.Carts;
import Model.Enums.Heroes;
import Main.Player;
import Model.Interface.Character;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties({"purchasedCards", "CardsInDeck"})
public class Hero implements Character {
    private Player player;
    private String name;
    private int hp;
    private Boolean canAttack;
    private int att;
    private String specialPower;
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Boolean getCanAttack() {
        return canAttack;
    }

    public void setCanAttack(Boolean canAttack) {
        this.canAttack = canAttack;
    }

    public int getAtt() {
        return att;
    }

    public void setAtt(int att) {
        this.att = att;
    }

    public String getSpecialPower() {
        return specialPower;
    }

    public void setSpecialPower(String specialPower) {
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
        if (!ar2.contains(Carts.dreadscale))
            ar2.add(Carts.dreadscale);
        if (!ar2.contains(Carts.polymorph))
            ar2.add(Carts.polymorph);
        if (!ar2.contains(Carts.friendlysmith))
            ar2.add(Carts.friendlysmith);
        if (!ar2.contains(Carts.swampkingdred))
            ar2.add(Carts.swampkingdred);
        if (!ar2.contains(Carts.highpriestamet))
            ar2.add(Carts.highpriestamet);
        player.setPlayerCarts(ar2);
    }

    @Override
    public int getat() {
        return getAtt();
    }

    @Override
    public int getHE() {
        return getHp();
    }

    @Override
    public void setHE(int i) {
        setHp(i);
    }

    @Override
    public void setat(int i) {
        setAtt(i);
    }
}


