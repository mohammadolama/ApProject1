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
public class AghaHaghi extends Minion {

    public AghaHaghi() {
        setName("aghahaghi");
        setManaCost(5);
        setDamage(6);
        setHealth(5);
        setMaxHealth(5);
        setHealthRestore(0);
        setPrice(20);
        setType(Type.Minion);
        setHeroClass("Neutral");
        setRarity(Rarity.Rare);
        setDescription("Rush");
        setContiniousAction(false);
        setAttackRestore(0);
        setTitle("Dragon");
        setSleep(true);
        setCanBeAttacked(false);
        setAttributes(new ArrayList<>(Collections.singletonList(Attribute.Rush)));
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {
        visitor.visitAghaHaghi(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy, managers);
    }
}
