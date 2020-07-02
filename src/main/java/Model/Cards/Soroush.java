package Model.Cards;

import Controller.Actions.Visitor;
import Model.Enums.Rarity;
import Model.Enums.Type;
import Model.Heros.Hero;
import Model.Interface.Character;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("soroush")
public class Soroush extends Spell {

    public Soroush() {
        setName("Soroush");
        setDescription("Give a minion +4/+4, Divine Shield, and Taunt");
        setManaCost(6);
        setType(Type.Spell);
        setHeroClass("Neutral");
        setRarity(Rarity.Rare);
        setPrice(20);
        setAttributes(new ArrayList<>());
        setNeedEnemyTarget(true);
        setNeedFriendlyTarget(true);
        setContiniousAction(false);
        setHealthRestore(4);
        setAttackRestore(4);
        setUsageTimes(1);
        setManaSpendOnSth(0);
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitSoroush(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }
}
