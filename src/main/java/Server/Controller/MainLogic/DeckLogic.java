package Server.Controller.MainLogic;


import Server.Model.Cards.Card;
import Server.Model.Deck;
import Server.Model.Enums.*;
import Server.Model.Player;

import java.util.*;
import java.util.Comparator;
import java.util.Map;

public class DeckLogic {

    public static ArrayList<Carts> DefultAvailableCardsManager() {
        ArrayList<Carts> ar = new ArrayList<>();
        ar.add(Carts.fierywaraxe);
        ar.add(Carts.gearblade);
        ar.add(Carts.blessingoftheancients);
        ar.add(Carts.cookie);
        ar.add(Carts.lightforgedblessing);
        ar.add(Carts.swarmofcats);
        ar.add(Carts.sprint);
        ar.add(Carts.arcanitereaper);
        ar.add(Carts.aghahaghi);
        ar.add(Carts.hossein);
        ar.add(Carts.hosseinhima);
        ar.add(Carts.khashayar);
        ar.add(Carts.lachin);
        ar.add(Carts.mobin);
        return ar;
    }

    public static ArrayList<String> bestDeck(Player player) {
        ArrayList<Deck> ar = new ArrayList<>();
        for (Map.Entry<String, Deck> entry : player.getAllDecks().entrySet()) {
            ar.add(entry.getValue());
        }
        ar.sort(Comparator.comparing(Deck::winRate).thenComparing(Deck::getTotalWins).thenComparing(Deck::getTotalPlays).thenComparing(Deck::avarageMana));

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

    public static ArrayList<Carts> UpdateSellCards(Player player) {
        ArrayList<Carts> ar = new ArrayList<>();
        for (Map.Entry<String, Deck> entry : player.getAllDecks().entrySet()) {
            Deck deck = entry.getValue();
            ar.addAll(deck.getDeck());
        }
        return ar;
    }

    public static ArrayList<Card> UpdateDeck(List<Carts> List) { // biuld card objects from json using enum
        ArrayList<Card> ar = new ArrayList<>();

        for (Carts cartss : List) {
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

}
