package Server.Model.Cards;

import Server.Controller.Actions.CardVisitors.Visitor;
import Server.Model.Enums.Rarity;
import Server.Model.Enums.Type;
import Server.Model.Heros.Hero;
import Server.Model.Interface.Character;

import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class Soroush extends Spell {

    public Soroush() {
        setName("soroush");
        setDescription("Give a minion +4/+4, Divine Shield, and Taunt");
        setManaCost(6);
        setType(Type.Spell);
        setHeroClass("Neutral");
        setRarity(Rarity.Rare);
        setPrice(20);
        setAttributes(new ArrayList<>());
        setNeedEnemyTarget(true);
        setNeedFriendlyTarget(true);
        setContiniousAction(false);
        setHealthRestore(4);
        setAttackRestore(4);
        setUsageTimes(1);
        setManaSpendOnSth(0);
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitSoroush(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }
}
