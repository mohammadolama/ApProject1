package Model.Cards;

import Controller.Actions.Visitor;
import Model.Enums.Rarity;
import Model.Enums.Type;
import Model.Heros.Hero;
import Model.Interface.Character;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("swarmofcats")
public class SwarmOfCats extends Spell {

    public SwarmOfCats() {
        setName("SwarmOfCats");
        setDescription("Summon seven 1/1 Cats with Rush");
        setManaCost(6);
        setType(Type.Spell);
        setHeroClass("Neutral");
        setRarity(Rarity.Rare);
        setPrice(20);
        setAttributes(new ArrayList<>());
        setTargetNeeded(false);
        setContiniousAction(false);
        setHealthRestore(0);
        setAttackRestore(0);
        setUsageTimes(1);
        setManaSpendOnSth(0);

    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitSwarmOfCats(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }
}
