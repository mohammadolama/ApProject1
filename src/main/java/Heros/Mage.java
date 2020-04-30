package Heros;

import AllCards.HeroPower;
import Main.Player;
import Enums.*;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.IOException;
import java.util.ArrayList;

@JsonIgnoreProperties({"purchasedCards", "CardsInDeck"})
public class Mage extends Hero {
    public Mage() {
        this.setName("Mage");
        this.setCanAttack(false);
        this.setAtt(0);
        this.setHp(30);
        this.setHeroPower(new HeroPower("FireBlast", 2));
        this.setHeroPowerManaCost(2);
        this.setSpecialPower("They use 2 less mana for using Spells .");
    }

    public static ArrayList<Carts> Spcards() {
        ArrayList<Carts> ar = new ArrayList<>();
        ar.add(Carts.polymorph);
        ar.add(Carts.flamestrike);
        return ar;
    }
}
