package Server.Controller.MainLogic;

import Server.Model.Enums.Carts;
import Server.Model.Player;

import java.util.List;

public class HeroLogic {
    public static void updateFirstHero(String hero, Player player) {
        switch (hero) {
            case "mage":
                List<Carts> ar = player.getPlayerCarts();
                ar.add(Carts.polymorph);
                player.setPlayerCarts(ar);
                ar = player.getSelectedDeck().getDeck();
                ar.add(Carts.polymorph);
                player.getAllDecks().get("Default Deck").getUsedTimes().put("polymorph", 0);
                player.getSelectedDeck().getUsedTimes().put("polymorph", 0);
                player.getSelectedDeck().setDeck(ar);
                player.getSelectedDeck().setHero(DataBaseManagment.HeroJsonReader(player, "mage"));
                player.getAllDecks().replace(player.getSelectedDeck().getName(), player.getSelectedDeck());
                break;
            case "rogue":
                List<Carts> ar1 = player.getPlayerCarts();
                ar1.add(Carts.aylar);
                player.setPlayerCarts(ar1);
                ar = player.getSelectedDeck().getDeck();
                ar.add(Carts.aylar);
                player.getAllDecks().get("Default Deck").getUsedTimes().put("aylar", 0);
                player.getSelectedDeck().getUsedTimes().put("aylar", 0);
                player.getSelectedDeck().setDeck(ar);
                player.getAllDecks().replace(player.getSelectedDeck().getName(), player.getSelectedDeck());
                player.getSelectedDeck().setHero(DataBaseManagment.HeroJsonReader(player, "rogue"));
                break;
            case "warlock":
                List<Carts> ar2 = player.getPlayerCarts();
                ar2.add(Carts.benyamin);
                player.setPlayerCarts(ar2);
                ar = player.getSelectedDeck().getDeck();
                ar.add(Carts.benyamin);
                player.getAllDecks().get("Default Deck").getUsedTimes().put("benyamin", 0);
                player.getSelectedDeck().getUsedTimes().put("benyamin", 0);
                player.getSelectedDeck().setDeck(ar);
                player.getAllDecks().replace(player.getSelectedDeck().getName(), player.getSelectedDeck());
                player.getSelectedDeck().setHero(DataBaseManagment.HeroJsonReader(player, "warlock"));
                break;
            case "priest":
                List<Carts> ar3 = player.getPlayerCarts();
                ar3.add(Carts.shahryar);
                player.setPlayerCarts(ar3);
                ar = player.getSelectedDeck().getDeck();
                ar.add(Carts.shahryar);
                player.getAllDecks().get("Default Deck").getUsedTimes().put("shahryar", 0);
                player.getSelectedDeck().getUsedTimes().put("shahryar", 0);
                player.getSelectedDeck().setDeck(ar);
                player.getAllDecks().replace(player.getSelectedDeck().getName(), player.getSelectedDeck());
                player.getSelectedDeck().setHero(DataBaseManagment.HeroJsonReader(player, "priest"));
                break;
            case "hunter":
                List<Carts> ar4 = player.getPlayerCarts();
                ar4.add(Carts.faeze);
                player.setPlayerCarts(ar4);
                ar = player.getSelectedDeck().getDeck();
                ar.add(Carts.faeze);
                player.getAllDecks().get("Default Deck").getUsedTimes().put("faeze", 0);
                player.getSelectedDeck().getUsedTimes().put("faeze", 0);
                player.getSelectedDeck().setDeck(ar);
                player.getAllDecks().replace(player.getSelectedDeck().getName(), player.getSelectedDeck());
                player.getSelectedDeck().setHero(DataBaseManagment.HeroJsonReader(player, "hunter"));
                break;
        }
    }
}
