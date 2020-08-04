package Server.Model;


import Server.Controller.MainLogic.DeckLogic;
import Server.Model.Enums.Carts;
import Server.Model.Enums.Heroes;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Server.Model.Deck.DefaultDeck;


@Entity
public class Player implements Serializable {
    @Id
    private String username;
    @Column
    private String password;
    @Column
    private String playerID;
    @Column
    private int level;
    @Column
    private int exp;
    @Column
    private long money = 1000;
    @Column
    private Boolean NewToGame = true;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection(targetClass = Carts.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable
    @Column
    private List<Carts> PlayerCarts;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection(targetClass = Heroes.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable
    @Column
    private List<Heroes> PlayerHeroes;

    @OneToOne
    private Deck selectedDeck;

    @Transient
    private InfoPassive selectedPassive;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable
    private Map<String, Deck> allDecks;


    public Player() {
    }

    public Player(String username, String password) {
        try {
            this.username = username;
            this.password = password;
            this.level = 0;
            this.exp = 0;
            this.setPlayerCarts(DeckLogic.DefultAvailableCardsManager());
            this.setSelectedDeck(DefaultDeck());
            allDecks = new HashMap<>();
            allDecks.put(getSelectedDeck().getName(), getSelectedDeck());
            this.playerID = System.nanoTime() + "";
            String st = String.format("resources/players/%s-%s.log", username, this.getPlayerID());
            PrintWriter vm = new PrintWriter(st);
            Date date = new Date();
            vm.write("Created in : " + date.toString() + "\n" + "Password : " + password + "\n" + "User : " + username + "\n" + "**********************" + "\n");
            vm.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public Boolean getNewToGame() {
        return NewToGame;
    }

    public void setNewToGame(Boolean newToGame) {
        NewToGame = newToGame;
    }

    public List<Carts> getPlayerCarts() {
        return PlayerCarts;
    }

    public void setPlayerCarts(List<Carts> playerCarts) {
        PlayerCarts = playerCarts;
    }

    public List<Heroes> getPlayerHeroes() {
        return PlayerHeroes;
    }

    public void setPlayerHeroes(List<Heroes> playerHeroes) {
        PlayerHeroes = playerHeroes;
    }

    public Deck getSelectedDeck() {
        return selectedDeck;
    }

    public void setSelectedDeck(Deck selectedDeck) {
        this.selectedDeck = selectedDeck;
    }

    public InfoPassive getSelectedPassive() {
        return selectedPassive;
    }

    public void setSelectedPassive(InfoPassive selectedPassive) {
        this.selectedPassive = selectedPassive;
    }

    public Map<String, Deck> getAllDecks() {
        return allDecks;
    }

    public void setAllDecks(Map<String, Deck> allDecks) {
        this.allDecks = allDecks;
    }

    @PostLoad
    void postLoad() {
        this.allDecks = new HashMap<>(this.allDecks);

        for (Map.Entry<String, Deck> entry : allDecks.entrySet()) {
            System.out.println(entry.getKey() + "  :  " + entry.getValue());
        }
    }
}