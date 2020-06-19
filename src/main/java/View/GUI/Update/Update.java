package View.GUI.Update;

import Enums.Carts;
import View.GUI.Panels.CollectionPanel;
import View.GUI.Panels.MyFrame;
import View.GUI.Panels.StatusPanel;
import Main.Deck;
import Main.Gamestate;
import Main.JsonBuilders;
import Main.JsonReaders;

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
                Gamestate.getPlayer().getSelectedDeck().setMostUsedCard(Deck.mostUsedCard(Gamestate.getPlayer().getSelectedDeck()));
                Gamestate.getPlayer().getSelectedDeck().setHero(JsonReaders.HeroJsonReader(Gamestate.getPlayer(), "mage"));
                Gamestate.getPlayer().getAllDecks().replace(Gamestate.getPlayer().getSelectedDeck().getName(), Gamestate.getPlayer().getSelectedDeck());
                break;
            case "rogue":
                ArrayList<Carts> ar1 = Gamestate.getPlayer().getPlayerCarts();
                ar1.add(Carts.friendlysmith);
                Gamestate.getPlayer().setPlayerCarts(ar1);
                ar = Gamestate.getPlayer().getSelectedDeck().getDeck();
                ar.add(Carts.friendlysmith);
                Gamestate.getPlayer().getAllDecks().get("Default Deck").getUsedTimes().put("friendlysmith", 0);
                Gamestate.getPlayer().getSelectedDeck().getUsedTimes().put("friendlysmith", 0);
                Gamestate.getPlayer().getSelectedDeck().setDeck(ar);
                Gamestate.getPlayer().getSelectedDeck().setMostUsedCard(Deck.mostUsedCard(Gamestate.getPlayer().getSelectedDeck()));
                Gamestate.getPlayer().getAllDecks().replace(Gamestate.getPlayer().getSelectedDeck().getName(), Gamestate.getPlayer().getSelectedDeck());
                Gamestate.getPlayer().getSelectedDeck().setHero(JsonReaders.HeroJsonReader(Gamestate.getPlayer(), "rogue"));
                break;
            case "warlock":
                ArrayList<Carts> ar2 = Gamestate.getPlayer().getPlayerCarts();
                ar2.add(Carts.dreadscale);
                Gamestate.getPlayer().setPlayerCarts(ar2);
                ar = Gamestate.getPlayer().getSelectedDeck().getDeck();
                ar.add(Carts.dreadscale);
                Gamestate.getPlayer().getAllDecks().get("Default Deck").getUsedTimes().put("dreadscale", 0);
                Gamestate.getPlayer().getSelectedDeck().getUsedTimes().put("dreadscale", 0);
                Gamestate.getPlayer().getSelectedDeck().setDeck(ar);
                Gamestate.getPlayer().getSelectedDeck().setMostUsedCard(Deck.mostUsedCard(Gamestate.getPlayer().getSelectedDeck()));
                Gamestate.getPlayer().getAllDecks().replace(Gamestate.getPlayer().getSelectedDeck().getName(), Gamestate.getPlayer().getSelectedDeck());
                Gamestate.getPlayer().getSelectedDeck().setHero(JsonReaders.HeroJsonReader(Gamestate.getPlayer(), "warlock"));
                break;
            case "priest":
                ArrayList<Carts> ar3 = Gamestate.getPlayer().getPlayerCarts();
                ar3.add(Carts.highpriestamet);
                Gamestate.getPlayer().setPlayerCarts(ar3);
                ar = Gamestate.getPlayer().getSelectedDeck().getDeck();
                ar.add(Carts.highpriestamet);
                Gamestate.getPlayer().getAllDecks().get("Default Deck").getUsedTimes().put("highpriestamet", 0);
                Gamestate.getPlayer().getSelectedDeck().getUsedTimes().put("highpriestamet", 0);
                Gamestate.getPlayer().getSelectedDeck().setDeck(ar);
                Gamestate.getPlayer().getSelectedDeck().setMostUsedCard(Deck.mostUsedCard(Gamestate.getPlayer().getSelectedDeck()));
                Gamestate.getPlayer().getAllDecks().replace(Gamestate.getPlayer().getSelectedDeck().getName(), Gamestate.getPlayer().getSelectedDeck());
                Gamestate.getPlayer().getSelectedDeck().setHero(JsonReaders.HeroJsonReader(Gamestate.getPlayer(), "priest"));
                break;
            case "hunter":
                ArrayList<Carts> ar4 = Gamestate.getPlayer().getPlayerCarts();
                ar4.add(Carts.swampkingdred);
                Gamestate.getPlayer().setPlayerCarts(ar4);
                ar = Gamestate.getPlayer().getSelectedDeck().getDeck();
                ar.add(Carts.swampkingdred);
                Gamestate.getPlayer().getAllDecks().get("Default Deck").getUsedTimes().put("swampkingdred", 0);
                Gamestate.getPlayer().getSelectedDeck().getUsedTimes().put("swampkingdred", 0);
                Gamestate.getPlayer().getSelectedDeck().setDeck(ar);
                Gamestate.getPlayer().getSelectedDeck().setMostUsedCard(Deck.mostUsedCard(Gamestate.getPlayer().getSelectedDeck()));
                Gamestate.getPlayer().getAllDecks().replace(Gamestate.getPlayer().getSelectedDeck().getName(), Gamestate.getPlayer().getSelectedDeck());
                Gamestate.getPlayer().getSelectedDeck().setHero(JsonReaders.HeroJsonReader(Gamestate.getPlayer(), "hunter"));
                break;
        }
        Gamestate.getPlayer().getSelectedDeck().setMostUsedCard(Deck.mostUsedCard(Gamestate.getPlayer().getSelectedDeck()));
        Gamestate.getPlayer().getAllDecks().get(Gamestate.getPlayer().getSelectedDeck().getName()).setMostUsedCard(Deck.mostUsedCard(Gamestate.getPlayer().getSelectedDeck()));
    }
}
