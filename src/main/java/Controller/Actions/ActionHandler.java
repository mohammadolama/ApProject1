package Controller.Actions;

import Model.Cards.Card;
import Model.Cards.Minion;
import Model.Enums.Attribute;
import Model.Heros.Hero;
import Model.Interface.Character;

import java.util.ArrayList;

public class ActionHandler {

    public void Attack(Character attacker, Character target, ArrayList<Card> enemyHand) {
        if (target instanceof Minion) {
            attackMinion(attacker, target, enemyHand);
        } else if (target instanceof Hero) {
            attackHero(attacker, target, enemyHand);
        }
    }

    private void attackMinion(Character attacker, Character target, ArrayList<Card> enemyHand) {
        int i = tauntCheckerForMinion((Minion) target, enemyHand);
        if (i == 0 || i == 1) {
            if (((Minion) target).getAttributes().contains(Attribute.DivineShield)) {
                ((Minion) target).getAttributes().remove(Attribute.DivineShield);
            } else {
                int attackerdamage = attacker.getAttack();
                int targetdamage = target.getAttack();
                target.setLife(target.getLife() - attackerdamage);
                attacker.setLife(attacker.getLife() - targetdamage);
            }
        }
    }

    private void attackHero(Character attacker, Character target, ArrayList<Card> enemyHand) {
        if (!tauntCheckerForHero(enemyHand)) {
            int attackerdamage = attacker.getAttack();
            int targetDefence = ((Hero) target).getDefence();
            if (targetDefence >= attackerdamage) {
                ((Hero) target).setDefence(targetDefence - attackerdamage);
                return;
            } else {
                attackerdamage = attackerdamage - targetDefence;
                ((Hero) target).setDefence(0);
                target.setLife(target.getLife() - attackerdamage);
            }
        }
    }

    private int tauntCheckerForMinion(Minion target, ArrayList<Card> enemyHand) {
        boolean flag = false;
        ArrayList<Card> ar = new ArrayList<>();
        for (Card card : enemyHand) {
            if (((Minion) card).getAttributes().contains(Attribute.Taunt)) {
                ar.add(card);
            }
        }
        if (ar.size() == 0) {
            return 0;
        } else {
            for (Card card : ar) {
                if (card.equals(target)) {
                    return 1;
                }
            }
            return 2;
        }
    }

    private boolean tauntCheckerForHero(ArrayList<Card> enemyHand) {
        for (Card card : enemyHand) {
            if (((Minion) card).getAttributes().contains(Attribute.Taunt)) {
                return true;
            }
        }
        return false;
    }
}
