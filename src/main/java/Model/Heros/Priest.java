package Model.Heros;

import Controller.Actions.SPVisitor.PowerVisitor;
import Model.Cards.Card;
import Model.HeroPowers.HeroPower;
import Model.Enums.Carts;
import Model.HeroPowers.PriestPower;
import Model.Interface.Character;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("priest")
public class Priest extends Hero {
    public Priest() {
        this.setName("Priest");
        this.setCanAttack(false);
        this.setDamage(0);
        this.setHealth(30);
        this.setPowerNeedFriendlyTarget(true);
        this.setMaxHealth(30);
        this.setHeroPowerManaCost(2);
        this.setHeroPower(new PriestPower());
    }

    @Override
    public void accept(PowerVisitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed) {
        visitor.visitPriest(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed);
    }

    public static ArrayList<Carts> Spcards() {
        ArrayList<Carts> ar = new ArrayList<>();
        ar.add(Carts.sandbreath);
        ar.add(Carts.shahryar);
        return ar;
    }
}
