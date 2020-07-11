package Model.Cards;

import Controller.Actions.CardVisitors.Visitor;
import Model.Enums.Rarity;
import Model.Enums.Type;
import Model.Heros.Hero;
import Model.Interface.Character;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("bloodfury")
public class BloodFury extends Weapon {

    public BloodFury() {
        setName("BloodFury");
        setManaCost(3);
        setDamage(3);
        setDurability(8);
        setHealthRestore(0);
        setPrice(20);
        setType(Type.Weapon);
        setHeroClass("Neutral");
        setRarity(Rarity.Common);
        setDescription(null);
        setContiniousAction(false);
        setAttackRestore(0);
        setAttributes(new ArrayList<>());
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitBloodFury(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }
}
