package Server.Model.Heros;

import Server.Controller.Actions.SPVisitor.PowerVisitor;
import Server.Controller.Manager.Managers;
import Server.Model.Cards.Card;
import Server.Model.Enums.Carts;
import Server.Model.HeroPowers.HunterPower;
import Server.Model.Interface.Character;
import org.codehaus.jackson.annotate.JsonTypeName;

import javax.persistence.Entity;
import java.util.ArrayList;

@JsonTypeName("hunter")
@Entity
public class Hunter extends Hero {


    public Hunter() {
        this.setName("hunter");
        this.setCanAttack(false);
        this.setDamage(0);
        this.setHealth(30);
        this.setMaxHealth(30);
        this.setHeroPowerManaCost(0);
        this.setHeroPower(new HunterPower());
    }

    @Override
    public void accept(PowerVisitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Managers managers) {
        visitor.visitHunter(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, managers);
    }

    public static ArrayList<Carts> Spcards() {
        ArrayList<Carts> ar = new ArrayList<>();
        ar.add(Carts.faeze);
        ar.add(Carts.quiz);
        return ar;
    }
}
