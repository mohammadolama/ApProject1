package Server.Model.Cards;

import Server.Controller.Actions.CardVisitors.Visitor;
import Server.Model.Enums.Attribute;
import Server.Model.Enums.Rarity;
import Server.Model.Enums.Type;
import Server.Model.Heros.Hero;
import Server.Model.Interface.Character;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Arrays;

@Entity
public class HosseinHima extends Minion {

    public HosseinHima() {
        setName("hosseinhima");
        setManaCost(6);
        setDamage(5);
        setHealth(3);
        setMaxHealth(3);
        setHealthRestore(0);
        setPrice(20);
        setType(Type.Minion);
        setHeroClass("Neutral");
        setRarity(Rarity.Common);
        setDescription("Divine Shield , Rush ");
        setContiniousAction(false);
        setAttackRestore(0);
        setTitle("Dragon");
        setSleep(true);
        setCanBeAttacked(false);
        setAttributes(new ArrayList<>(Arrays.asList(Attribute.Rush, Attribute.DivineShield)));
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitHossein(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }
}
