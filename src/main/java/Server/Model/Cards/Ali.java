package Server.Model.Cards;

import Server.Controller.Actions.CardVisitors.Visitor;
import Server.Model.Enums.Attribute;
import Server.Model.Enums.Rarity;
import Server.Model.Enums.Type;
import Server.Model.Heros.Hero;
import Server.Model.Interface.Character;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Collections;

@Entity
public class Ali extends Minion {

    public Ali() {
        setName("ali");
        setManaCost(9);
        setDamage(5);
        setHealth(5);
        setMaxHealth(5);
        setHealthRestore(0);
        setPrice(20);
        setType(Type.Minion);
        setHeroClass("Neutral");
        setRarity(Rarity.Legendary);
        setDescription("Battlecry , Choose a friendly minion. Add a copy of it to your hand, deck,and battlefield .");
        setContiniousAction(false);
        setAttackRestore(0);
        setNeedFriendlyTarget(true);
        setTitle("Demon");
        setSleep(true);
        setCanBeAttacked(false);
        setAttributes(new ArrayList<>(Collections.singletonList(Attribute.BattleCry)));
    }


    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitAli(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }

}
