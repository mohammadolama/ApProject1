package Heros;

import Enums.*;
import Heros.Hero;
import Main.*;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.IOException;
import java.util.ArrayList;


@JsonIgnoreProperties({"purchasedCards", "CardsInDeck"})
public class Warlock extends Hero {
    public Warlock(Player player) throws IOException {
        super(player);
        this.setName("Warlock");
        this.setCanAttack(false);
        this.setATT(0);
        this.setHP(35);
        this.setHeroPower("Decrease 2 HP , do randomly : " +
                "\n 1) Add 1 HP and 1 ATT to a random minion (if there is) " +
                "\n 2) Add a card from your deck and add it to your hand .");
        this.setHeroPowerManaCost(3);
        this.setSpecialPower("This hero has 35 HP at the start of the game .");
        this.setSPcarts(Spcards());
    }

    public static ArrayList<Carts> Spcards() {
        ArrayList<Carts> ar = new ArrayList<>();
        ar.add(Carts.darkskies);
        ar.add(Carts.dreadscale);
        return ar;
    }
}