package Main;

import AllCards.Cards;
import Enums.*;


import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.*;
import java.util.*;

public class Shop {


    public static void Buy(String st) {
        Gamestate.getPlayer().setMoney(Gamestate.getPlayer().getMoney() - Price(st));
        ArrayList<Carts> ar = Gamestate.getPlayer().getPlayerCarts();
        ar.add(Carts.valueOf(st));
        Gamestate.getPlayer().setPlayerCarts(ar);
        JsonBuilders.PlayerJsonBuilder(Gamestate.getPlayer().getUsername(), Gamestate.getPlayer());
    }

    public static ArrayList<Cards> Buyable() {
        ArrayList<Carts> ar = BuyableCards();
        ArrayList<Cards> ar2 = null;
        ar2 = Deck.UpdateDeck(ar);
        return ar2;
    }

    public static ArrayList<Cards> Sellable() {
        ArrayList<Carts> ar = Gamestate.getPlayer().getPlayerCarts();
        ArrayList<Cards> ar2 = null;
        ar2 = Deck.UpdateDeck(ar);
        return ar2;
    }

    public static boolean CanBeSold(String st) {
        ArrayList<Carts> ar = Deck.UpdateSellCards();
        return !ar.contains(Carts.valueOf(st.toLowerCase()));
    }


    public static void Sell(String string) {
        String st = string.toLowerCase();
        Gamestate.getPlayer().setMoney(Gamestate.getPlayer().getMoney() + (Price(st) / 2));
        ArrayList<Carts> ar2 = Gamestate.getPlayer().getPlayerCarts();
        ar2.remove(Carts.valueOf(st));
        JsonBuilders.PlayerJsonBuilder(Gamestate.getPlayer().getUsername(), Gamestate.getPlayer());
    }

    private static ArrayList<Carts> BuyableCards() {
        ArrayList<Carts> ar = new ArrayList<>();
        for (NeutralCarts cartss : NeutralCarts.values()) {
            int i = 0;
            for (Carts cartsss : Gamestate.getPlayer().getPlayerCarts()) {
                if (cartss.toString().equalsIgnoreCase(cartsss.toString())) {
                    i++;
                }
            }
            if (i < 1) {
                ar.add(Carts.valueOf(cartss.toString()));
            }
        }
        for (SpecialCarts cartss : SpecialCarts.values()) {
            int i = 0;
            for (Carts cartsss : Gamestate.getPlayer().getPlayerCarts()) {
                if (cartss.toString().equalsIgnoreCase(cartsss.toString())) {
                    i++;
                }
            }
            if (i < 1) {
                ar.add(Carts.valueOf(cartss.toString()));
            }
        }
        return ar;
    }

    public static long Price(String name) {
        long n = 0;
        try {
            JSONParser jsonParser = new JSONParser();
            FileReader fileReader = new FileReader(String.format("resources/Jsons/Cards/%s.json", name));
            Object object = jsonParser.parse(fileReader);
            JSONObject jsonObject = (JSONObject) object;
            n = (Long) jsonObject.get("price");

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return n;
    }
}
