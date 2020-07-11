package Model.Heros;

import Controller.Actions.SPVisitor.PowerVisitor;
import Model.Cards.Card;
import Model.Enums.*;
import Model.HeroPowers.WarlockPower;
import Model.Interface.Character;
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

    @Override
    public void accept(PowerVisitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed) {
        visitor.visitWarlock(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed);
    }

    public static ArrayList<Carts> Spcards() {
        ArrayList<Carts> ar = new ArrayList<>();
        ar.add(Carts.darkskies);
        ar.add(Carts.benyamin);
        return ar;
    }
}