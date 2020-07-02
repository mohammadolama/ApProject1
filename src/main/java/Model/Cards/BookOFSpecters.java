package Model.Cards;

import Controller.Actions.Visitor;
import Model.Enums.Rarity;
import Model.Enums.Type;
import Model.Heros.Hero;
import Model.Interface.Character;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("bookofspecters")
public class BookOFSpecters extends Spell {

    public BookOFSpecters() {
        setName("BookOfSpecters");
        setDescription("Draw 3 cards.");
        setManaCost(2);
        setType(Type.Spell);
        setHeroClass("Neutral");
        setRarity(Rarity.Epic);
        setPrice(20);
        setAttributes(new ArrayList<>());

        setContiniousAction(false);
        setHealthRestore(0);
        setAttackRestore(0);
        setUsageTimes(1);
        setManaSpendOnSth(0);
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitBookOfSpecters(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }
}
