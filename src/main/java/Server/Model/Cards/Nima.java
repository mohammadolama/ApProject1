package Server.Model.Cards;

import Server.Controller.Actions.CardVisitors.Visitor;
import Server.Model.Enums.Rarity;
import Server.Model.Enums.Type;
import Server.Model.Heros.Hero;
import Server.Model.Interface.Character;

import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class Nima extends Minion {

    public Nima() {
        setName("nima");
        setManaCost(4);
        setDamage(9);
        setHealth(9);
        setMaxHealth(9);
        setHealthRestore(0);
        setPrice(20);
        setType(Type.Minion);
        setHeroClass("Neutral");
        setRarity(Rarity.Epic);
        setDescription("Do Absolutely Nothing . ");
        setAttackRestore(0);
        setTitle("Pirate");
        setSleep(true);
        setCanBeAttacked(false);
        setAttributes(new ArrayList<>());
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitNima(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }
}
