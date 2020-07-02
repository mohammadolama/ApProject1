package Model.Cards;

import Controller.Actions.Visitor;
import Model.Enums.Rarity;
import Model.Enums.Type;
import Model.Heros.Hero;
import Model.Interface.Character;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("shahryar")
public class Shahryar extends Minion {
    public Shahryar() {
        setName("Shahryar");
        setManaCost(4);
        setDamage(9);
        setHealth(9);
        setMaxHealth(9);
        setHealthRestore(0);
        setPrice(20);
        setType(Type.Minion);
        setHeroClass("Priest");
        setRarity(Rarity.Legendary);
        setDescription("Whenever you summon a minion, set its Health equal to this minion's");
        setContiniousAction(true);
        setAttackRestore(0);
        setTitle(null);
        setSleep(true);
        setCanBeAttacked(false);
        setAttributes(new ArrayList<>());
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitShahryar(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }
}
