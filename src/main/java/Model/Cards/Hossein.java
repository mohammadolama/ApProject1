package Model.Cards;

import Controller.Actions.CardVisitors.Visitor;
import Model.Enums.Attribute;
import Model.Enums.Rarity;
import Model.Enums.Type;
import Model.Heros.Hero;
import Model.Interface.Character;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;
import java.util.Collections;

@JsonTypeName("hossein")
public class Hossein extends Minion {

    public Hossein() {
        setName("Hossein");
        setManaCost(2);
        setDamage(3);
        setHealth(3);
        setMaxHealth(3);
        setHealthRestore(0);
        setPrice(20);
        setType(Type.Minion);
        setHeroClass("Neutral");
        setRarity(Rarity.Common);
        setDescription("Divine shield");
        setContiniousAction(false);
        setAttackRestore(0);
        setTitle(null);
        setSleep(true);
        setCanBeAttacked(false);
        setAttributes(new ArrayList<>(Collections.singletonList(Attribute.DivineShield)));
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitHosseinHima(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }
}
