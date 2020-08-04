package Server.Controller.Actions.SPVisitor;

import Server.Model.Heros.*;
import Server.Model.Cards.*;
import Server.Model.Interface.Character;
import Server.Model.Enums.*;

import java.util.ArrayList;

public class SpecialPowerVisitor implements PowerVisitor {
    @Override
    public void visitHunter(Hunter hunter, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> enemyDeck, ArrayList<Card> enemyHand, ArrayList<Card> enemyPlayed) {
        for (Card card : myDeck) {
            if (card instanceof Minion) {
                if (!card.getAttributes().contains(Attribute.Rush)) {
                    card.getAttributes().add(Attribute.Rush);
                }
            }
        }
    }

    @Override
    public void visitMage(Mage mage, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> enemyDeck, ArrayList<Card> enemyHand, ArrayList<Card> enemyPlayed) {
        for (Card card : myDeck) {
            if (card instanceof Spell) {
                card.setManaCost(card.getManaCost() - 2);
                if (card.getManaCost() < 0) {
                    card.setManaCost(0);
                }
            }
        }
    }

    @Override
    public void visitRogue(Rogue rogue, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> enemyDeck, ArrayList<Card> enemyHand, ArrayList<Card> enemyPlayed) {
        for (Card card : myDeck) {
            if (!card.getHeroClass().equalsIgnoreCase("Neutral") && !card.getHeroClass().equalsIgnoreCase("Rogue")) {
                card.setManaCost(card.getManaCost() - 2);
                if (card.getManaCost() < 7) {
                    card.setManaCost(0);
                }
            }
        }
    }

    @Override
    public void visitWarlock(Warlock warlock, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> enemyDeck, ArrayList<Card> enemyHand, ArrayList<Card> enemyPlayed) {

    }

    @Override
    public void visitPriest(Priest priest, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> enemyDeck, ArrayList<Card> enemyHand, ArrayList<Card> enemyPlayed) {
        for (Card card : myDeck) {
            if (card.getName().equalsIgnoreCase("cookie") && card.getName().equalsIgnoreCase("holylight")) {
                card.setHealthRestore(card.getHealthRestore() * 2);
            }
        }
    }
}
