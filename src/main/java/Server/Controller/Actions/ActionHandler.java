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

    private Character createTarget(int target, Managers gameManager, ClientHandler cl) {

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

    String canBePlayed(String string, ArrayList<Card> hand, ArrayList<Card> played,
                       int notUsed, int manaDecrease) {
        for (Card cards : friendlyHandCards()) {
            if (cards.getName().equalsIgnoreCase(string)) {
                if (gameManager.getFriendlyNotUsedMana() >= cards.getManaCost() - gameManager.getFriendlyManaDecrease()) {
                    if (getCardOf(string) instanceof Minion) {
                        if (friendlyPlayedCards().size() < 7) {
                            return "ok";
                        } else {
                            playSound("error");
                            return "full";
                        }
                    } else if (getCardOf(string) instanceof Spell) {
                        return "ok";
                    } else if (getCardOf(string) instanceof Weapon) {
                        return "ok";
                    }
                    break;
                } else {
                    return "mana";
                }
            }
        }
        throw new RuntimeException();
    }


    public void playCard(String string, int i, int target, Managers gameManager,
                         ArrayList<Card> p1deck, ArrayList<Card> p1hand, ArrayList<Card> p1played,
                         ArrayList<Card> p2deck, ArrayList<Card> p2hand, ArrayList<Card> p2played,
                         Hero p1hero, Hero p2hero, boolean p2Turn, ClientHandler cl1, ClientHandler cl2) {
        Minion targeted = (Minion) createTarget(target, gameManager);
        for (Card cards : p1hand) {
            if (cards.getName().equalsIgnoreCase(string)) {
                checkContiniousAction(cards, p2Turn, gameManager);
                cards.accept(new BattlecryVisitor(), targeted, p1deck, p1hand,
                        p1played, p2deck, p2hand,
                        p2played, p1hero, p2hero, gameManager);
                if (cards instanceof Minion) {
                    playMinion((Minion) cards, i, targeted, gameManager);
                    gameManager.spendManaOnMinion(cards.getManaCost() - gameManager.getFriendlyManaDecrease(), p2Turn);
                } else if (cards instanceof Spell) {
                    playSpell((Spell) cards, targeted, gameManager);
                    gameManager.spendManaOnSpell(cards.getManaCost() - gameManager.getFriendlyManaDecrease(), p2Turn);
                } else if (cards instanceof Weapon) {
                    playWeapon((Weapon) cards, gameManager);
                }
                Admin.getInstance().updateCardUsageTime(cards.getName().toLowerCase());
                break;
            }
        }
        gameManager.checkDestroyMinion();
        gameManager.checkForWinner();
    }

    private void playMinion(Minion minions, int i, Character target, Managers gameManager) {
        gameManager.setFriendlyNotUsedMana(gameManager.getFriendlyNotUsedMana() - (minions.getManaCost() - gameManager.getFriendlyManaDecrease()));
        minions.accept(new ActionVisitor(), target, gameManager.getFriendlyDeckCards(), gameManager.getFriendLyHandCards(),
                gameManager.getFriendlyPlayedCards(), gameManager.getEnemyDeckCards(), gameManager.getEnemyHandCards(),
                gameManager.getEnemyPlayedCards(), gameManager.getFriendlyPlayerHero(), gameManager.getEnemyPlayerHero(), gameManager);
        summonMinion(minions, i, gameManager);
            gameManager.checkDestroyMinion();
            gameManager.hunterPowerAction(minions, false);
            gameManager.faezeAction(minions, false);
            Admin.getInstance().updateGameLog(String.format("%s Played %s", gameManager.getFriendlyPlayer().getUsername(), minions.getName()));
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
                gameManager.getEnemyPlayedCards(), gameManager.getFriendlyPlayerHero(), gameManager.getEnemyPlayerHero(), );

        Admin.getInstance().updateGameLog(String.format("%s Cast %s", gameManager.getFriendlyPlayer().getUsername(), spell.getName()));
    }

    private void playWeapon(Weapon weapon, Managers gameManager) {
        playSound("weapon");
        gameManager.setFriendlyNotUsedMana(gameManager.getFriendlyNotUsedMana() - (weapon.getManaCost() - gameManager.getFriendlyManaDecrease()));
        gameManager.getFriendLyHandCards().remove(weapon);
        gameManager.setPlayer1Weapon(weapon);
        Admin.getInstance().updateGameLog(String.format("%s Equiped %s", gameManager.getFriendlyPlayer().getUsername(), weapon.getName()));
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
        ActionHandler actionHandler = new ActionHandler();
        if (attacker >= 0 && target >= 0) {
            Minion attacker1 = (Minion) list1.get(attacker);
            Minion target1 = (Minion) list2.get(target);
            actionHandler.Attack(attacker1, target1, list2);
            setSleep(attacker, list1);
            cl1.notifyAttack(attacker, target, attacker1.getAttack(), target1.getAttack());
            cl2.notifyAttack(target, attacker, target1.getAttack(), attacker1.getAttack());
            managers.updateGameLog(String.format("%s Attacked %s", attacker1.getName(), target1.getName()));
        } else if (attacker >= 0) {
            Minion attacker1 = (Minion) list1.get(attacker);
            actionHandler.Attack(attacker1, hero2, list2);
            setSleep(attacker, list1);
            cl1.notifyAttack(attacker, target, attacker1.getAttack(), hero2.getAttack());
            cl2.notifyAttack(target, attacker, hero2.getAttack(), attacker1.getAttack());
            managers.updateGameLog(String.format("%s Attacked %s", attacker1.getName(), hero2.getName()));
        } else if (target >= 0) {
            Minion target1 = (Minion) list2.get(target);
            cl1.notifyAttack(attacker, target, hero1.getAttack(), target1.getAttack());
            cl2.notifyAttack(target, attacker, target1.getAttack(), hero1.getAttack());
            if (actionHandler.Attack(hero1, target1, list2)) {
                managers.updateGameLog(String.format("%s Attacked %s", hero1.getName(), target1.getName()));
                managers.updateWeapon(cl1);
            }
        } else {
            cl1.notifyAttack(attacker, target, hero1.getAttack(), hero2.getAttack());
            cl2.notifyAttack(target, attacker, hero2.getAttack(), hero1.getAttack());
            if (actionHandler.Attack(hero1, hero2, list2)) {
                managers.updateGameLog(String.format("%s Attacked %s", hero1.getName(), hero2.getName()));
                managers.updateWeapon(cl1);
            }
        }
    }

    public void setSleep(int i, ArrayList<Card> list) {
        ((Minion) list.get(i)).setSleep(true);
    }


}
