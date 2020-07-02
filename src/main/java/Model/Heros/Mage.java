package Model.Heros;

import Model.Enums.*;
import Model.HeroPowers.MagePower;
import org.codehaus.jackson.annotate.JsonTypeName;
import java.util.ArrayList;

@JsonTypeName("mage")
public class Mage extends Hero {
    public Mage() {
        this.setName("Mage");
        this.setCanAttack(false);
        this.setDamage(0);
        this.setHealth(30);
        this.setMaxHealth(30);
        this.setHeroPowerManaCost(2);
        this.setHeroPower(new MagePower());
    }

    public static ArrayList<Carts> Spcards() {
        ArrayList<Carts> ar = new ArrayList<>();
        ar.add(Carts.polymorph);
        ar.add(Carts.flamestrike);
        return ar;
    }
}
