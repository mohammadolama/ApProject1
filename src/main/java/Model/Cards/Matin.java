package Model.Cards;

import Controller.Actions.Visitor;
import Model.Enums.Rarity;
import Model.Enums.Type;
import Model.Heros.Hero;
import Model.Interface.Character;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("matin")
public class Matin extends Minion {

    public Matin() {
        setName("Matin");
        setManaCost(5);
        setDamage(4);
        setHealth(4);
        setMaxHealth(4);
        setHealthRestore(1);
        setPrice(20);
        setType(Type.Minion);
        setHeroClass("Neutral");
        setRarity(Rarity.Rare);
        setDescription("Whenever you draw a card gain +1/+1");
        setContiniousAction(true);
        setAttackRestore(1);
        setTitle(null);
        setSleep(true);
        setCanBeAttacked(false);
        setAttributes(new ArrayList<>());
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitMatin(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }
}
