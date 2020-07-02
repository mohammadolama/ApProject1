package Model.Heros;

import Model.HeroPowers.HeroPower;
import Model.Enums.Carts;
import Model.HeroPowers.HunterPower;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("hunter")
public class Hunter extends Hero {


    public Hunter() {
        this.setName("Hunter");
        this.setCanAttack(false);
        this.setDamage(0);
        this.setHealth(30);
        this.setMaxHealth(30);
        this.setHeroPowerManaCost(0);
        this.setHeroPower(new HunterPower());
    }

    public static ArrayList<Carts> Spcards() {
        ArrayList<Carts> ar = new ArrayList<>();
        ar.add(Carts.faeze);
        ar.add(Carts.quiz);
        return ar;
    }
}
