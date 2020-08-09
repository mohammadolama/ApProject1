package Server.Model.Cards;

import Server.Controller.Actions.CardVisitors.Visitor;
import Server.Controller.Manager.Managers;
import Server.Model.Enums.Rarity;
import Server.Model.Enums.Type;
import Server.Model.Heros.Hero;
import Server.Model.Interface.Character;

import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class SandBreath extends Spell {

    public SandBreath() {
        setName("sandbreath");
        setDescription("Give a minion +1/+2. Give it Divine Shield .");
        setManaCost(2);
        setType(Type.Spell);
        setHeroClass("Priest");
        setRarity(Rarity.Common);
        setPrice(20);
        setAttributes(new ArrayList<>());
        setNeedEnemyTarget(true);
        setNeedFriendlyTarget(true);
        setContiniousAction(false);
        setHealthRestore(1);
        setAttackRestore(2);
        setUsageTimes(1);
        setManaSpendOnSth(0);
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {
        visitor.visitSandBreath(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy, managers);
    }
}
