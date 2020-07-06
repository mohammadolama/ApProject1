package Model.Heros;

import Controller.Actions.SPVisitor.PowerVisitor;
import Model.Cards.Card;
import Model.Enums.*;
import Model.HeroPowers.MagePower;
import Model.Interface.Character;
import org.codehaus.jackson.annotate.JsonTypeName;
import java.util.ArrayList;

@JsonTypeName("mage")
public class Mage extends Hero {
    public Mage() {
        this.setName("Mage");
        this.setCanAttack(false);
        this.setPowerNeedEnemyTarget(true);
        this.setDamage(0);
        this.setHealth(30);
        this.setMaxHealth(30);
        this.setHeroPowerManaCost(2);
        this.setHeroPower(new MagePower());
    }

    @Override
    public void accept(PowerVisitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed) {
        visitor.visitMage(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed);
    }

    public static ArrayList<Carts> Spcards() {
        ArrayList<Carts> ar = new ArrayList<>();
        ar.add(Carts.polymorph);
        ar.add(Carts.flamestrike);
        return ar;
    }
}
