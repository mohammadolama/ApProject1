package Main;

import Enums.Carts;
import Enums.NeutralCarts;
import Enums.SpecialCarts;
import Heros.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Update {

    static ArrayList<Carts> UpdateSellCards() {
        ArrayList<Carts> ar = new ArrayList<>();
        for (Map.Entry<String, Deck> entry : Gamestate.getPlayer().getAllDecks().entrySet()) {
            Deck deck=entry.getValue();
            for (Carts carts : deck.getDeck()) {
                ar.add(carts);
            }
        }
        return ar;

    }

//    static void RemoveFromPcards(Player player,String card) {
//        try {
//            Hero mage = JsonReaders.HeroJsonReader(player, "mage");
//            Hero rogue = JsonReaders.HeroJsonReader(player, "rogue");
//            Hero warlock = JsonReaders.HeroJsonReader(player, "warlock");
//            ArrayList<Carts> ar = mage.getPcarts();
//            if (ar.contains(Carts.valueOf(card))) {
//                ar.remove(Carts.valueOf(card));
//            }
//            ArrayList adasr = new ArrayList(Arrays.asList("s", "d"));
//            JsonBuilders.HeroBuilder(player, mage);
//            ar = rogue.getPcarts();
//            if (ar.contains(Carts.valueOf(card))) {
//                ar.remove(Carts.valueOf(card));
//            }
//            JsonBuilders.HeroBuilder(player, rogue);
//            ar = warlock.getPcarts();
//            if (ar.contains(Carts.valueOf(card))) {
//                ar.remove(Carts.valueOf(card));
//            }
//            JsonBuilders.HeroBuilder(player, warlock);
//        } catch (IOException e) {
//            e.printStackTrace();
//            LOGGER.playerlog(player,e.toString());
//        }
//    }


//    static void AddToPcards(String card , Player player) {
//        try {
//            Hero mage = JsonReaders.HeroJsonReader(player, "mage");
//            Hero rogue = JsonReaders.HeroJsonReader(player, "rogue");
//            Hero warlock = JsonReaders.HeroJsonReader(player, "warlock");
//            for (NeutralCarts neutralCarts : NeutralCarts.values()) {
//                if (neutralCarts.toString().equalsIgnoreCase(card)) {
//                    ArrayList<Carts> ar = mage.getPcarts();
//                    ar.add(Carts.valueOf(card));
//                    mage.setPcarts(ar);
//                    JsonBuilders.HeroBuilder(player, mage);
//                    ar = rogue.getPcarts();
//                    ar.add(Carts.valueOf(card));
//                    rogue.setPcarts(ar);
//                    JsonBuilders.HeroBuilder(player, rogue);
//                    ar = warlock.getPcarts();
//                    ar.add(Carts.valueOf(card));
//                    warlock.setPcarts(ar);
//                    JsonBuilders.HeroBuilder(player, warlock);
//                }
//            }
//            player.setSelectedHero(JsonReaders.HeroJsonReader(player, player.getSelectedHero().getName().toLowerCase()));
//            for (SpecialCarts specialCarts : SpecialCarts.values()) {
//                if (specialCarts.toString().equalsIgnoreCase(card)) {
//                    ArrayList<Carts> ar = player.getSelectedHero().getPcarts();
//                    ar.add(Carts.valueOf(card));
//                    player.getSelectedHero().setPcarts(ar);
//                }
//                JsonBuilders.HeroBuilder(player, player.getSelectedHero());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            LOGGER.playerlog(player,e.toString());
//        }
//    }
}
