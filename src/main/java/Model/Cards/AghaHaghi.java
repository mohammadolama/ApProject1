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

@JsonTypeName("aghahaghi")
public class AghaHaghi extends Minion {

    public AghaHaghi() {
        setName("AghaHaghi");
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
        setAttributes(new ArrayList<Attribute>(Arrays.asList(Attribute.Rush)));
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitAghaHaghi(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }
}
