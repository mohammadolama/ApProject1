package Server.Controller.Actions.SPVisitor;

import Server.Model.Cards.*;
import Server.Model.Heros.*;
import Server.Model.Interface.Character;

import java.util.ArrayList;

public interface PowerVisitor {

    void visitHunter(Hunter hunter, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> enemyDeck, ArrayList<Card> enemyHand, ArrayList<Card> enemyPlayed);

    void visitMage(Mage mage, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> enemyDeck, ArrayList<Card> enemyHand, ArrayList<Card> enemyPlayed);

    void visitRogue(Rogue rogue, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> enemyDeck, ArrayList<Card> enemyHand, ArrayList<Card> enemyPlayed);

    void visitWarlock(Warlock warlock, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> enemyDeck, ArrayList<Card> enemyHand, ArrayList<Card> enemyPlayed);

    void visitPriest(Priest priest, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> enemyDeck, ArrayList<Card> enemyHand, ArrayList<Card> enemyPlayed);
}
