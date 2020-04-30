package AllCards;

import Enums.*;
import Main.Deck;
import Main.Gamestate;
import Main.JsonReaders;

import java.util.ArrayList;
import java.util.Arrays;

public class Cards {
    private String name;
    private String description;
    private int ManaCost;
    private Type type;
    private String heroClass;
    private Rarity rarity;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public int getRarityI() {
        return getRarity().getI();
    }

    public int getTypeI() {
        return getType().getI();
    }

    public static ArrayList<Cards> allCards() {
        ArrayList<Carts> ar = new ArrayList<>();
        for (Carts carts : Carts.values()) {
            ar.add(carts);
        }

        return Deck.UpdateDeck(ar);
    }

    public static ArrayList<Cards> purchasedCards() {
        ArrayList<Carts> ar = new ArrayList<>();
        for (Carts cart : Gamestate.getPlayer().getPlayerCarts()) {
            ar.add(cart);
        }
        return Deck.UpdateDeck(ar);
    }

    public static ArrayList<Cards> lockedCards() {
        ArrayList<Carts> ar = new ArrayList<>();
        outer:
        for (Carts carts : Carts.values()) {
            for (Carts playerCart : Gamestate.getPlayer().getPlayerCarts()) {
                if (carts.toString().equalsIgnoreCase(playerCart.toString())) {
                    continue outer;
                }
            }
            ar.add(carts);
        }
        return Deck.UpdateDeck(ar);
    }

    public static ArrayList<Cards> neutralCardsFilter() {
        ArrayList<Carts> ar = new ArrayList<>();
        for (NeutralCarts value : NeutralCarts.values()) {
            ar.add(Carts.valueOf(value.toString()));
        }
        return Deck.UpdateDeck(ar);
    }

    public static ArrayList<Cards> specialCardsFilter() {
        ArrayList<Carts> ar = new ArrayList<>();
        for (SpecialCarts value : SpecialCarts.values()) {
            ar.add(Carts.valueOf(value.toString()));
        }
        return Deck.UpdateDeck(ar);
    }

    public static Cards getCardOf(String name) {
        outer:
        for (int i = 0; i < 1; i++) {
            for (MinionCarts value : MinionCarts.values()) {
                if (value.toString().equalsIgnoreCase(name)) {
                    return JsonReaders.MinionsReader(name.toLowerCase());
                }
            }
            for (SpellCarts value : SpellCarts.values()) {
                if (value.toString().equalsIgnoreCase(name)) {
                    return JsonReaders.SpellReader(name.toLowerCase());
                }
            }
            for (WeaponCarts value : WeaponCarts.values()) {
                if (value.toString().equalsIgnoreCase(name)) {
                    return JsonReaders.WeaponReader(name.toLowerCase());
                }
            }
        }
        return null;
    }
}