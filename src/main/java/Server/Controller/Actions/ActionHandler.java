package Server.Controller.Actions;

import Server.Model.Heros.*;
import Server.Controller.Actions.CardVisitors.ActionVisitor;
import Server.Controller.Actions.CardVisitors.BattlecryVisitor;
import Server.Controller.Manager.*;
import Server.Controller.MainLogic.*;
import Server.Model.Cards.*;
import Server.Model.Enums.*;
import Server.Model.Interface.Character;


import java.util.ArrayList;

import static Client.View.View.Sounds.SoundAdmin.playSound;

public class ActionHandler {

    private Managers m;

    public ActionHandler(Managers m) {
        this.m = m;
    }

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

    public void restoreHealth(Character target, int i) {
        target.setLife(target.getLife() + i);
        if (target.getLife() > target.getMaxLife()) {
            target.setLife(target.getMaxLife());
        }
        if (target instanceof Minion) {
            m.summonedMinion((Minion) target, 1, target.getAttack(), target.getLife());
        }
    }


    private void checkContiniousAction(Card cards, boolean p2Turn, Managers gameManager) {
        for (ContiniousActionCarts value : ContiniousActionCarts.values()) {
            if (cards.getName().equalsIgnoreCase(value.toString())) {
                gameManager.addContiniousActionCard(cards, p2Turn);
                break;
            }
        }
    }

    public void Attack(int attacker, int target, ArrayList<Card> list1, ArrayList<Card> list2, Hero hero1, Hero hero2,
                       Managers managers, ClientHandler cl1, ClientHandler cl2) {
        if (attacker >= 0 && target >= 0) {
            Minion attacker1 = (Minion) list1.get(attacker);
            Minion target1 = (Minion) list2.get(target);
            Attack(attacker1, target1, list2);
            setSleep(attacker, list1);
            cl1.notifyAttack(attacker, target, attacker1.getAttack(), target1.getAttack());
            if (cl2 != null)
                cl2.notifyAttack(target, attacker, target1.getAttack(), attacker1.getAttack());
            managers.updateGameLog(String.format("%s Attacked %s", attacker1.getName(), target1.getName()));
        } else if (attacker >= 0) {
            Minion attacker1 = (Minion) list1.get(attacker);
            Attack(attacker1, hero2, list2);
            setSleep(attacker, list1);
            cl1.notifyAttack(attacker, target, attacker1.getAttack(), hero2.getAttack());
            if (cl2 != null)
                cl2.notifyAttack(target, attacker, hero2.getAttack(), attacker1.getAttack());
            managers.updateGameLog(String.format("%s Attacked %s", attacker1.getName(), hero2.getName()));
        } else if (target >= 0) {
            Minion target1 = (Minion) list2.get(target);
            cl1.notifyAttack(attacker, target, hero1.getAttack(), target1.getAttack());
            if (cl2 != null)
                cl2.notifyAttack(target, attacker, target1.getAttack(), hero1.getAttack());
            if (Attack(hero1, target1, list2)) {
                managers.updateGameLog(String.format("%s Attacked %s", hero1.getName(), target1.getName()));
                managers.updateWeapon(cl1);
            }
        } else {
            if (Attack(hero1, hero2, list2)) {
                managers.updateGameLog(String.format("%s Attacked %s", hero1.getName(), hero2.getName()));
                managers.updateWeapon(cl1);
            }
            cl1.notifyAttack(attacker, target, hero1.getAttack(), hero2.getAttack());
            if (cl2 != null)
                cl2.notifyAttack(target, attacker, hero2.getAttack(), hero1.getAttack());
        }
    }

    public void setSleep(int i, ArrayList<Card> list) {
        ((Minion) list.get(i)).setSleep(true);
    }


}
