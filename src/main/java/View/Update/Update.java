package View.Update;

import Model.Enums.Carts;
import View.Panels.CollectionPanel;
import View.Panels.MyFrame;
import View.Panels.StatusPanel;
import MainLogic.Gamestate;
import MainLogic.JsonBuilders;
import MainLogic.JsonReaders;

import java.util.ArrayList;

public class Update {

    public static void refresh() {
        render();
        CollectionPanel.getInstance().refresh();
        StatusPanel.getInstance().refresh();
    }

    public static  void render(){
        MyFrame.getInstance().revalidate();
        MyFrame.getInstance().repaint();

    }
    public static void saveAndUpdate() {
        JsonBuilders.PlayerJsonBuilder(Gamestate.getPlayer().getUsername(), Gamestate.getPlayer());
    }


    public static void updateFirstHero(String hero) {

        switch (hero) {
            case "mage":
                ArrayList<Carts> ar = Gamestate.getPlayer().getPlayerCarts();
                ar.add(Carts.polymorph);
                Gamestate.getPlayer().setPlayerCarts(ar);
                ar = Gamestate.getPlayer().getSelectedDeck().getDeck();
                ar.add(Carts.polymorph);
                Gamestate.getPlayer().getAllDecks().get("Default Deck").getUsedTimes().put("polymorph", 0);
                Gamestate.getPlayer().getSelectedDeck().getUsedTimes().put("polymorph", 0);
                Gamestate.getPlayer().getSelectedDeck().setDeck(ar);
                Gamestate.getPlayer().getSelectedDeck().setMostUsedCard(Gamestate.getPlayer().getSelectedDeck().mostUsedCard());
                Gamestate.getPlayer().getSelectedDeck().setHero(JsonReaders.HeroJsonReader(Gamestate.getPlayer(), "mage"));
                Gamestate.getPlayer().getAllDecks().replace(Gamestate.getPlayer().getSelectedDeck().getName(), Gamestate.getPlayer().getSelectedDeck());
                break;
            case "rogue":
                ArrayList<Carts> ar1 = Gamestate.getPlayer().getPlayerCarts();
                ar1.add(Carts.aylar);
                Gamestate.getPlayer().setPlayerCarts(ar1);
                ar = Gamestate.getPlayer().getSelectedDeck().getDeck();
                ar.add(Carts.aylar);
                Gamestate.getPlayer().getAllDecks().get("Default Deck").getUsedTimes().put("aylar", 0);
                Gamestate.getPlayer().getSelectedDeck().getUsedTimes().put("aylar", 0);
                Gamestate.getPlayer().getSelectedDeck().setDeck(ar);
                Gamestate.getPlayer().getSelectedDeck().setMostUsedCard(Gamestate.getPlayer().getSelectedDeck().mostUsedCard());
                Gamestate.getPlayer().getAllDecks().replace(Gamestate.getPlayer().getSelectedDeck().getName(), Gamestate.getPlayer().getSelectedDeck());
                Gamestate.getPlayer().getSelectedDeck().setHero(JsonReaders.HeroJsonReader(Gamestate.getPlayer(), "rogue"));
                break;
            case "warlock":
                ArrayList<Carts> ar2 = Gamestate.getPlayer().getPlayerCarts();
                ar2.add(Carts.benyamin);
                Gamestate.getPlayer().setPlayerCarts(ar2);
                ar = Gamestate.getPlayer().getSelectedDeck().getDeck();
                ar.add(Carts.benyamin);
                Gamestate.getPlayer().getAllDecks().get("Default Deck").getUsedTimes().put("benyamin", 0);
                Gamestate.getPlayer().getSelectedDeck().getUsedTimes().put("benyamin", 0);
                Gamestate.getPlayer().getSelectedDeck().setDeck(ar);
                Gamestate.getPlayer().getSelectedDeck().setMostUsedCard(Gamestate.getPlayer().getSelectedDeck().mostUsedCard());
                Gamestate.getPlayer().getAllDecks().replace(Gamestate.getPlayer().getSelectedDeck().getName(), Gamestate.getPlayer().getSelectedDeck());
                Gamestate.getPlayer().getSelectedDeck().setHero(JsonReaders.HeroJsonReader(Gamestate.getPlayer(), "warlock"));
                break;
            case "priest":
                ArrayList<Carts> ar3 = Gamestate.getPlayer().getPlayerCarts();
                ar3.add(Carts.shahryar);
                Gamestate.getPlayer().setPlayerCarts(ar3);
                ar = Gamestate.getPlayer().getSelectedDeck().getDeck();
                ar.add(Carts.shahryar);
                Gamestate.getPlayer().getAllDecks().get("Default Deck").getUsedTimes().put("shahryar", 0);
                Gamestate.getPlayer().getSelectedDeck().getUsedTimes().put("shahryar", 0);
                Gamestate.getPlayer().getSelectedDeck().setDeck(ar);
                Gamestate.getPlayer().getSelectedDeck().setMostUsedCard(Gamestate.getPlayer().getSelectedDeck().mostUsedCard());
                Gamestate.getPlayer().getAllDecks().replace(Gamestate.getPlayer().getSelectedDeck().getName(), Gamestate.getPlayer().getSelectedDeck());
                Gamestate.getPlayer().getSelectedDeck().setHero(JsonReaders.HeroJsonReader(Gamestate.getPlayer(), "priest"));
                break;
            case "hunter":
                ArrayList<Carts> ar4 = Gamestate.getPlayer().getPlayerCarts();
                ar4.add(Carts.faeze);
                Gamestate.getPlayer().setPlayerCarts(ar4);
                ar = Gamestate.getPlayer().getSelectedDeck().getDeck();
                ar.add(Carts.faeze);
                Gamestate.getPlayer().getAllDecks().get("Default Deck").getUsedTimes().put("faeze", 0);
                Gamestate.getPlayer().getSelectedDeck().getUsedTimes().put("faeze", 0);
                Gamestate.getPlayer().getSelectedDeck().setDeck(ar);
                Gamestate.getPlayer().getSelectedDeck().setMostUsedCard(Gamestate.getPlayer().getSelectedDeck().mostUsedCard());
                Gamestate.getPlayer().getAllDecks().replace(Gamestate.getPlayer().getSelectedDeck().getName(), Gamestate.getPlayer().getSelectedDeck());
                Gamestate.getPlayer().getSelectedDeck().setHero(JsonReaders.HeroJsonReader(Gamestate.getPlayer(), "hunter"));
                break;
        }
        Gamestate.getPlayer().getSelectedDeck().setMostUsedCard(Gamestate.getPlayer().getSelectedDeck().mostUsedCard());
        Gamestate.getPlayer().getAllDecks().get(Gamestate.getPlayer().getSelectedDeck().getName()).setMostUsedCard(Gamestate.getPlayer().getAllDecks().get(Gamestate.getPlayer().getSelectedDeck().getName()).mostUsedCard());
    }
}
