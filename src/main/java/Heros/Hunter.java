package Heros;

import AllCards.HeroPower;
import Enums.Carts;
import Main.Player;

import java.io.IOException;
import java.util.ArrayList;

public class Hunter extends Hero {
    public Hunter() {
        this.setName("Hunter");
        this.setCanAttack(false);
        this.setAtt(0);
        this.setHp(30);
        this.setHeroPower(new HeroPower("Caltrops", 0));
        this.setHeroPowerManaCost(0);
        this.setSpecialPower("All minions of this hero , have \"Rush\"");
    }

    public static ArrayList<Carts> Spcards() {
        ArrayList<Carts> ar = new ArrayList<>();
        ar.add(Carts.swampkingdred);
        ar.add(Carts.deadlyshot);
        return ar;
    }
}
