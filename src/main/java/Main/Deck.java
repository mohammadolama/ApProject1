package Main;

import Enums.Carts;
import Heros.Hero;

import java.io.IOException;
import java.util.ArrayList;

public class Deck {
    private ArrayList<Carts> deck;
    private Hero hero;

    public Deck(){

    }

    public Deck(ArrayList<Carts> deck, Hero hero) {
        this.deck = deck;
        this.hero = hero;
    }

    public ArrayList<Carts> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Carts> deck) {
        this.deck = deck;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public void addCard(Carts cart){
        ArrayList<Carts> ar=deck;
        ar.add(cart);
        this.setDeck(ar);
    }
    public void removeCard(Carts cart){
        ArrayList<Carts> ar=deck;
        ar.remove(cart);
        this.setDeck(ar);
    }


    public static ArrayList<Carts> DefultAvailableCardsManager() {
        ArrayList ar = new ArrayList();
        ar.add(Carts.fierywaraxe);
        ar.add(Carts.evasivewyrm);
        ar.add(Carts.blessingoftheancients);
        ar.add(Carts.tastyflyfish);
        ar.add(Carts.lightforgedblessing);
        ar.add(Carts.depthcharge);
        ar.add(Carts.cobaltspellkin);
        ar.add(Carts.koboldstickyfinger);
        ar.add(Carts.tombwarden);
        return ar;
    }
}
