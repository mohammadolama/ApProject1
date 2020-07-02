package Model.Heros;

import Model.HeroPowers.HeroPower;
import Model.Enums.*;
import Model.HeroPowers.PriestPower;
import Model.HeroPowers.WarlockPower;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("warlock")
public class Warlock extends Hero {
    public Warlock() {
        this.setName("Warlock");
        this.setCanAttack(false);
        this.setDamage(0);
        this.setHealth(35);
        this.setMaxHealth(35);
        this.setHeroPowerManaCost(3);
        this.setHeroPower(new WarlockPower());
    }

    public static ArrayList<Carts> Spcards() {
        ArrayList<Carts> ar = new ArrayList<>();
        ar.add(Carts.darkskies);
        ar.add(Carts.benyamin);
        return ar;
    }
}