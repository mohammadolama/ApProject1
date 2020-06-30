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

@JsonTypeName("yasaman")
public class Yasaman extends Minion {

    public Yasaman() {
        setName("Yasaman");
        setManaCost(8);
        setDamage(8);
        setHealth(1);
        setMaxHealth(12);
        setHealthRestore(-200);
        setPrice(20);
        setType(Type.Minion);
        setHeroClass("Rogue");
        setRarity(Rarity.Legendary);
        setDescription("Battlecry : Destroy a minion and gain its health .");
        setTargetNeeded(true);
        setContiniousAction(false);
        setAttackRestore(0);
        setTitle(null);
        setSleep(true);
        setCanBeAttacked(false);
        setAttributes(new ArrayList<Attribute>(Arrays.asList(Attribute.BattleCry)));
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitYasaman(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }
}
