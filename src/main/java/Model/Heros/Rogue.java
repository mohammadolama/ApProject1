package Model.Heros;

import Model.Cards.HeroPower;
import Model.Enums.*;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties({"purchasedCards", "CardsInDeck"})
public class Rogue extends Hero {
    public Rogue() {
        this.setName("Rogue");
        this.setCanAttack(false);
        this.setAtt(0);
        this.setHp(30);
        this.setHeroPower(new HeroPower("Rubbery", 3));
        this.setHeroPowerManaCost(3);
        this.setSpecialPower("A card which is not in your class or not neutral, costs 2 less mana .");
    }

    public static ArrayList<Carts> Spcards() {
        ArrayList<Carts> ar = new ArrayList<>();
        ar.add(Carts.friendlysmith);
        ar.add(Carts.umbralskulker);
        return ar;
    }

}
