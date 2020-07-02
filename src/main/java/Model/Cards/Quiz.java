package Model.Cards;

import Controller.Actions.Visitor;
import Model.Enums.Rarity;
import Model.Enums.Type;
import Model.Heros.Hero;
import Model.Interface.Character;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("quiz")
public class Quiz extends Spell {

    public Quiz() {
        setName("Quiz");
        setDescription("Destroy a random enemy minion");
        setManaCost(3);
        setType(Type.Spell);
        setHeroClass("Hunter");
        setRarity(Rarity.Common);
        setPrice(20);
        setAttributes(new ArrayList<>());
        setContiniousAction(false);
        setHealthRestore(-200);
        setAttackRestore(0);
        setUsageTimes(1);
        setManaSpendOnSth(0);
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitQuiz(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }
}
