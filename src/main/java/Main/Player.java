package Main;


import Model.Enums.*;

import java.io.*;
import java.util.*;

import static Main.Deck.DefaultDeck;
import static Main.Deck.DefultAvailableCardsManager;

public class Player implements Serializable {
    private String username;
    private String password;
    private String id;
    private int level;
    private int exp;
    private long money = 1000;
    private Boolean NewToGame = true;
    private ArrayList<Carts> PlayerCarts;
    private ArrayList<Heroes> PlayerHeroes;
    private Deck selectedDeck;
    private InfoPassive selectedPassive;
    private HashMap<String, Deck> allDecks;


    public Player() {
    }

    public Player(String username, String password) {
        try {
            this.username = username;
            this.password = password;
            this.level = 0;
            this.exp = 0;
            this.setPlayerCarts(DefultAvailableCardsManager());
            this.setSelectedDeck(DefaultDeck());
            allDecks = new HashMap<String, Deck>();
            allDecks.put(getSelectedDeck().getName(), getSelectedDeck());
            this.setId(System.nanoTime() + "");
            String st = String.format("resources/players/%s-%s.log", username, this.getId());
            PrintWriter vm = new PrintWriter(st);
            Date date = new Date();
            vm.write("Created in : " + date.toString() + "\n" + "Password : " + password + "\n" + "User : " + username + "\n" + "**********************" + "\n");
            vm.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Heroes> getPlayerHeroes() {
        return PlayerHeroes;
    }

    public void setPlayerHeroes(ArrayList<Heroes> playerHeroes) {
        PlayerHeroes = playerHeroes;
    }

    String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Carts> getPlayerCarts() {
        return PlayerCarts;
    }

    public void setPlayerCarts(ArrayList<Carts> playerCarts) {
        PlayerCarts = playerCarts;
    }


    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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

    public Boolean getNewToGame() {
        return NewToGame;
    }

    public void setNewToGame(Boolean newToGame) {
        NewToGame = newToGame;
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

    public HashMap<String, Deck> getAllDecks() {
        return allDecks;
    }

    public void setAllDecks(HashMap<String, Deck> allDecks) {
        this.allDecks = allDecks;
    }

}