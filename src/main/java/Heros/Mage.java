package Heros;

import Main.Player;
import Enums.*;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import java.io.IOException;
import java.util.ArrayList;

@JsonIgnoreProperties({"purchasedCards", "CardsInDeck"})
public class Mage extends Hero {
    public Mage(Player player) throws IOException {
        super(player);
        this.setName("Mage");
        this.setCanAttack(false);
        this.setATT(0);
        this.setHP(30);
        this.setHeroPower("FireBlast : Deal 1 damage , Costs 2 Mana");
        this.setHeroPowerManaCost(2);
        this.setSpecialPower("They use 2 less mana for using Spells .");
        this.setSPcarts(Spcards());
    }







    private ArrayList<Carts> Spcards() {
        ArrayList<Carts> ar = new ArrayList<>();
        ar.add(Carts.polymorph);
        ar.add(Carts.flamestrike);
        return ar;
    }
}
