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

@JsonTypeName("khashayar")
public class Khashayar extends Minion {

    public Khashayar() {
        setName("Khashayar");
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
        setTargetNeeded(false);
        setContiniousAction(false);
        setAttackRestore(0);
        setTitle(null);
        setSleep(true);
        setCanBeAttacked(false);
        setAttributes(new ArrayList<Attribute>(Arrays.asList(Attribute.BattleCry)));
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy) {
        visitor.visitKhashayer(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy);
    }
}
