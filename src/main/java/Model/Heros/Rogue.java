package Model.Heros;

import Controller.Actions.SPVisitor.PowerVisitor;
import Model.Cards.Card;
import Model.Enums.*;
import Model.HeroPowers.RoguePower;
import Model.Interface.Character;
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
