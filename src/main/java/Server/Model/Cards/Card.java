package Server.Model.Cards;

import Server.Controller.Actions.CardVisitors.Visitor;
import Server.Controller.Manager.Managers;
import Server.Model.Enums.Attribute;
import Server.Model.Enums.Rarity;
import Server.Model.Enums.Type;
import Server.Model.Heros.Hero;
import Server.Model.Interface.Character;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public abstract class Card implements Character {
    @Id
    private String name;
    @Column
    private String description;
    @Column
    private int manaCost;
    @Column
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column
    private String heroClass;
    @Column
    @Enumerated(EnumType.STRING)
    private Rarity rarity;
    @Column
    private int price;
    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection(targetClass = Attribute.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "attributes")
    @Column
//    @Transient
    private List<Attribute> attributes;
    @Column
    private boolean needFriendlyTarget;
    @Column
    private boolean needEnemyTarget;
    @Column
    private boolean continiousAction;
    @Column
    private int healthRestore;
    @Column
    private int attackRestore;

    public abstract void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers);


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

    public abstract int getAttack();

    public abstract void setAttack(int i);

    public abstract int getLife();

    public abstract void setLife(int i);

    public abstract int getMaxLife();

}