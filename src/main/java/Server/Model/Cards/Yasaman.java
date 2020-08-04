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
public class Yasaman extends Minion {

    public Yasaman() {
        setName("yasaman");
        setManaCost(8);
        setDamage(8);
        setHealth(1);
        setMaxHealth(12);
        setHealthRestore(-200);
        setPrice(20);
        setType(Type.Minion);
        setHeroClass("Rogue");
        setRarity(Rarity.Legendary);
        setDescription("Battlecry : Destroy a minion and gain its health .");
        setNeedFriendlyTarget(true);
        setNeedEnemyTarget(true);
        setContiniousAction(false);
        setAttackRestore(0);
        setTitle(null);
        setSleep(true);
        setCanBeAttacked(false);
        setAttributes(new ArrayList<>(Collections.singletonList(Attribute.BattleCry)));
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitYasaman(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }
}
