package Model.Heros;

import Model.HeroPowers.HeroPower;
import Model.Enums.Carts;
import Model.HeroPowers.PriestPower;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("priest")
public class Priest extends Hero {
    public Priest() {
        this.setName("Priest");
        this.setCanAttack(false);
        this.setDamage(0);
        this.setHealth(30);
        this.setMaxHealth(30);
        this.setHeroPowerManaCost(2);
        this.setHeroPower(new PriestPower());
    }

    public static ArrayList<Carts> Spcards() {
        ArrayList<Carts> ar = new ArrayList<>();
        ar.add(Carts.sandbreath);
        ar.add(Carts.shahryar);
        return ar;
    }
}
