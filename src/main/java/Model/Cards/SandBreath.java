package Model.Cards;

import Controller.Actions.CardVisitors.Visitor;
import Model.Enums.Rarity;
import Model.Enums.Type;
import Model.Heros.Hero;
import Model.Interface.Character;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("sandbreath")
public class SandBreath extends Spell {

    public SandBreath() {
        setName("SandBreath");
        setDescription("Give a minion +1/+2. Give it Divine Shield .");
        setManaCost(2);
        setType(Type.Spell);
        setHeroClass("Priest");
        setRarity(Rarity.Common);
        setPrice(20);
        setAttributes(new ArrayList<>());
        setNeedEnemyTarget(true);
        setNeedFriendlyTarget(true);
        setContiniousAction(false);
        setHealthRestore(1);
        setAttackRestore(2);
        setUsageTimes(1);
        setManaSpendOnSth(0);
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitSandBreath(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }
}
