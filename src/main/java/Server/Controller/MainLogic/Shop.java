package Server.Controller.MainLogic;

import Server.Model.Cards.Card;
import Server.Model.Enums.Carts;
import Server.Model.Enums.NeutralCarts;
import Server.Model.Enums.SpecialCarts;
import Server.Model.Player;

import java.util.ArrayList;
import java.util.List;

public class Shop {

    public static boolean Buy(String st, Player player) {
        if (player.getMoney() < Price(st)) {
            return false;
        }
        player.setMoney(player.getMoney() - Price(st));
        List<Carts> ar = player.getPlayerCarts();
        ar.add(Carts.valueOf(st));
        player.setPlayerCarts(ar);
        DataBaseManagment.savePlayer(player);
        return true;
    }

    public static ArrayList<Card> Buyable(Player player) {
        ArrayList<Carts> ar = BuyableCards(player);
        ArrayList<Card> ar2;
        ar2 = DeckLogic.UpdateDeck(ar);
        return ar2;
    }

    public static ArrayList<Card> Sellable(Player player) {
        List<Carts> ar = player.getPlayerCarts();
        ArrayList<Card> ar2;
        ar2 = DeckLogic.UpdateDeck(ar);
        return ar2;
    }

    public static boolean CanBeSold(String st, Player player) {
        ArrayList<Carts> ar = DeckLogic.UpdateSellCards(player);
        return !ar.contains(Carts.valueOf(st.toLowerCase()));
    }


    public static void Sell(String string, Player player) {
        String st = string.toLowerCase();
        player.setMoney(player.getMoney() + (Price(st) / 2));
        List<Carts> ar2 = player.getPlayerCarts();
        ar2.remove(Carts.valueOf(st));
        DataBaseManagment.savePlayer(player);
    }

    private static ArrayList<Carts> BuyableCards(Player player) {
        ArrayList<Carts> ar = new ArrayList<>();
        for (NeutralCarts cartss : NeutralCarts.values()) {
            int i = 0;
            for (Carts cartsss : player.getPlayerCarts()) {
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
            for (Carts cartsss : player.getPlayerCarts()) {
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
        Card card = DeckLogic.getCardOf(name);
        return (long) card.getPrice();
    }
}
