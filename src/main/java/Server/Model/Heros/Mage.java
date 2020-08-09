package Server.Model.Heros;

import Server.Controller.Actions.SPVisitor.PowerVisitor;
import Server.Controller.Manager.Managers;
import Server.Model.Cards.Card;
import Server.Model.Enums.Carts;
import Server.Model.HeroPowers.MagePower;
import Server.Model.Interface.Character;
import org.codehaus.jackson.annotate.JsonTypeName;

import javax.persistence.Entity;
import java.util.ArrayList;

@JsonTypeName("mage")
@Entity
public class Mage extends Hero {
    public Mage() {
        this.setName("mage");
        this.setCanAttack(false);
        this.setPowerNeedEnemyTarget(true);
        this.setDamage(0);
        this.setHealth(30);
        this.setMaxHealth(30);
        this.setHeroPowerManaCost(2);
        this.setHeroPower(new MagePower());
    }

    @Override
    public void accept(PowerVisitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Managers managers) {
        visitor.visitMage(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, managers);
    }

    public static ArrayList<Carts> Spcards() {
        ArrayList<Carts> ar = new ArrayList<>();
        ar.add(Carts.polymorph);
        ar.add(Carts.flamestrike);
        return ar;
    }
}
