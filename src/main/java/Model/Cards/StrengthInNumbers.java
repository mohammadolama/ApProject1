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

@JsonTypeName("strengthinnumbers")
public class StrengthInNumbers extends Spell {

    public StrengthInNumbers() {
        setName("StrengthInNumbers");
        setDescription("Sidequest: Spend 10 Mana on minions.\nReward: Summon a minion from your deck.");
        setManaCost(1);
        setType(Type.Spell);
        setHeroClass("Neutral");
        setRarity(Rarity.Common);
        setPrice(20);
        setAttributes(new ArrayList<Attribute>(Arrays.asList(Attribute.Reward)));
        setContiniousAction(true);
        setHealthRestore(0);
        setAttackRestore(0);
        setUsageTimes(1);
        setManaSpendOnSth(10);
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitStrengthInNumbers(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }
}
