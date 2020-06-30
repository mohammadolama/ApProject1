package Model.Cards;

import Controller.Actions.Visitor;
import Model.Enums.Attribute;
import Model.Enums.Rarity;
import Model.Enums.Type;
import Model.Heros.Hero;
import Model.Interface.Character;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;
import java.util.Arrays;

@JsonTypeName("mobin")
public class Mobin extends Minion {

    public Mobin() {
        setName("Mobin");
        setManaCost(8);
        setDamage(3);
        setHealth(6);
        setMaxHealth(6);
        setHealthRestore(0);
        setPrice(20);
        setType(Type.Minion);
        setHeroClass("Neutral");
        setRarity(Rarity.Rare);
        setDescription("Taunt , Battlecry: Summon a copy of this minion");
        setTargetNeeded(false);
        setContiniousAction(false);
        setAttackRestore(0);
        setTitle("Mech");
        setSleep(true);
        setCanBeAttacked(false);
        setAttributes(new ArrayList<Attribute>(Arrays.asList(Attribute.Taunt, Attribute.BattleCry)));
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitMobin(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }
}
