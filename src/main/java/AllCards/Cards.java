package AllCards;

import Enums.Carts;
import Enums.NeutralCarts;
import Enums.SpecialCarts;
import Main.Deck;
import Main.Gamestate;

import java.util.ArrayList;
import java.util.Arrays;

public class Cards {
    private String name;
    private String description;
    private int ManaCost;
    private String type;
    private String heroClass;
    private String rarity;
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getManaCost() {
        return ManaCost;
    }

    public void setManaCost(int manaCost) {
        ManaCost = manaCost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }

    public String getRarity() {
        return rarity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public static ArrayList<Cards> allCards(){
        ArrayList<Carts> ar=new ArrayList<>();
        for (Carts carts : Carts.values()) {
            ar.add(carts);
        }

    return Deck.UpdateDeck(ar);
    }

    public static ArrayList<Cards> purchasedCards(){
        ArrayList<Carts> ar=new ArrayList<>();
        for (Carts cart : Gamestate.getPlayer().getPlayerCarts()) {
            ar.add(cart);
        }
        return Deck.UpdateDeck(ar);
    }

    public static ArrayList<Cards> lockedCards(){
        ArrayList<Carts> ar=new ArrayList<>();
        outer :for (Carts carts : Carts.values()) {
            for (Carts playerCart : Gamestate.getPlayer().getPlayerCarts()) {
                if (carts.toString().equalsIgnoreCase(playerCart.toString())){
                    continue outer;
                }
            }
            ar.add(carts);
        }
        return Deck.UpdateDeck(ar);
    }

    public static ArrayList<Cards> neutralCardsFilter(){
        ArrayList<Carts> ar=new ArrayList<>();
        for (NeutralCarts value : NeutralCarts.values()) {
            ar.add(Carts.valueOf(value.toString()));
        }
        return Deck.UpdateDeck(ar);
    }

    public static ArrayList<Cards> specialCardsFilter(){
        ArrayList<Carts> ar=new ArrayList<>();
        for (SpecialCarts value : SpecialCarts.values()) {
            ar.add(Carts.valueOf(value.toString()));
        }
        return Deck.UpdateDeck(ar);
    }
}