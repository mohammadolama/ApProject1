package Server.Model.Heros;

import Server.Controller.Actions.SPVisitor.PowerVisitor;
import Server.Controller.Manager.Managers;
import Server.Model.Cards.Card;
import Server.Model.Enums.Carts;
import Server.Model.HeroPowers.WarlockPower;
import Server.Model.Interface.Character;
import org.codehaus.jackson.annotate.JsonTypeName;

import javax.persistence.Entity;
import java.util.ArrayList;

@JsonTypeName("warlock")
@Entity
public class Warlock extends Hero {
    public Warlock() {
        this.setName("warlock");
        this.setCanAttack(false);
        this.setDamage(0);
        this.setHealth(35);
        this.setMaxHealth(35);
        this.setHeroPowerManaCost(3);
        this.setHeroPower(new WarlockPower());
    }

    @Override
    public void accept(PowerVisitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Managers managers) {
        visitor.visitWarlock(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, managers);
    }

    public static ArrayList<Carts> Spcards() {
        ArrayList<Carts> ar = new ArrayList<>();
        ar.add(Carts.darkskies);
        ar.add(Carts.benyamin);
        return ar;
    }
}