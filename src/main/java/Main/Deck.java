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
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Deck {
    private ArrayList<Carts> deck;
    private Hero hero;
    private Carts mostUsedCard;
    private int totalPlays;
    private int totalWins;
    private String name;
    private HashMap<String, Integer> usedTimes;

    public Deck() {
        usedTimes = new HashMap<>();
    }

    public Deck(int totalPlays, int totalWins, String name) {
        this();
        ArrayList<Carts> ar = DefultAvailableCardsManager();
        setDeck(ar);
        usedTimeUpdater();
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

    public void addCard(Carts cart) {
        if (deck == null) {
            deck = new ArrayList<>();
        }
        ArrayList<Carts> ar = deck;
        ar.add(cart);
        this.setDeck(ar);
    }

    public void removeCard(Carts cart) {
        ArrayList<Carts> ar = deck;
        ar.remove(cart);
        this.setDeck(ar);
    }


    public double avarageMana() {
        int x = 0;


        return x;
    }

    public double winRate() {
        if (totalPlays == 0) {
            return 0;
        }
        double x = totalWins / totalPlays;
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

    public static Deck DefaultDeck() {
        Deck deck = new Deck(0, 0, "Default Deck");
        System.out.println(1);
        System.out.println(deck);
        deck.setMostUsedCard(mostUsedCard(deck));


        System.out.println(2);
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

    public static Deck changeSelectedDeck(Deck deck) {
        Deck deck1 = new Deck();
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

    public void usedTimeUpdater(){
        for (Carts carts : deck) {
            usedTimes.put(carts.toString() , 0);
        }
    }

    public static Carts mostUsedCard(Deck deck) {
        System.out.println(deck.toString());
        int i = 0;
        String name;
        int j = 0;
        ArrayList<Carts> ar = new ArrayList<>();
        for (Map.Entry<String, Integer> Entry : deck.usedTimes.entrySet()) {
            System.out.println(Entry.getKey() + " : " + Entry.getValue());
            i = Entry.getValue();
            if (j < i) {
                j = i;
            }
        }

        for (Map.Entry<String, Integer> Entry : deck.usedTimes.entrySet()) {
            if (Entry.getValue() == j) {
                ar.add(Carts.valueOf(Entry.getKey().toLowerCase()));
            }
        }
        System.out.println("ar1 :  " + ar.size());
        if (ar.size() == 1) {
            return ar.get(0);
        } else {
            ArrayList<Cards> ar2 = Deck.UpdateDeck(ar);
            System.out.println("ar2 :  " + ar2.size());
            ArrayList<Cards> ar3 = new ArrayList<>();
            for (int k = 0; k < 1; k++) {
                for (Cards cards : ar2) {
                    System.out.println(cards.getName() + " : " + cards.getRarity());
                    if (cards.getRarity().equalsIgnoreCase("Legendary")) {
                        ar3.add(cards);
                    }
                }
                System.out.println(ar3.toString());
                if (ar3.size() > 0) {
                    break;
                }
                for (Cards cards : ar2) {
                    if (cards.getRarity().equalsIgnoreCase("Epic")) {
                        ar3.add(cards);
                    }
                }
                if (ar3.size() > 0) {
                    break;
                }
                for (Cards cards : ar2) {
                    if (cards.getRarity().equalsIgnoreCase("Rare")) {
                        ar3.add(cards);
                    }
                }
                if (ar3.size() > 0) {
                    break;
                }
                for (Cards cards : ar2) {
                    if (cards.getRarity().equalsIgnoreCase("Common")) {
                        ar3.add(cards);
                    }
                }
                if (ar3.size() > 0) {
                    break;
                }
            }
            if (ar3.size() == 1) {
                return Carts.valueOf(ar3.get(0).getName().toLowerCase());
            } else{
                System.out.println("ar3 :  " + ar3.size());
                ArrayList<Cards> ar4=new ArrayList<>();
                i=0;
                j=0;
                for (Cards cards : ar3) {
                    i=cards.getManaCost();
                    if (j<i){
                        j=i;
                    }
                }
                for (Cards cards : ar3) {
                    if (cards.getManaCost()==j){
                        ar4.add(cards);
                    }
                }
                System.out.println("ar4 :  " + ar4.size());
                if (ar4.size()==1){
                    return Carts.valueOf(ar4.get(0).getName().toLowerCase());
                }else{
                    ar2=new ArrayList<>();
                    for (int k = 0; k < 1; k++) {
                        for (Cards cards : ar4) {
                            if (cards.getType().equalsIgnoreCase("minion")){
                                ar2.add(cards);
                            }
                        }
                        if (ar2.size()>0){
                            break;
                        }
                        for (Cards cards : ar4) {
                            if (cards.getType().equalsIgnoreCase("spell")){
                                ar2.add(cards);
                            }
                        }
                        if (ar2.size()>0){
                            break;
                        }
                        for (Cards cards : ar4) {
                            if (cards.getType().equalsIgnoreCase("weapon")){
                                ar2.add(cards);
                            }
                        }
                    }
                    System.out.println("ar2 :  " + ar2.size());
                    Random rand=new Random();
                    i=rand.nextInt();
                    j=i%ar2.size();
                    return Carts.valueOf(ar2.get(j).getName().toLowerCase());
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Deck{" +
                "deck=" + deck +
                ", hero=" + hero +
                ", mostUsedCard=" + mostUsedCard +
                ", totalPlays=" + totalPlays +
                ", totalWins=" + totalWins +
                ", name='" + name + '\'' +
                ", usedTimes=" + usedTimes +
                '}';
    }

    public HashMap<String, Integer> getUsedTimes() {
        return usedTimes;
    }

    public void setUsedTimes(HashMap<String, Integer> usedTimes) {
        this.usedTimes = usedTimes;
    }
}
