package Model.Heros;

import Controller.Actions.SPVisitor.PowerVisitor;
import Model.Cards.Card;
import Model.Enums.Carts;
import Model.HeroPowers.HunterPower;
import Model.Interface.Character;
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

    @Override
    public void accept(PowerVisitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed) {
        visitor.visitHunter(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed);
    }

    public static ArrayList<Carts> Spcards() {
        ArrayList<Carts> ar = new ArrayList<>();
        ar.add(Carts.faeze);
        ar.add(Carts.quiz);
        return ar;
    }
}
