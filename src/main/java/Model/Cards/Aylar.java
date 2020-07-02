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

@JsonTypeName("aylar")
public class Aylar extends Spell {

    public Aylar() {
        setName("Aylar");
        setDescription("Discover a weapon from any class . Add it to your deck with +2/+2 .");
        setManaCost(1);
        setType(Type.Spell);
        setHeroClass("Rogue");
        setRarity(Rarity.Common);
        setPrice(20);
        setAttributes(new ArrayList<>());
        setContiniousAction(false);
        setHealthRestore(2);
        setAttackRestore(2);
        setUsageTimes(1);
        setManaSpendOnSth(0);
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitAylar(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }
}
