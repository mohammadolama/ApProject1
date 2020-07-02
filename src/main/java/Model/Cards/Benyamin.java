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

@JsonTypeName("benyamin")
public class Benyamin extends Minion {

    public Benyamin() {
        setName("Benyamin");
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
