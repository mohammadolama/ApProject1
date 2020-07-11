package Controller.Actions;

import Model.Cards.*;
import Model.Enums.Attribute;
import Model.Heros.*;
import Model.Interface.Character;

import java.util.ArrayList;

public class ActionHandler {

    public boolean Attack(Character attacker, Character target, ArrayList<Card> enemyHand) {
        if (target instanceof Minion) {
            return attackMinion(attacker, target, enemyHand);
        } else {
            return attackHero(attacker, target, enemyHand);
        }
    }

    private boolean attackMinion(Character attacker, Character target, ArrayList<Card> enemyHand) {
        int i = tauntCheckerForMinion((Minion) target, enemyHand);
        if (i == 0 || i == 1) {
            int attackerdamage = attacker.getAttack();
            int targetdamage = target.getAttack();
            if (((Minion) target).getAttributes().contains(Attribute.DivineShield)) {
                ((Minion) target).getAttributes().remove(Attribute.DivineShield);

            } else {
                target.setLife(target.getLife() - attackerdamage);
            }
            if (attacker instanceof Minion && ((Minion) attacker).getAttributes().contains(Attribute.DivineShield)) {
                ((Minion) attacker).getAttributes().remove(Attribute.DivineShield);
            } else {
                attacker.setLife(attacker.getLife() - targetdamage);
            }
            return true;
        }
        return false;
    }

    public void attackMinion2(Character attacker, Character target) {
        int attackerdamage = attacker.getAttack();
        int targetdamage = target.getAttack();
        if (((Minion) target).getAttributes().contains(Attribute.DivineShield)) {
            ((Minion) target).getAttributes().remove(Attribute.DivineShield);

        } else {
            target.setLife(target.getLife() - attackerdamage);
        }
        if (attacker instanceof Minion && ((Minion) attacker).getAttributes().contains(Attribute.DivineShield)) {
            ((Minion) attacker).getAttributes().remove(Attribute.DivineShield);
        } else {
            attacker.setLife(attacker.getLife() - targetdamage);
        }
    }


    private boolean attackHero(Character attacker, Character target, ArrayList<Card> enemyHand) {
        if (!tauntCheckerForHero(enemyHand)) {
            int attackerDamage = attacker.getAttack();
            int targetDefence = ((Hero) target).getDefence();
            if (targetDefence >= attackerDamage) {
                ((Hero) target).setDefence(targetDefence - attackerDamage);
            } else {
                attackerDamage = attackerDamage - targetDefence;
                ((Hero) target).setDefence(0);
                target.setLife(target.getLife() - attackerDamage);
            }
            return true;
        }
        return false;
    }

    private int tauntCheckerForMinion(Minion target, ArrayList<Card> enemyHand) {
        ArrayList<Card> ar = new ArrayList<>();
        for (Card card : enemyHand) {
            if (card.getAttributes().contains(Attribute.Taunt)) {
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
            if (card.getAttributes().contains(Attribute.Taunt)) {
                return true;
            }
        }
        return false;
    }


}
