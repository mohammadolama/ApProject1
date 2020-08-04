package Server.Model.Cards;

import Server.Controller.Actions.CardVisitors.Visitor;
import Server.Model.Enums.Rarity;
import Server.Model.Enums.Type;
import Server.Model.Heros.Hero;
import Server.Model.Interface.Character;

import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class Polymorph extends Spell {

    public Polymorph() {
        setName("polymorph");
        setDescription("Transform a minion into a 1/1 sheep .");
        setManaCost(4);
        setType(Type.Spell);
        setHeroClass("Mage");
        setRarity(Rarity.Rare);
        setPrice(20);
        setAttributes(new ArrayList<>());
        setNeedEnemyTarget(true);
        setNeedFriendlyTarget(true);
        setContiniousAction(false);
        setHealthRestore(0);
        setAttackRestore(0);
        setUsageTimes(1);
        setManaSpendOnSth(0);
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitPolymorph(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }
}
