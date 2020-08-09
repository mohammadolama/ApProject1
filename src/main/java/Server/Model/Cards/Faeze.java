package Server.Model.Cards;

import Server.Controller.Actions.CardVisitors.Visitor;
import Server.Controller.Manager.Managers;
import Server.Model.Enums.Rarity;
import Server.Model.Enums.Type;
import Server.Model.Heros.Hero;
import Server.Model.Interface.Character;

import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class Faeze extends Minion {

    public Faeze() {
        setName("faeze");
        setManaCost(7);
        setDamage(9);
        setMaxHealth(9);
        setHealth(9);
        setHealthRestore(0);
        setPrice(20);
        setType(Type.Minion);
        setHeroClass("Hunter");
        setRarity(Rarity.Legendary);
        setDescription("Alternative for \"Swamp King Dred\" ");
        setContiniousAction(false);
        setAttackRestore(0);
        setTitle("Beast");
        setSleep(true);
        setCanBeAttacked(false);
        setAttributes(new ArrayList<>());
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {
        visitor.visitFaeze(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy, managers);
    }
}
