package Server.Model.Cards;

import Server.Controller.Actions.CardVisitors.Visitor;
import Server.Controller.Manager.Managers;
import Server.Model.Enums.Attribute;
import Server.Model.Enums.Rarity;
import Server.Model.Enums.Type;
import Server.Model.Heros.Hero;
import Server.Model.Interface.Character;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Collections;

@Entity
public class StrengthInNumbersDR extends Spell {

    public StrengthInNumbersDR() {
        setName("strengthinnumbersdr");
        setDescription("Sidequest: Spend 10 Mana on minions.\nReward: Summon Lachin.");
        setManaCost(1);
        setType(Type.Spell);
        setHeroClass("Neutral");
        setRarity(Rarity.Common);
        setPrice(20);
        setAttributes(new ArrayList<>(Collections.singletonList(Attribute.Reward)));
        setContiniousAction(true);
        setHealthRestore(0);
        setAttackRestore(0);
        setUsageTimes(1);
        setMaxManaSpendOnSth(10);
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {
        visitor.visitStrengthInNumbersDR(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy, managers);
    }
}
