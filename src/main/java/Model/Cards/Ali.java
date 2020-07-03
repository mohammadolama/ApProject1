package Model.Cards;

import Controller.Actions.Visitor;
import Model.Enums.Attribute;
import Model.Enums.Rarity;
import Model.Enums.Type;
import Model.Heros.Hero;
import Model.Interface.Character;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

@JsonTypeName("ali")
public class Ali extends Minion {

    public Ali() {
        setName("Ali");
        setManaCost(9);
        setDamage(5);
        setHealth(5);
        setMaxHealth(5);
        setHealthRestore(0);
        setPrice(20);
        setType(Type.Minion);
        setHeroClass("Neutral");
        setRarity(Rarity.Legendary);
        setDescription("Battlecry , Choose a friendly minion. Add a copy of it to your hand, deck,and battlefield .");
        setContiniousAction(false);
        setAttackRestore(0);
        setNeedFriendlyTarget(true);
        setTitle("Demon");
        setSleep(true);
        setCanBeAttacked(false);
        setAttributes(new ArrayList<Attribute>(Arrays.asList(Attribute.BattleCry)));
    }


    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitAli(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }

}
