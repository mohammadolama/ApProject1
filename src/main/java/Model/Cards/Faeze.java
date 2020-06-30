package Model.Cards;

import Controller.Actions.Visitor;
import Model.Enums.Attribute;
import Model.Enums.Rarity;
import Model.Enums.Type;
import Model.Heros.Hero;
import Model.Interface.Character;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;
import java.util.Arrays;

@JsonTypeName("faeze")
public class Faeze extends Minion {

    public Faeze() {
        setName("Faeze");
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
        setTargetNeeded(false);
        setContiniousAction(false);
        setAttackRestore(0);
        setTitle("Beast");
        setSleep(true);
        setCanBeAttacked(false);
        setAttributes(new ArrayList<>());
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitFaeze(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }
}
