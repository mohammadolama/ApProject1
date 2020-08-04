package Server.Model.Cards;

import Server.Controller.Actions.CardVisitors.Visitor;
import Server.Model.Enums.Rarity;
import Server.Model.Enums.Type;
import Server.Model.Heros.Hero;
import Server.Model.Interface.Character;

import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class Aylar extends Spell {

    public Aylar() {
        setName("aylar");
        setDescription("Discover a weapon from any class . Add it to your deck with +2/+2 .");
        setManaCost(1);
        setType(Type.Spell);
        setHeroClass("Rogue");
        setRarity(Rarity.Common);
        setPrice(20);
        setAttributes(new ArrayList<>());
        setContiniousAction(false);
        setHealthRestore(2);
        setAttackRestore(2);
        setUsageTimes(1);
        setManaSpendOnSth(0);
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitAylar(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }
}
