package Controller.Actions;

import Controller.Actions.CardVisitors.ActionVisitor;
import Controller.Actions.CardVisitors.BattlecryVisitor;
import Controller.Admin;
import Controller.Manager.Managers;
import Controller.Manager.PracticeManager;
import Model.Cards.*;
import Model.Enums.Attribute;
import Model.Enums.ContiniousActionCarts;
import Model.Heros.*;
import Model.Interface.Character;

import java.util.ArrayList;

import static View.Sounds.SoundAdmin.playSound;

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

    public void restoreHealth(Character target, int i) {
        target.setLife(target.getLife() + i);
        if (target.getLife() > target.getMaxLife()) {
            target.setLife(target.getMaxLife());
        }
        if (target instanceof Minion) {
            Admin.getInstance().summonedMinion((Minion) target, 1, target.getAttack(), target.getLife());
        }
    }

    private Character createTarget(int target, Managers gameManager) {
        if (target >= 10 && target < 20) {
            return gameManager.getFriendlyPlayedCards().get(target - 10);
        } else if (target >= 20 && target < 30) {
            return gameManager.getEnemyPlayedCards().get(target - 20);
        } else if (target == 1) {
            return gameManager.getFriendlyPlayerHero();
        } else if (target == 2) {
            return gameManager.getEnemyPlayerHero();
        } else {
            return null;
        }
    }

    public void playCard(String string, int i, int target, Managers gameManager) {
        Minion targeted = (Minion) createTarget(target, gameManager);
        for (Card cards : gameManager.getFriendLyHandCards()) {
            if (cards.getName().equalsIgnoreCase(string)) {
                if (gameManager.getFriendlyNotUsedMana() >= cards.getManaCost() - gameManager.getFriendlyManaDecrease()) {
                    checkContiniousAction(cards, false, gameManager);

                    cards.accept(new BattlecryVisitor(), targeted, gameManager.getFriendlyDeckCards(), gameManager.getFriendLyHandCards(),
                            gameManager.getFriendlyPlayedCards(), gameManager.getEnemyDeckCards(), gameManager.getEnemyHandCards(),
                            gameManager.getEnemyPlayedCards(), gameManager.getFriendlyPlayerHero(), gameManager.getEnemyPlayerHero());

                    if (cards instanceof Minion) {
                        playMinion((Minion) cards, i, targeted, gameManager);
                        gameManager.spendManaOnMinion(cards.getManaCost() - gameManager.getFriendlyManaDecrease(), false);
                    } else if (cards instanceof Spell) {
                        playSpell((Spell) cards, targeted, gameManager);
                        gameManager.spendManaOnSpell(cards.getManaCost() - gameManager.getFriendlyManaDecrease(), false);
                    } else if (cards instanceof Weapon) {
                        playWeapon((Weapon) cards, gameManager);
                    }
                    Admin.getInstance().updateCardUsageTime(cards.getName().toLowerCase());
                    break;
                } else {
                    playSound("mana");
                }
            }
        }
        gameManager.checkDestroyMinion();
        gameManager.checkForWinner();
    }

    private void playMinion(Minion minions, int i, Character target, Managers gameManager) {
        if (gameManager.getFriendlyPlayedCards().size() < 7) {
            playSound("minion");
            gameManager.setFriendlyNotUsedMana(gameManager.getFriendlyNotUsedMana() - (minions.getManaCost() - gameManager.getFriendlyManaDecrease()));
            minions.accept(new ActionVisitor(), target, gameManager.getFriendlyDeckCards(), gameManager.getFriendLyHandCards(),
                    gameManager.getFriendlyPlayedCards(), gameManager.getEnemyDeckCards(), gameManager.getEnemyHandCards(),
                    gameManager.getEnemyPlayedCards(), gameManager.getFriendlyPlayerHero(), gameManager.getEnemyPlayerHero());
            summonMinion(minions, i, gameManager);
            gameManager.checkDestroyMinion();
            gameManager.hunterPowerAction(minions, false);
            gameManager.faezeAction(minions, false);
            Admin.getInstance().updateGameLog(String.format("%s Played %s", gameManager.getFriendlyPlayer().getUsername(), minions.getName()));
        } else {
            playSound("error");
            Admin.getInstance().frameRender();
        }
    }

    public void summonMinion(Minion minions, int i, Managers gameManager) {
        if (gameManager.isAiTurn()) {
            ((PracticeManager) gameManager).practiceSummonMinion(minions);
        } else {
            if (gameManager.getFriendlyPlayedCards().size() < 7) {
                gameManager.shahryarAction(minions, false);
                if (minions.getAttributes() != null && (minions.getAttributes().contains(Attribute.Charge) || minions.getAttributes().contains(Attribute.Rush))) {
                    minions.setSleep(false);
                }
                if (i < 0) {
                    gameManager.getFriendlyPlayedCards().add(minions);
                } else {
                    gameManager.getFriendlyPlayedCards().add(i, minions);
                }
                gameManager.getFriendLyHandCards().remove(minions);
            }
        }
    }

    private void playSpell(Spell spell, Character target, Managers gameManager) {
        playSound("spell");
        gameManager.setFriendlyNotUsedMana(gameManager.getFriendlyNotUsedMana() - (spell.getManaCost() - gameManager.getFriendlyManaDecrease()));
        gameManager.getFriendLyHandCards().remove(spell);
        spell.accept(new ActionVisitor(), target, gameManager.getFriendlyDeckCards(), gameManager.getFriendLyHandCards(),
                gameManager.getFriendlyPlayedCards(), gameManager.getEnemyDeckCards(), gameManager.getEnemyHandCards(),
                gameManager.getEnemyPlayedCards(), gameManager.getFriendlyPlayerHero(), gameManager.getEnemyPlayerHero());

        Admin.getInstance().updateGameLog(String.format("%s Cast %s", gameManager.getFriendlyPlayer().getUsername(), spell.getName()));
    }

    private void playWeapon(Weapon weapon, Managers gameManager) {
        playSound("weapon");
        gameManager.setFriendlyNotUsedMana(gameManager.getFriendlyNotUsedMana() - (weapon.getManaCost() - gameManager.getFriendlyManaDecrease()));
        gameManager.getFriendLyHandCards().remove(weapon);
        gameManager.setFriendlyWeapon(weapon);
        Admin.getInstance().updateGameLog(String.format("%s Equiped %s", gameManager.getFriendlyPlayer().getUsername(), weapon.getName()));
    }

    private void checkContiniousAction(Card cards, boolean AI, Managers gameManager) {
        for (ContiniousActionCarts value : ContiniousActionCarts.values()) {
            if (cards.getName().equalsIgnoreCase(value.toString())) {
                gameManager.addContiniousActionCard(cards, AI);
                break;
            }
        }
    }


}
