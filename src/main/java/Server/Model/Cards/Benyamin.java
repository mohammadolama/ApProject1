package Server.Model.Cards;

import Server.Controller.Actions.CardVisitors.Visitor;
import Server.Model.Enums.Rarity;
import Server.Model.Enums.Type;
import Server.Model.Heros.Hero;
import Server.Model.Interface.Character;

import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class Benyamin extends Minion {

    public Benyamin() {
        setName("benyamin");
        setManaCost(3);
        setDamage(4);
        setHealth(2);
        setMaxHealth(2);
        setHealthRestore(-1);
        setPrice(20);
        setType(Type.Minion);
        setHeroClass("Warlock");
        setRarity(Rarity.Legendary);
        setDescription("At the end of your turn , deal 1 damage to all other minions .");
        setContiniousAction(true);
        setAttackRestore(0);
        setTitle("Beast");
        setSleep(true);
        setCanBeAttacked(false);
        setAttributes(new ArrayList<>());
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitBenyamin(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }
}
