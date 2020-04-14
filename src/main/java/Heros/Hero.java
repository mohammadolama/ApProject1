package Heros;

import AllCards.Cards;
import Enums.*;
import Main.JsonReaders;
import Main.Player;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.IOException;
import java.util.ArrayList;

@JsonIgnoreProperties({"purchasedCards", "CardsInDeck"})
public class Hero {
    private Player player;
    private String name;
    private int HP;
    private Boolean CanAttack;
    private int ATT;
    private String SpecialPower;
    private String HeroPower;
    private int HeroPowerManaCost;

    private  ArrayList<Carts> Pcarts = new ArrayList<>(); //Purchased Not in deck Cards
    private  ArrayList<Carts> Dcarts;   //Cards in your deck
    private  ArrayList<Carts> SPcarts;   //SPecial cards of each hero
    @JsonIgnore
    private static ArrayList CardsInDeck;

    public Hero(Player player){
        this.player=player;
    }
    public Hero(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public Boolean getCanAttack() {
        return CanAttack;
    }

    public void setCanAttack(Boolean canAttack) {
        CanAttack = canAttack;
    }

    public int getATT() {
        return ATT;
    }

    public void setATT(int ATT) {
        this.ATT = ATT;
    }

    public String getSpecialPower() {
        return SpecialPower;
    }

    public void setSpecialPower(String specialPower) {
        SpecialPower = specialPower;
    }

    public String getHeroPower() {
        return HeroPower;
    }

    public void setHeroPower(String heroPower) {
        HeroPower = heroPower;
    }

    public int getHeroPowerManaCost() {
        return HeroPowerManaCost;
    }

    public void setHeroPowerManaCost(int heroPowerManaCost) {
        HeroPowerManaCost = heroPowerManaCost;
    }

    public ArrayList<Carts> getPcarts() {
        return Pcarts;
    }

    public void setPcarts(ArrayList<Carts> pcarts) {
        Pcarts = pcarts;
    }

    public ArrayList<Carts> getDcarts() {
        return Dcarts;
    }

    public void setDcarts(ArrayList<Carts> dcarts) {
        Dcarts = dcarts;
    }

    public ArrayList<Carts> getSPcarts() {
        return SPcarts;
    }

    public void setSPcarts(ArrayList<Carts> SPcarts) {
        this.SPcarts = SPcarts;
    }

    public static ArrayList getCardsInDeck() {
        return CardsInDeck;
    }

    public static void setCardsInDeck(ArrayList cardsInDeck) {
        CardsInDeck = cardsInDeck;
    }





    public void UpdateAddPurchasedCards(Carts cartss) {
        ArrayList<Carts> ar = this.Pcarts;
        ar.add(cartss);
        this.Pcarts = ar;
    }

    public void UpdateAddDeckCards(Carts cartss) {
        ArrayList<Carts> ar = this.Dcarts;
        ar.add(cartss);
        this.Dcarts = ar;
    }

    public void UpdateRemovePurchasedCards(Carts cartss) {
        ArrayList<Carts> ar = this.Pcarts;
        ar.remove(cartss);
        this.Pcarts = ar;
    }
}


