package Model.Cards;

import Controller.Actions.Visitor;
import Model.Enums.Rarity;
import Model.Enums.Type;
import Model.Heros.Hero;
import Model.Interface.Character;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("javad")
public class Javad extends Minion {
    public Javad() {
        setName("Javad");
        setManaCost(0);
        setDamage(6);
        setHealth(6);
        setMaxHealth(6);
        setHealthRestore(0);
        setPrice(20);
        setType(Type.Minion);
        setHeroClass("Neutral");
        setRarity(Rarity.Common);
        setDescription(null);
        setContiniousAction(false);
        setAttackRestore(0);
        setTitle(null);
        setSleep(true);
        setCanBeAttacked(false);
        setAttributes(new ArrayList<>());
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitJavad(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }
}
