package Server.Model;

import Server.Controller.MainLogic.DeckLogic;
import Server.Model.Cards.Card;
import Server.Model.Enums.Carts;
import Server.Model.Heros.Hero;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Entity
public class Deck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection(targetClass = Carts.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable
    @Column
    private List<Carts> deck;
    @ManyToOne
    private Hero hero;
    @Transient
    @Enumerated(EnumType.STRING)
    private Carts mostUsedCard;
    @Column
    private int totalPlays;
    @Column
    private int totalWins;
    @LazyCollection(LazyCollectionOption.FALSE)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ElementCollection
    @JoinTable
    @MapKeyColumn
    private Map<String, Integer> usedTimes = new HashMap<>();
    @Transient
    private int cup;

    public Deck() {

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Carts> getDeck() {
        return deck;
    }

    public void setDeck(List<Carts> deck) {
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


    public static Deck cloneDeck(Deck deck) {
        Deck deck1 = new Deck();
        deck1.setDeck(deck.getDeck());
        deck1.setHero(deck.getHero());
        deck1.setUsedTimes(deck.getUsedTimes());
//        deck1.setMostUsedCard(deck.getMostUsedCard());
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

    public Carts getMostUsedCard() {
//        mostUsedCard = mostUsedCard();
        return mostUsedCard;
    }

    public Map<String, Integer> getUsedTimes() {
        return usedTimes;
    }

    public void setUsedTimes(Map<String, Integer> usedTimes) {
        this.usedTimes = usedTimes;
    }

    public int getCup() {
        return totalWins - (totalPlays - totalWins);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deck=" + deck +
                ", hero=" + hero +
                ", mostUsedCard=" + mostUsedCard +
                ", totalPlays=" + totalPlays +
                ", totalWins=" + totalWins +
                ", usedTimes=" + usedTimes +
                '}';
    }
}
