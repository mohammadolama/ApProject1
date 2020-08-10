package Server.Controller.Actions.SPVisitor;

import Server.Controller.Actions.ActionHandler;
import Server.Controller.MainLogic.Admin;
import Server.Controller.Manager.Managers;
import Server.Model.Cards.*;
import Server.Model.Heros.*;
import Server.Model.Interface.Character;

import java.util.*;

public class HeroPowerVisitor implements PowerVisitor {
    @Override
    public void visitHunter(Hunter hunter, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> enemyDeck, ArrayList<Card> enemyHand, ArrayList<Card> enemyPlayed, Managers managers) {
        target.setLife(target.getLife() - 1);
    }

    @Override
    public void visitMage(Mage mage, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> enemyDeck, ArrayList<Card> enemyHand, ArrayList<Card> enemyPlayed, Managers managers) {
        target.setLife(target.getLife() - 1);
    }

    @Override
    public void visitRogue(Rogue rogue, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> enemyDeck, ArrayList<Card> enemyHand, ArrayList<Card> enemyPlayed, Managers managers) {
        if (rogue.getWeapon() == null) {
            for (Card card : enemyHand) {
                myHand.add(card);
                enemyHand.remove(card);
                break;
            }
        } else {
            for (Card card : enemyHand) {
                myHand.add(card);
                enemyHand.remove(card);
                break;
            }
            for (Card card : enemyDeck) {
                myHand.add(card);
                enemyDeck.remove(card);
                break;
            }
        }
    }

    @Override
    public void visitWarlock(Warlock warlock, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> enemyDeck, ArrayList<Card> enemyHand, ArrayList<Card> enemyPlayed, Managers managers) {
        Random random = new Random();
        int rand = random.nextInt(2);
        if (myPlayed.size() > 0 && myDeck.size() > 0) {
            if (rand == 0) {
                int j = random.nextInt(myPlayed.size());
                ((Minion) myPlayed.get(j)).setHealth(((Minion) myPlayed.get(j)).getHealth() + 1);
                ((Minion) myPlayed.get(j)).setDamage(((Minion) myPlayed.get(j)).getDamage() + 1);
                Admin.getInstance().summonedMinion(myPlayed.get(j), 1, myPlayed.get(j).getAttack(), myPlayed.get(j).getLife());
            } else if (rand == 1) {
                managers.drawCard(1, null, myDeck, myHand);
            }
        } else if (myPlayed.size() == 0 && myDeck.size() > 0) {
            managers.drawCard(1, null, myDeck, myHand);
        } else if (myPlayed.size() > 0 && myDeck.size() == 0) {
            int j = random.nextInt(myPlayed.size());
            ((Minion) myPlayed.get(j)).setHealth(((Minion) myPlayed.get(j)).getHealth() + 1);
            ((Minion) myPlayed.get(j)).setDamage(((Minion) myPlayed.get(j)).getDamage() + 1);
        }
    }

    @Override
    public void visitPriest(Priest priest, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> enemyDeck, ArrayList<Card> enemyHand, ArrayList<Card> enemyPlayed, Managers managers) {
        ActionHandler actionHandler = new ActionHandler();
        actionHandler.restoreHealth(target, 4);
    }
}
