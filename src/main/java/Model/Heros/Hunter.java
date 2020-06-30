package Model.Heros;

import Model.HeroPower;
import Model.Enums.Carts;

import java.util.ArrayList;

public class Hunter extends Hero {
    public Hunter() {
        this.setName("Hunter");
        this.setCanAttack(false);
        this.setDamage(0);
        this.setHealth(30);
        this.setHeroPower(new HeroPower("Caltrops", 0));
        this.setHeroPowerManaCost(0);
        this.setSpecialPower("All minions of this hero , have \"Rush\"");
    }

    public static ArrayList<Carts> Spcards() {
        ArrayList<Carts> ar = new ArrayList<>();
        ar.add(Carts.faeze);
        ar.add(Carts.quiz);
        return ar;
    }
}
