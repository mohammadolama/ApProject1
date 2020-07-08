package Model.Cards;

import Controller.Actions.Visitor;
import Model.Enums.*;
import Main.Deck;
import Main.Gamestate;
import Main.JsonReaders;
import Model.Heros.Hero;
import Model.Interface.Character;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Collections;

@JsonIgnoreProperties({"rarityI", "typeI", "life", "attack"})
public abstract class Card implements Character {
    private String name;
    private String description;
    private int manaCost;
    private Type type;
    private String heroClass;
    private Rarity rarity;
    private int price;
    private ArrayList<Model.Enums.Attribute> attributes;
    private boolean needFriendlyTarget;
    private boolean needEnemyTarget;
    private boolean continiousAction;
    private int healthRestore;
    private int attackRestore;

    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {

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

    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<Attribute> attributes) {
        this.attributes = attributes;
    }

    public boolean isContiniousAction() {
        return continiousAction;
    }

    public void setContiniousAction(boolean continiousAction) {
        this.continiousAction = continiousAction;
    }


    public int getHealthRestore() {
        return healthRestore;
    }

    public void setHealthRestore(int healthRestore) {
        this.healthRestore = healthRestore;
    }

    public int getAttackRestore() {
        return attackRestore;
    }

    public void setAttackRestore(int attackRestore) {
        this.attackRestore = attackRestore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public int getRarityI() {
        return getRarity().getI();
    }

    public int getTypeI() {
        return getType().getI();
    }

    public static ArrayList<Card> allCards() {
        ArrayList<Carts> ar = new ArrayList<>();
        Collections.addAll(ar, Carts.values());
        return Deck.UpdateDeck(ar);
    }

    public static ArrayList<Card> purchasedCards() {
        ArrayList<Carts> ar = new ArrayList<>(Gamestate.getPlayer().getPlayerCarts());
        return Deck.UpdateDeck(ar);
    }

    public static ArrayList<Card> lockedCards() {
        ArrayList<Carts> ar = new ArrayList<>();
        outer:
        for (Carts carts : Carts.values()) {
            for (Carts playerCart : Gamestate.getPlayer().getPlayerCarts()) {
                if (carts.toString().equalsIgnoreCase(playerCart.toString())) {
                    continue outer;
                }
            }
            ar.add(carts);
        }
        return Deck.UpdateDeck(ar);
    }

    public static ArrayList<Card> neutralCardsFilter() {
        ArrayList<Carts> ar = new ArrayList<>();
        for (NeutralCarts value : NeutralCarts.values()) {
            ar.add(Carts.valueOf(value.toString()));
        }
        return Deck.UpdateDeck(ar);
    }

    public static ArrayList<Card> specialCardsFilter() {
        ArrayList<Carts> ar = new ArrayList<>();
        for (SpecialCarts value : SpecialCarts.values()) {
            ar.add(Carts.valueOf(value.toString()));
        }
        return Deck.UpdateDeck(ar);
    }

    public static Card getCardOf(String name) {
        for (int i = 0; i < 1; i++) {
            for (MinionCarts value : MinionCarts.values()) {
                if (value.toString().equalsIgnoreCase(name)) {
                    return JsonReaders.MinionsReader(name.toLowerCase());
                }
            }
            for (SpellCarts value : SpellCarts.values()) {
                if (value.toString().equalsIgnoreCase(name)) {
                    return JsonReaders.SpellReader(name.toLowerCase());
                }
            }
            for (WeaponCarts value : WeaponCarts.values()) {
                if (value.toString().equalsIgnoreCase(name)) {
                    return JsonReaders.WeaponReader(name.toLowerCase());
                }
            }
        }
        return null;
    }

    @Override
    public int getAttack() {
        return 0;
    }

    @Override
    public void setAttack(int i) {

    }

    @Override
    public int getLife() {
        return 0;
    }

    @Override
    public void setLife(int i) {
    }

    @Override
    public int getMaxLife() {
        return 0;
    }
}