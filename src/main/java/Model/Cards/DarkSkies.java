package Model.Cards;

import Controller.Actions.Visitor;
import Model.Enums.Rarity;
import Model.Enums.Type;
import Model.Heros.Hero;
import Model.Interface.Character;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("darkskies")
public class DarkSkies extends Spell {

    public DarkSkies() {
        setName("DarkSkies");
        setDescription("Deal 2 damage to a random minion .");
        setManaCost(3);
        setType(Type.Spell);
        setHeroClass("Warlock");
        setRarity(Rarity.Epic);
        setPrice(20);
        setAttributes(new ArrayList<>());
        setTargetNeeded(false);
        setContiniousAction(false);
        setHealthRestore(-2);
        setAttackRestore(0);
        setUsageTimes(1);
        setManaSpendOnSth(0);
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitDarkSkies(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }
}
