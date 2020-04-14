package Main;

import AllCards.Cards;
import AllCards.Spell;
import Enums.Carts;
import Enums.MinionCarts;
import Enums.SpellCarts;
import Enums.WeaponCarts;
import Heros.Hero;

import java.io.IOException;
import java.util.ArrayList;

public class Deck {
    private ArrayList<Carts> deck;
    private Hero hero;
    private Carts mostUsedCard;
    private int totalPlays;
    private int totalWins;
    private String name;


    public Deck(){

    }

    public Deck(int totalPlays, int totalWins, String name) {
        this.totalPlays = totalPlays;
        this.totalWins = totalWins;
        this.name = name;
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
        if (deck==null){
            deck=new ArrayList<>();
        }
        ArrayList<Carts> ar=deck;
        ar.add(cart);
        this.setDeck(ar);
    }
    public void removeCard(Carts cart){
        ArrayList<Carts> ar=deck;
        ar.remove(cart);
        this.setDeck(ar);
    }


    public double avarageMana(){
        int x=0;


        return x;
    }

    public double winRate(){
        if (totalPlays==0){
            return 0;
        }
        double x=totalWins/totalPlays;
        return x;
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

    public static Deck DefaultDeck(){
        Deck deck=new Deck(0,0,"Default Deck" );

        ArrayList<Carts> ar=DefultAvailableCardsManager();
        deck.setDeck(ar);

        return deck;
    }


    public Carts getMostUsedCard() {
        return mostUsedCard;
    }

    public void setMostUsedCard(Carts mostUsedCard) {
        this.mostUsedCard = mostUsedCard;
    }

    public int getTotalPlays() {
        return totalPlays;
    }

    public void setTotalPlays(int totalPlays) {
        this.totalPlays = totalPlays;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(int totalWins) {
        this.totalWins = totalWins;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Deck changeSelectedDeck(Deck deck){
        Deck deck1=new Deck();
        deck1.setDeck(deck.getDeck());
        deck1.setHero(deck.getHero());
        deck1.setMostUsedCard(deck.getMostUsedCard());
        deck1.setName(deck.getName());
        deck1.setTotalPlays(deck.getTotalPlays());
        deck1.setTotalWins(deck.getTotalWins());
        return deck1;
    }

    public static ArrayList<Cards> UpdateDeck(ArrayList<Carts> arrayList) { // biuld card objects from json using enum
        ArrayList<Cards> ar = new ArrayList();
        try {

            for (Carts cartss : arrayList) {
                for (MinionCarts minionCarts : MinionCarts.values()) {
                    if (cartss.toString().equals(minionCarts.toString())) {
                        ar.add(JsonReaders.MinionsReader(cartss.name()));
                    }
                }
                for (SpellCarts spellCarts : SpellCarts.values()) {
                    if (cartss.toString().equals(spellCarts.toString())) {
                        ar.add(JsonReaders.SpellReader(cartss.name()));
                    }
                }
                for (WeaponCarts weaponCarts : WeaponCarts.values()) {
                    if (cartss.toString().equals(weaponCarts.toString())) {
                        ar.add(JsonReaders.WeaponReader(cartss.name()));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ar;
    }




}
