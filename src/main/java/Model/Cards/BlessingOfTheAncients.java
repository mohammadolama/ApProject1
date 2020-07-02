package Model.Cards;

import Controller.Actions.Visitor;
import Model.Enums.Rarity;
import Model.Enums.Type;
import Model.Heros.Hero;
import Model.Interface.Character;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("blessingoftheancients")
public class BlessingOfTheAncients extends Spell {

    public BlessingOfTheAncients() {
        setName("BlessingOfTheAncients");
        setDescription("Give your minions +1/+1 .");
        setManaCost(3);
        setType(Type.Spell);
        setHeroClass("Neutral");
        setRarity(Rarity.Common);
        setPrice(20);
        setAttributes(new ArrayList<>());

        setContiniousAction(false);
        setHealthRestore(1);
        setAttackRestore(1);
        setUsageTimes(1);
        setManaSpendOnSth(0);
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitBlessingOFTheAncient(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }
}
