package Heros;

import AllCards.HeroPower;
import Enums.*;
import Heros.Hero;
import Main.*;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.IOException;
import java.util.ArrayList;


@JsonIgnoreProperties({"purchasedCards", "CardsInDeck"})
public class Warlock extends Hero {
    public Warlock() {
        this.setName("Warlock");
        this.setCanAttack(false);
        this.setAtt(0);
        this.setHp(35);
        this.setHeroPower(new HeroPower("LifeTap", 2));
        this.setHeroPowerManaCost(3);
        this.setSpecialPower("This hero has 35 HP at the start of the game .");
    }

    public static ArrayList<Carts> Spcards() {
        ArrayList<Carts> ar = new ArrayList<>();
        ar.add(Carts.darkskies);
        ar.add(Carts.dreadscale);
        return ar;
    }
}