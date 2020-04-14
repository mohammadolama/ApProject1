package G_L_Interface;

import Enums.Carts;
import GUI.CollectionPanel;
import GUI.MyFrame;
import GUI.ShopPanel;
import GUI.StatusPanel;
import Heros.Hero;
import Main.Deck;
import Main.Gamestate;
import Main.JsonReaders;
import Main.Player;

import java.io.IOException;
import java.util.ArrayList;

public class Update {

    public static void update() {
//        System.out.println(Gamestate.getPlayer());
//        StatusPanel.setPlayer(Gamestate.getPlayer());
        StatusPanel.pictures();
        StatusPanel.getInstance().revalidate();
        StatusPanel.getInstance().repaint();

    }

    public static void updateFirstHero(String hero) {

        switch (hero) {
            case "mage":
                ArrayList<Carts> ar = Gamestate.getPlayer().getPlayerCarts();
                ar.add(Carts.polymorph);
                Gamestate.getPlayer().setPlayerCarts(ar);
                ar = Gamestate.getPlayer().getSelectedDeck().getDeck();
                ar.add(Carts.polymorph);
                Gamestate.getPlayer().getSelectedDeck().setDeck(ar);
                Gamestate.getPlayer().getAllDecks().replace(Gamestate.getPlayer().getSelectedDeck().getName() , Gamestate.getPlayer().getSelectedDeck());
                try {
                    Gamestate.getPlayer().getSelectedDeck().setHero(JsonReaders.HeroJsonReader(Gamestate.getPlayer(), "mage"));

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case "rogue":
                ArrayList<Carts> ar1 = Gamestate.getPlayer().getPlayerCarts();
                ar1.add(Carts.friendlysmith);
                Gamestate.getPlayer().setPlayerCarts(ar1);
                ar = Gamestate.getPlayer().getSelectedDeck().getDeck();
                ar.add(Carts.friendlysmith);
                Gamestate.getPlayer().getSelectedDeck().setDeck(ar);
                Gamestate.getPlayer().getAllDecks().replace(Gamestate.getPlayer().getSelectedDeck().getName() , Gamestate.getPlayer().getSelectedDeck());

                try {
                    Gamestate.getPlayer().getSelectedDeck().setHero(JsonReaders.HeroJsonReader(Gamestate.getPlayer(), "rogue"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case "warlock":
                ArrayList<Carts> ar2 = Gamestate.getPlayer().getPlayerCarts();
                ar2.add(Carts.dreadscale);
                Gamestate.getPlayer().setPlayerCarts(ar2);
                ar = Gamestate.getPlayer().getSelectedDeck().getDeck();
                ar.add(Carts.dreadscale);
                Gamestate.getPlayer().getSelectedDeck().setDeck(ar);
                Gamestate.getPlayer().getAllDecks().replace(Gamestate.getPlayer().getSelectedDeck().getName() , Gamestate.getPlayer().getSelectedDeck());
                try {
                    Gamestate.getPlayer().getSelectedDeck().setHero(JsonReaders.HeroJsonReader(Gamestate.getPlayer(), "warlock"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case "priest":
                ArrayList<Carts> ar3 = Gamestate.getPlayer().getPlayerCarts();
                ar3.add(Carts.highpriestamet);
                Gamestate.getPlayer().setPlayerCarts(ar3);
                ar = Gamestate.getPlayer().getSelectedDeck().getDeck();
                ar.add(Carts.highpriestamet);
                Gamestate.getPlayer().getSelectedDeck().setDeck(ar);
                Gamestate.getPlayer().getAllDecks().replace(Gamestate.getPlayer().getSelectedDeck().getName() , Gamestate.getPlayer().getSelectedDeck());

                try {
                    Gamestate.getPlayer().getSelectedDeck().setHero(JsonReaders.HeroJsonReader(Gamestate.getPlayer(), "priest"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case "hunter":
                ArrayList<Carts> ar4 = Gamestate.getPlayer().getPlayerCarts();
                ar4.add(Carts.swampkingdred);
                Gamestate.getPlayer().setPlayerCarts(ar4);
                ar = Gamestate.getPlayer().getSelectedDeck().getDeck();
                ar.add(Carts.swampkingdred);
                Gamestate.getPlayer().getSelectedDeck().setDeck(ar);
                Gamestate.getPlayer().getAllDecks().replace(Gamestate.getPlayer().getSelectedDeck().getName() , Gamestate.getPlayer().getSelectedDeck());

                try {
                    Gamestate.getPlayer().getSelectedDeck().setHero(JsonReaders.HeroJsonReader(Gamestate.getPlayer(), "hunter"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
        }


    }




}
