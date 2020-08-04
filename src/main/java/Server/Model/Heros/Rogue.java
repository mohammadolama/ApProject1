package Server.Model.Heros;

import Server.Controller.Actions.SPVisitor.PowerVisitor;
import Server.Model.Cards.Card;
import Server.Model.Enums.Carts;
import Server.Model.HeroPowers.RoguePower;
import Server.Model.Interface.Character;
import org.codehaus.jackson.annotate.JsonTypeName;

import javax.persistence.Entity;
import java.util.ArrayList;

@JsonTypeName("rogue")
@Entity
public class Rogue extends Hero {
    public Rogue() {
        this.setName("rogue");
        this.setCanAttack(false);
        this.setDamage(0);
        this.setHealth(30);
        this.setMaxHealth(30);
        this.setHeroPowerManaCost(3);
        this.setHeroPower(new RoguePower());
    }

    @Override
    public void accept(PowerVisitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed) {
        visitor.visitRogue(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed);
    }

    public static ArrayList<Carts> Spcards() {
        ArrayList<Carts> ar = new ArrayList<>();
        ar.add(Carts.aylar);
        ar.add(Carts.yasaman);
        return ar;
    }

}
