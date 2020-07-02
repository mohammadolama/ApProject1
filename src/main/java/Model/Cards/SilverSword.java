package Model.Cards;

import Controller.Actions.Visitor;
import Model.Enums.Rarity;
import Model.Enums.Type;
import Model.Heros.Hero;
import Model.Interface.Character;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("silversword")
public class SilverSword extends Weapon {
    public SilverSword() {
        setName("SilverSword");
        setManaCost(4);
        setDamage(2);
        setDurability(3);
        setHealthRestore(0);
        setPrice(20);
        setType(Type.Weapon);
        setHeroClass("Neutral");
        setRarity(Rarity.Epic);
        setDescription(null);
        setContiniousAction(false);
        setAttackRestore(0);
        setAttributes(new ArrayList<>());

    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitSilverSword(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }
}
