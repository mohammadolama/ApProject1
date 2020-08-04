package Client.Model;

import Client.Model.Enums.Carts;

import java.util.ArrayList;

public class DeckModel {
    private String name;
    private ArrayList<Carts> list;
    private String hero;
    private Carts mostUsedcart;
    private int totalPlays;
    private int totalWins;
    private double avarageMana;
    private double winRate;

    public DeckModel(String name, ArrayList<Carts> list, String hero, Carts mostUsedcart,
                     int totalPlays, int totalWins, double avarageMana, double winRate) {
        this.name = name;
        this.list = list;
        this.hero = hero;
        this.mostUsedcart = mostUsedcart;
        this.totalPlays = totalPlays;
        this.totalWins = totalWins;
        this.avarageMana = avarageMana;
        this.winRate = winRate;
    }

    public DeckModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Carts> getList() {
        return list;
    }

    public void setList(ArrayList<Carts> list) {
        this.list = list;
    }

    public String getHero() {
        return hero;
    }

    public void setHero(String hero) {
        this.hero = hero;
    }

    public Carts getMostUsedcart() {
        return mostUsedcart;
    }

    public void setMostUsedcart(Carts mostUsedcart) {
        this.mostUsedcart = mostUsedcart;
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

    public double getAvarageMana() {
        return avarageMana;
    }

    public void setAvarageMana(double avarageMana) {
        this.avarageMana = avarageMana;
    }

    public double getWinRate() {
        return winRate;
    }

    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }
}
