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

@JsonTypeName("cat")
public class Cat extends Minion {

    public Cat() {
        setName("Cat");
        setManaCost(0);
        setDamage(1);
        setHealth(1);
        setMaxHealth(1);
        setHealthRestore(0);
        setPrice(0);
        setType(Type.Minion);
        setHeroClass("Neutral");
        setRarity(Rarity.Common);
        setDescription(null);
        setTargetNeeded(false);
        setContiniousAction(false);
        setAttackRestore(0);
        setTitle(null);
        setSleep(true);
        setCanBeAttacked(false);
        setAttributes(new ArrayList<Attribute>(Arrays.asList(Attribute.Rush)));
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitCat(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }
}
