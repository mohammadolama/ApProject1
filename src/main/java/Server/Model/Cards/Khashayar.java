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
public class Khashayar extends Minion {

    public Khashayar() {
        setName("khashayar");
        setManaCost(1);
        setDamage(2);
        setHealth(5);
        setMaxHealth(5);
        setHealthRestore(-5);
        setPrice(20);
        setType(Type.Minion);
        setHeroClass("Priest");
        setRarity(Rarity.Rare);
        setDescription("Battlecry : deal 5 damage to All minions .");
        setContiniousAction(false);
        setAttackRestore(0);
        setTitle(null);
        setSleep(true);
        setCanBeAttacked(false);
        setAttributes(new ArrayList<>(Collections.singletonList(Attribute.BattleCry)));
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitKhashayer(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }
}
