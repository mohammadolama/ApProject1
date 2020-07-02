package Model.Heros;

import Model.HeroPowers.HeroPower;
import Model.Enums.*;
import Model.HeroPowers.PriestPower;
import Model.HeroPowers.RoguePower;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("rogue")
public class Rogue extends Hero {
    public Rogue() {
        this.setName("Rogue");
        this.setCanAttack(false);
        this.setDamage(0);
        this.setHealth(30);
        this.setMaxHealth(30);
        this.setHeroPowerManaCost(3);
        this.setHeroPower(new RoguePower());
    }

    public static ArrayList<Carts> Spcards() {
        ArrayList<Carts> ar = new ArrayList<>();
        ar.add(Carts.aylar);
        ar.add(Carts.yasaman);
        return ar;
    }

}
