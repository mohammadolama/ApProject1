package Heros;

import AllCards.HeroPower;
import Enums.Carts;
import Main.Player;

import java.util.ArrayList;

public class Priest extends Hero {
    public Priest() {
        this.setName("Priest");
        this.setCanAttack(false);
        this.setAtt(0);
        this.setHp(30);
        this.setHeroPower(new HeroPower("Heal", 2));
        this.setHeroPowerManaCost(2);
        this.setSpecialPower("All spells related to \"Restore\" have double effect.");
    }

    public static ArrayList<Carts> Spcards() {
        ArrayList<Carts> ar = new ArrayList<>();
        ar.add(Carts.sandbreath);
        ar.add(Carts.highpriestamet);
        return ar;
    }
}
