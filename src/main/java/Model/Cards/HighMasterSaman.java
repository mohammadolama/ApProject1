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

@JsonTypeName("highmastersaman")
public class HighMasterSaman extends Minion {

    public HighMasterSaman() {
        setName("HighMasterSaman");
        setManaCost(8);
        setDamage(12);
        setHealth(12);
        setMaxHealth(12);
        setHealthRestore(-200);
        setPrice(20);
        setType(Type.Minion);
        setHeroClass("Neutral");
        setRarity(Rarity.Legendary);
        setDescription("Battlecry: Destroy all other minions");
        setTargetNeeded(false);
        setContiniousAction(false);
        setAttackRestore(0);
        setTitle("Dragon");
        setSleep(true);
        setCanBeAttacked(false);
        setAttributes(new ArrayList<Attribute>(Arrays.asList(Attribute.BattleCry)));
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitHighMasterSaman(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }
}
