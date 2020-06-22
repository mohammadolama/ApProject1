package Main;

import Model.Cards.Card;
import Model.Enums.Carts;
import Model.Enums.MinionCarts;
import Model.Enums.SpellCarts;
import Model.Enums.WeaponCarts;
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
        ArrayList<Card> ar = Deck.UpdateDeck(getDeck());
        double i = 0;
        for (Card card : ar) {
            i = i + card.getManaCost();
        }
        i = i / ar.size();
        BigDecimal bd = new BigDecimal(Double.toString(i));
        bd = bd.setScale(3, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }

    public double winRate() {
        if (totalPlays == 0) {
            return 0;
        }
        return totalWins / totalPlays;
    }


    static ArrayList<Carts> DefultAvailableCardsManager() {
        ArrayList<Carts> ar = new ArrayList<>();
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

    static Deck DefaultDeck() {
        Deck deck = new Deck(0, 0, "Default Deck");
        deck.setMostUsedCard(mostUsedCard(deck));
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

    public static Deck cloneDeck(Deck deck) {
        Deck deck1 = new Deck();
        deck1.setDeck(deck.getDeck());
        deck1.setHero(deck.getHero());
        deck1.setMostUsedCard(deck.getMostUsedCard());
        deck1.setName(deck.getName());
        deck1.setTotalPlays(deck.getTotalPlays());
        deck1.setTotalWins(deck.getTotalWins());
        return deck1;
    }

    public static ArrayList<Card> UpdateDeck(ArrayList<Carts> arrayList) { // biuld card objects from json using enum
        ArrayList<Card> ar = new ArrayList<>();

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
        return ar;
    }

    private void usedTimeUpdater() {
        for (Carts carts : deck) {
            usedTimes.put(carts.toString(), 0);
        }
    }


    public static Carts mostUsedCard(Deck deck) {
        int i = 0;
        int j = 0;
        ArrayList<Carts> ar = new ArrayList<>();
        for (Map.Entry<String, Integer> Entry : deck.usedTimes.entrySet()) {
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
        if (ar.size() == 1) {
            return ar.get(0);
        } else {
            ArrayList<Card> ar2 = Deck.UpdateDeck(ar);
            ArrayList<Card> ar3 = new ArrayList<>();
            Collections.sort(ar2, Comparator.comparing(Card::getRarityI).thenComparing(Card::getManaCost).thenComparing(Card::getTypeI));
            System.out.println(ar2.get(ar2.size() - 1));
            return Carts.valueOf(ar2.get(ar2.size() - 1).getName().toLowerCase());
        }
    }

    public static ArrayList<String> bestDeck(Player player) {
        ArrayList<Deck> ar = new ArrayList<>();
        for (Map.Entry<String, Deck> entry : player.getAllDecks().entrySet()) {
            ar.add(entry.getValue());
        }
        System.out.println(" ");
        Collections.sort(ar, Comparator.comparing(Deck::winRate).thenComparing(Deck::getTotalWins).thenComparing(Deck::getTotalPlays).thenComparing(Deck::avarageMana));

        ArrayList<String> arrayList = new ArrayList<>();
        if (ar.size() <= 10) {
            for (Deck deck : ar) {
                arrayList.add(deck.getName());
            }
            return arrayList;
        } else {
            for (int i = 0; i < 10; i++) {
                arrayList.add(ar.get(i).getName());
            }
            return arrayList;
        }
    }


    static ArrayList<Carts> UpdateSellCards() {
        ArrayList<Carts> ar = new ArrayList<>();
        for (Map.Entry<String, Deck> entry : Gamestate.getPlayer().getAllDecks().entrySet()) {
            Deck deck = entry.getValue();
            for (Carts carts : deck.getDeck()) {
                ar.add(carts);
            }
        }
        return ar;

    }

    public HashMap<String, Integer> getUsedTimes() {
        return usedTimes;
    }

    public void setUsedTimes(HashMap<String, Integer> usedTimes) {
        this.usedTimes = usedTimes;
    }

    public static HashMap<String, Integer> resetUsedTimes(ArrayList<Carts> carts, Deck deck) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (Carts cart : carts) {
            hashMap.put(cart.toString().toLowerCase(), 0);
        }
        return hashMap;
    }
}
