package Controller.Manager;

import Controller.Actions.ActionHandler;
import Controller.Actions.CardVisitors.ActionVisitor;
import Controller.Actions.CardVisitors.BattlecryVisitor;
import Controller.Actions.SPVisitor.HeroPowerVisitor;
import Controller.Actions.SPVisitor.SpecialPowerVisitor;
import Controller.Admin;
import MainLogic.DeckLogic;
import MainLogic.JsonReaders;
import Model.Cards.Card;
import Model.Cards.Minion;
import Model.Cards.Spell;
import Model.Cards.Weapon;
import Model.Enums.Attribute;
import Model.Heros.Hero;
import Model.InfoPassive;
import Model.Interface.Character;
import Model.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static View.Sounds.SoundAdmin.playSound;

public class PracticeManager extends Managers {

    public PracticeManager(Player player, InfoPassive infoPassive, ArrayList<Card> list) {
        try {
            deckReaderMode = false;
            practiceMode = true;
            this.friendlyPlayer = player;
            this.friendlyPlayerHero = (Hero) player.getSelectedDeck().getHero().clone();
            this.playerHero = friendlyPlayerHero;
            Collections.shuffle(list);
            this.friendlyCardsOfPlayer = list;
            ThreePrimitiveRandom(this.friendlyCardsOfPlayer, "friendly");
            this.friendlyInfoPassive = infoPassive;
            friendlyInfoInitilize(infoPassive);
            practiceEnemyInit();
            friendlyPlayerHero.accept(new SpecialPowerVisitor(), null, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyDeckCards, enemyHandCards, enemyPlayedCards);
            enemyPlayerHero.accept(new SpecialPowerVisitor(), null, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyDeckCards, enemyHandCards, enemyPlayedCards);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    private void practiceEnemyInit() {
        Player player = JsonReaders.deckReaderPlayer("practice");
        this.enemyPlayer = player;
        enemyInfoPassive = InfoPassive.practiceRandomChoice();
        enemyInfoInitilize(enemyInfoPassive);
        ArrayList<Card> ar1 = DeckLogic.UpdateDeck(player.getSelectedDeck().getDeck());
        Collections.shuffle(ar1);
        ThreePrimitiveRandom(ar1, "enemy");
        this.enemyPlayedCards = new ArrayList<>();
        this.enemyPlayerHero = player.getSelectedDeck().getHero();
    }

    @Override
    public void endTurn() {
        benyaminAction(false);
        AiActions();
    }

    private void AiActions() {
        new Thread(() -> {
            AiTurn = true;
            try {
                Admin.getInstance().AiTurn(true);
                Thread.sleep(300);
                checkDestroyMinion();
                final Object object = new Object();
                Admin.getInstance().RequestAct(object);
                if (!drawCard(enemyDrawCardNum, null, enemyDeckCards, enemyHandCards))
                    heroTakeDamage(enemyPlayerHero, 1);
                Thread.sleep(2000);
                wakeUp(true);
                refillMana(true);
                practicePlayCard();
                Thread.sleep(2000);
                Admin.getInstance().frameRender();
                Thread.sleep(2000);
                choosePracticeAttack();
                Thread.sleep(2000);
                practiceHeroPower();
                Admin.getInstance().AiTurn(false);
                updateGameLog(String.format("%s  EndTurn .", enemyPlayer.getUsername()));
                PlayerTurn();
                AiTurn = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }


    private void practicePlayCard() {
        if (enemyHandCards.size() > 0) {
            Collections.shuffle(enemyHandCards);
            for (Card card : enemyHandCards) {
                if (enemyNotUsedMana >= card.getManaCost() - enemyManaDecrease) {
                    if ((card instanceof Weapon) || (!card.isNeedEnemyTarget() && !card.isNeedFriendlyTarget())) {
                        playCard(card);
                        break;
                    }
                }
            }
        }
    }

    private void playCard(Card card) {
        for (Card cards : enemyHandCards) {
            if (card.equals(cards)) {
                if (enemyNotUsedMana >= cards.getManaCost() - enemyManaDecrease) {
                    checkContiniousAction(cards, true);
                    cards.accept(new BattlecryVisitor(), null, enemyDeckCards, enemyHandCards, enemyPlayedCards, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyPlayerHero, friendlyPlayerHero);
                    if (cards instanceof Minion) {
                        practicePlayMinion((Minion) cards);
                        spendManaOnMinion(cards.getManaCost() - enemyManaDecrease, true);
                    } else if (cards instanceof Spell) {
                        practicePlaySpell((Spell) cards);
                        spendManaOnSpell(cards.getManaCost() - enemyManaDecrease, true);
                    } else if (cards instanceof Weapon) {
                        practicePlayWeapon((Weapon) cards);
                    }
                    Admin.getInstance().summonedMinion(card, 0, 0, 0);
                    break;
                } else {
                    Admin.getInstance().playSound("mana");
                }
            }
        }
        checkDestroyMinion();
        checkForWinner();
    }


    public void practiceSummonMinion(Minion minions) {
        if (enemyPlayedCards.size() < 7) {
            if (minions.getAttributes() != null && (minions.getAttributes().contains(Attribute.Charge) || minions.getAttributes().contains(Attribute.Rush))) {
                minions.setSleep(false);
            }
            enemyPlayedCards.add(minions);
            enemyHandCards.remove(minions);
        }
    }

    private void practicePlayMinion(Minion minions) {
        if (enemyPlayedCards.size() < 7) {
            Admin.getInstance().playSound("minion");
            enemyNotUsedMana -= minions.getManaCost() - enemyManaDecrease;
            minions.accept(new ActionVisitor(), null, enemyDeckCards, enemyHandCards, enemyPlayedCards, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyPlayerHero, friendlyPlayerHero);
            practiceSummonMinion(minions);
            checkDestroyMinion();
            hunterPowerAction(minions, true);
            faezeAction(minions, true);
            shahryarAction(minions, true);
            updateGameLog(String.format("%s Played %s", enemyPlayer.getUsername(), minions.getName().toLowerCase()));
        } else {
            Admin.getInstance().playSound("error");
            Admin.getInstance().frameRender();
        }
    }

    private void choosePracticeAttack() {
        ArrayList<Card> list = new ArrayList<>();
        for (Card card : enemyPlayedCards) {
            if (!((Minion) card).isSleep()) {
                list.add(card);
            }
        }
        if (list.size() > 0) {
            Random random = new Random();
            int index = random.nextInt(list.size());
            Minion minion = (Minion) list.get(index);
            int j = 0;
            for (Card card : friendlyPlayedCards) {
                if (card.getAttributes() != null && card.getAttributes().contains(Attribute.Taunt)) {
                    practiceAttack(minion, card, index, j);
                    updateGameLog(String.format("%s Attacked %s", minion.getName(), card.getName()));
                    return;
                }
                j++;
            }
            j = 0;
            for (Card card : list) {
                if (((Minion) card).getDamage() >= friendlyPlayerHero.getHealth()) {
                    practiceAttack((Minion) card, friendlyPlayerHero, j, -1);
                    updateGameLog(String.format("%s Attacked %s", minion.getName(), friendlyPlayerHero.getName()));
                    return;
                }
                j++;
            }
            int chance = random.nextInt(20);
            if (friendlyPlayedCards.size() == 0 || chance % 3 == 0) {
                practiceAttack(minion, friendlyPlayerHero, index, -1);
                updateGameLog(String.format("%s Attacked %s", minion.getName(), friendlyPlayerHero.getName()));
            } else {
                j = 0;
                for (Card card : friendlyPlayedCards) {
                    practiceAttack(minion, card, index, j);
                    updateGameLog(String.format("%s Attacked %s", minion.getName(), card.getName()));
                    break;
                }
            }

        }
    }

    private void practiceAttack(Minion attacker, Character target, int i, int j) {
        ActionHandler actionHandler = new ActionHandler();
        new Thread(() -> playSound("attack")).start();
        if (j >= 0) {
            Minion target1 = (Minion) target;
            actionHandler.Attack(attacker, target1, friendlyPlayedCards);
            setSleep(attacker);
            Admin.getInstance().drawPracticeAttack(i, j, attacker.getDamage(), target.getAttack());
        } else {
            Hero target1 = friendlyPlayerHero;
            actionHandler.Attack(attacker, target1, friendlyPlayedCards);
            setSleep(attacker);
            Admin.getInstance().drawPracticeAttack(i, j, attacker.getDamage(), target.getAttack());
        }
    }

    private void practicePlaySpell(Spell spell) {
        Admin.getInstance().playSound("spell");
        enemyNotUsedMana -= spell.getManaCost() - enemyManaDecrease;
        enemyHandCards.remove(spell);
        spell.accept(new ActionVisitor(), null, enemyDeckCards, enemyHandCards, enemyPlayedCards, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyPlayerHero, friendlyPlayerHero);
        updateGameLog(String.format("%s Played %s", enemyPlayer.getUsername(), spell.getName().toLowerCase()));
    }

    private void practicePlayWeapon(Weapon weapon) {
        Admin.getInstance().playSound("weapon");
        enemyNotUsedMana -= weapon.getManaCost() - enemyManaDecrease;
        enemyHandCards.remove(weapon);
        setEnemyWeapon(weapon);
        updateGameLog(String.format("%s Equiped %s", enemyPlayer.getUsername(), weapon.getName()));
    }

    /*  Card Actions   */

    private void practiceHeroPower() {
        Random random = new Random();
        int chance = random.nextInt(1000);
        if (chance % 5 == 0) {
            Admin.getInstance().playSound("heropower");
            enemyPlayerHero.accept(new HeroPowerVisitor(), null, enemyDeckCards, enemyHandCards, enemyPlayedCards, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards);
            updateGameLog(String.format("%s Use HeroPower .", enemyPlayer.getUsername()));
            heroTakeDamage(enemyPlayerHero, 2);
        }

    }


}
