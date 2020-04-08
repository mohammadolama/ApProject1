package Main;

import Enums.Carts;
import Enums.NeutralCarts;
import Enums.SpecialCarts;
import Heros.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Update {

    static ArrayList<Carts> UpdateSellCards(Player player) throws IOException {
        ArrayList<Carts> ar = new ArrayList<>();
        ArrayList<Carts> ar2=new ArrayList<>();
        Hero mage = JsonReaders.HeroJsonReader(player,"mage");
        Hero rouge = JsonReaders.HeroJsonReader(player,"rouge");
        Hero warlock = JsonReaders.HeroJsonReader(player,"warlock");
        ar.addAll(mage.getPcarts());
        ar.addAll(rouge.getPcarts());
        ar.addAll(warlock.getPcarts());
        for (Carts carts : ar){
            int i=0;
            for (Carts carts1 : ar){
                if (carts.toString().equalsIgnoreCase(carts1.toString()))
                    i++;
            }
            if (i==3){
                if (ar2.contains(carts)==false)
                    ar2.add(carts);
            }else if (i==6){
                if (ar2.contains(carts)==false){
                    ar2.add(carts);
                    ar2.add(carts);
                }
            }
        }
        return ar2;
    }

    static void RemoveFromPcards(Player player,String card) {
        try {
            Hero mage = JsonReaders.HeroJsonReader(player, "mage");
            Hero rouge = JsonReaders.HeroJsonReader(player, "rouge");
            Hero warlock = JsonReaders.HeroJsonReader(player, "warlock");
            ArrayList<Carts> ar = mage.getPcarts();
            if (ar.contains(Carts.valueOf(card))) {
                ar.remove(Carts.valueOf(card));
            }
            ArrayList adasr = new ArrayList(Arrays.asList("s", "d"));
            JsonBuilders.HeroBuilder(player, mage);
            ar = rouge.getPcarts();
            if (ar.contains(Carts.valueOf(card))) {
                ar.remove(Carts.valueOf(card));
            }
            JsonBuilders.HeroBuilder(player, rouge);
            ar = warlock.getPcarts();
            if (ar.contains(Carts.valueOf(card))) {
                ar.remove(Carts.valueOf(card));
            }
            JsonBuilders.HeroBuilder(player, warlock);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.playerlog(player,e.toString());
        }
    }
    static void AddToPcards(String card , Player player) {
        try {
            Hero mage = JsonReaders.HeroJsonReader(player, "mage");
            Hero rouge = JsonReaders.HeroJsonReader(player, "rouge");
            Hero warlock = JsonReaders.HeroJsonReader(player, "warlock");
            for (NeutralCarts neutralCarts : NeutralCarts.values()) {
                if (neutralCarts.toString().equalsIgnoreCase(card)) {
                    ArrayList<Carts> ar = mage.getPcarts();
                    ar.add(Carts.valueOf(card));
                    mage.setPcarts(ar);
                    JsonBuilders.HeroBuilder(player, mage);
                    ar = rouge.getPcarts();
                    ar.add(Carts.valueOf(card));
                    rouge.setPcarts(ar);
                    JsonBuilders.HeroBuilder(player, rouge);
                    ar = warlock.getPcarts();
                    ar.add(Carts.valueOf(card));
                    warlock.setPcarts(ar);
                    JsonBuilders.HeroBuilder(player, warlock);
                }
            }
            player.setSelectedHero(JsonReaders.HeroJsonReader(player, player.getSelectedHero().getName().toLowerCase()));
            for (SpecialCarts specialCarts : SpecialCarts.values()) {
                if (specialCarts.toString().equalsIgnoreCase(card)) {
                    ArrayList<Carts> ar = player.getSelectedHero().getPcarts();
                    ar.add(Carts.valueOf(card));
                    player.getSelectedHero().setPcarts(ar);
                }
                JsonBuilders.HeroBuilder(player, player.getSelectedHero());
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.playerlog(player,e.toString());
        }
    }
}
