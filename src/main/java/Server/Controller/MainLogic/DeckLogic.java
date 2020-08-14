package Server.Controller.MainLogic;


import Server.Model.Cards.Card;
import Server.Model.Deck;
import Server.Model.DeckModel;
import Server.Model.Enums.*;
import Server.Model.Heros.Hero;
import Server.Model.Player;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

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
        ar.sort(Comparator.comparing(Deck::getCup).thenComparing(DeckLogic::winRate).thenComparing(Deck::getTotalWins).thenComparing(Deck::getTotalPlays).thenComparing(DeckLogic::avarageMana));

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
                    ar.add(DataBaseManagment.MinionsReader(cartss.name()));
                }
            }
            for (SpellCarts spellCarts : SpellCarts.values()) {
                if (cartss.toString().equals(spellCarts.toString())) {
                    ar.add(DataBaseManagment.SpellReader(cartss.name()));
                }
            }
            for (WeaponCarts weaponCarts : WeaponCarts.values()) {
                if (cartss.toString().equals(weaponCarts.toString())) {
                    ar.add(DataBaseManagment.WeaponReader(cartss.name()));
                }
            }
        }
        return ar;
    }

    public static Carts mostUsedCard(Deck deck) {
        int i;
        int j = 0;
        ArrayList<Carts> ar = new ArrayList<>();
        for (Map.Entry<String, Integer> Entry : deck.getUsedTimes().entrySet()) {
            i = Entry.getValue();
            if (j < i) {
                j = i;
            }
        }
        for (Map.Entry<String, Integer> Entry : deck.getUsedTimes().entrySet()) {
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


    public static double avarageMana(Deck deck) {
        ArrayList<Card> ar = DeckLogic.UpdateDeck(deck.getDeck());
        double i = 0;
        for (Card card : ar) {
            i = i + card.getManaCost();
        }
        i = i / ar.size();
        BigDecimal bd = new BigDecimal(Double.toString(i));
        bd = bd.setScale(3, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }


    public static double winRate(Deck deck) {
        if (deck.getTotalPlays() == 0) {
            return 0;
        }
        double i = deck.getTotalWins();
        double j = deck.getTotalPlays();
        return i / j;
    }


    public static ArrayList<Card> allCards() {
        ArrayList<Carts> ar = new ArrayList<>();
        Collections.addAll(ar, Carts.values());
        return UpdateDeck(ar);
    }

    public static ArrayList<Card> purchasedCards(Player player) {
        ArrayList<Carts> ar = new ArrayList<>(player.getPlayerCarts());
        return UpdateDeck(ar);
    }

    public static ArrayList<Card> lockedCards(Player player) {
        ArrayList<Carts> ar = new ArrayList<>();
        outer:
        for (Carts carts : Carts.values()) {
            for (Carts playerCart : player.getPlayerCarts()) {
                if (carts.toString().equalsIgnoreCase(playerCart.toString())) {
                    continue outer;
                }
            }
            ar.add(carts);
        }
        return UpdateDeck(ar);
    }

    public static ArrayList<Card> neutralCardsFilter() {
        ArrayList<Carts> ar = new ArrayList<>();
        for (NeutralCarts value : NeutralCarts.values()) {
            ar.add(Carts.valueOf(value.toString()));
        }
        return UpdateDeck(ar);
    }

    public static ArrayList<Card> specialCardsFilter() {
        ArrayList<Carts> ar = new ArrayList<>();
        for (SpecialCarts value : SpecialCarts.values()) {
            ar.add(Carts.valueOf(value.toString()));
        }
        return UpdateDeck(ar);
    }

    public static Card getCardOf(String name) {
        for (int i = 0; i < 1; i++) {
            for (MinionCarts value : MinionCarts.values()) {
                if (value.toString().equalsIgnoreCase(name)) {
                    return DataBaseManagment.MinionsReader(name.toLowerCase());
                }
            }
            for (SpellCarts value : SpellCarts.values()) {
                if (value.toString().equalsIgnoreCase(name)) {
                    return DataBaseManagment.SpellReader(name.toLowerCase());
                }
            }
            for (WeaponCarts value : WeaponCarts.values()) {
                if (value.toString().equalsIgnoreCase(name)) {
                    return DataBaseManagment.WeaponReader(name.toLowerCase());
                }
            }
        }
        return null;
    }


    public synchronized String createDeck(String name, ArrayList<Carts> selectedCards, String heroName, Player player) {
        for (Map.Entry<String, Deck> entry : player.getAllDecks().entrySet()) {
            String st = entry.getKey();
            if (name.equalsIgnoreCase(st)) {
                return "Name had been taken before !";
            }
        }
        if (selectedCards.size() > 30 || selectedCards.size() < 15) {
            return "Number of cards in your deck must be in range [15,30].";
        }
        Deck deck = new Deck(0, 0, name);
        deck.setDeck(selectedCards);
        deck.setHero(DataBaseManagment.HeroJsonReader(player, heroName.toLowerCase()));
        deck.setUsedTimes(Deck.resetUsedTimes(selectedCards, deck));
        player.getAllDecks().put(deck.getName(), deck);
        Admin.getInstance().Log(String.format("Create : deck %s is created.", deck.getName()), player);
        DataBaseManagment.savePlayer(player);
        return "ok";
    }

    public synchronized String changeDeck(DeckModel deck, ArrayList<Carts> selectedCards, String heroName, String previous, String current, Player player) {
        if (!current.equals(previous)) {
            for (Map.Entry<String, Deck> entry : player.getAllDecks().entrySet()) {
                String st = entry.getKey();
                if (current.equalsIgnoreCase(st)) {
                    return "Name had been taken before !";
                }
            }
        }
        if (selectedCards.size() > 30 || selectedCards.size() < 15) {
            return "Number of cards in your deck must be in range [15,30].";
        }
        Deck selectedDeck = player.getAllDecks().get(previous);
        selectedDeck.setDeck(selectedCards);
        selectedDeck.setName(current);
        Hero hero = DataBaseManagment.HeroJsonReader(player, heroName.toLowerCase());
        selectedDeck.setHero(hero);
        selectedDeck.setUsedTimes(Deck.resetUsedTimes(selectedCards, selectedDeck));
        player.getAllDecks().remove(previous);
        player.getAllDecks().put(current, selectedDeck);
        player.setSelectedDeck(null);
        Admin.getInstance().Log(String.format("Change : deck %s has been changed.", selectedDeck.getName()), player);
        DataBaseManagment.savePlayer(player);
        return "ok";
    }
}
