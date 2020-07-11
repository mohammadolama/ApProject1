package Model;

import MainLogic.DeckLogic;
import Model.Cards.Card;
import Model.Enums.Carts;
import Model.Heros.Hero;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

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
        ArrayList<Carts> ar = DeckLogic.DefultAvailableCardsManager();
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

    static Deck DefaultDeck() {
        //        deck.setMostUsedCard(mostUsedCard(deck));
        return new Deck(0, 0, "Default Deck");
    }

    public double avarageMana() {
        ArrayList<Card> ar = DeckLogic.UpdateDeck(getDeck());
        double i = 0;
        for (Card card : ar) {
            i = i + card.getManaCost();
        }
        i = i / ar.size();
        BigDecimal bd = new BigDecimal(Double.toString(i));
        bd = bd.setScale(3, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }


    public static Deck cloneDeck(Deck deck) {
        Deck deck1 = new Deck();
        deck1.setDeck(deck.getDeck());
        deck1.setHero(deck.getHero());
        deck1.setUsedTimes(deck.getUsedTimes());
        deck1.setMostUsedCard(deck.getMostUsedCard());
        deck1.setName(deck.getName());
        deck1.setTotalPlays(deck.getTotalPlays());
        deck1.setTotalWins(deck.getTotalWins());
        return deck1;
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

    public static HashMap<String, Integer> resetUsedTimes(ArrayList<Carts> carts, Deck deck) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (Carts cart : carts) {
            if (!hashMap.containsKey(cart.toString())) {
                hashMap.put(cart.toString().toLowerCase(), 0);
            }
        }
        return hashMap;
    }


    private void usedTimeUpdater() {
        for (Carts carts : deck) {
            usedTimes.put(carts.toString(), 0);
        }
    }

    public double winRate() {
        if (totalPlays == 0) {
            return 0;
        }
        double i = totalWins;
        double j = totalPlays;
        return i / j;
    }

    public Carts getMostUsedCard() {
        mostUsedCard = mostUsedCard();
        return mostUsedCard;
    }

    public HashMap<String, Integer> getUsedTimes() {
        return usedTimes;
    }

    public void setUsedTimes(HashMap<String, Integer> usedTimes) {
        this.usedTimes = usedTimes;
    }

    public Carts mostUsedCard() {
        int i;
        int j = 0;
        ArrayList<Carts> ar = new ArrayList<>();
        for (Map.Entry<String, Integer> Entry : this.usedTimes.entrySet()) {
            i = Entry.getValue();
            if (j < i) {
                j = i;
            }
        }
        for (Map.Entry<String, Integer> Entry : this.usedTimes.entrySet()) {
            if (Entry.getValue() == j) {
                ar.add(Carts.valueOf(Entry.getKey().toLowerCase()));
            }
        }
        if (ar.size() == 1) {
            return ar.get(0);
        } else {
            ArrayList<Card> ar2 = DeckLogic.UpdateDeck(ar);
            ar2.sort(Comparator.comparing(Card::getRarityI).thenComparing(Card::getManaCost).thenComparing(Card::getTypeI));
            return Carts.valueOf(ar2.get(ar2.size() - 1).getName().toLowerCase());
        }
    }

}
