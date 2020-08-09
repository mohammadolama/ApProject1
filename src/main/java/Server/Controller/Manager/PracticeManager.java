package Server.Controller.Manager;

import Server.Controller.Actions.ActionHandler;
import Server.Controller.Actions.CardVisitors.ActionVisitor;
import Server.Controller.Actions.CardVisitors.BattlecryVisitor;
import Server.Controller.Actions.SPVisitor.HeroPowerVisitor;
import Server.Controller.Actions.SPVisitor.SpecialPowerVisitor;
import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.MainLogic.DeckLogic;
import Server.Controller.MainLogic.JsonReaders;

import Server.Model.Cards.*;
import Server.Model.Heros.*;
import Server.Model.*;
import Server.Model.Enums.*;
import Server.Model.Interface.Character;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static Client.View.View.Sounds.SoundAdmin.playSound;

public class PracticeManager extends NormalManagers {

    public PracticeManager(Player player, InfoPassive infoPassive, ArrayList<Card> list) {
        try {
            deckReaderMode = false;
            practiceMode = true;
            this.player1 = player;
            this.player1Hero = (Hero) player.getSelectedDeck().getHero().clone();
            this.currentHero = player1Hero;
            Collections.shuffle(list);
            this.player1CardsOfPlayer = list;
            ThreePrimitiveRandom(this.player1CardsOfPlayer, "friendly");
            this.player1InfoPassive = infoPassive;
            player1InfoInitilize(infoPassive);
            practiceEnemyInit();
            player1Hero.accept(new SpecialPowerVisitor(), null, player1DeckCards, player1HandCards, player1PlayedCards, player2DeckCards, player2HandCards, player2PlayedCards, );
            player2Hero.accept(new SpecialPowerVisitor(), null, player1DeckCards, player1HandCards, player1PlayedCards, player2DeckCards, player2HandCards, player2PlayedCards, );
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    private void practiceEnemyInit() {
        Player player = JsonReaders.deckReaderPlayer("practice");
        this.player2 = player;
        player2InfoPassive = InfoPassive.practiceRandomChoice();
        player2InfoInitilize(player2InfoPassive);
        ArrayList<Card> ar1 = DeckLogic.UpdateDeck(player.getSelectedDeck().getDeck());
        Collections.shuffle(ar1);
        ThreePrimitiveRandom(ar1, "enemy");
        this.player2PlayedCards = new ArrayList<>();
        this.player2Hero = player.getSelectedDeck().getHero();
    }

    @Override
    public void endTurn(ClientHandler cl) {
        benyaminAction(false);
        AiActions(cl);
    }

    private void AiActions(ClientHandler cl) {
        new Thread(() -> {
            p2Turn = true;
            try {
                Admin.getInstance().AiTurn(true);
                Thread.sleep(300);
                checkDestroyMinion();
                final Object object = new Object();
                Admin.getInstance().RequestAct(object);
                if (!drawCard(player2DrawCardNum, null, player2DeckCards, player2HandCards, cl))
                    heroTakeDamage(player2Hero, 1);
                Thread.sleep(2000);
                wakeUp(true, cl);
                refillMana(true, cl);
                practicePlayCard(cl);
                Thread.sleep(2000);
                Admin.getInstance().frameRender();
                Thread.sleep(2000);
                choosePracticeAttack(cl);
                Thread.sleep(2000);
                practiceHeroPower();
                Admin.getInstance().AiTurn(false);
                updateGameLog(String.format("%s  EndTurn .", player2.getUsername()));
                PlayerTurn(cl);
                p2Turn = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }


    private void practicePlayCard(ClientHandler cl) {
        if (player2HandCards.size() > 0) {
            Collections.shuffle(player2HandCards);
            for (Card card : player2HandCards) {
                if (player2NotUsedMana >= card.getManaCost() - player2ManaDecrease) {
                    if ((card instanceof Weapon) || (!card.isNeedEnemyTarget() && !card.isNeedFriendlyTarget())) {
                        playCard(card, cl);
                        break;
                    }
                }
            }
        }
    }

    private void playCard(Card card, ClientHandler cl) {
        for (Card cards : player2HandCards) {
            if (card.equals(cards)) {
                if (player2NotUsedMana >= cards.getManaCost() - player2ManaDecrease) {
                    checkContiniousAction(cards, true);
                    cards.accept(new BattlecryVisitor(), null, player2DeckCards, player2HandCards, player2PlayedCards, player1DeckCards, player1HandCards, player1PlayedCards, player2Hero, player1Hero, );
                    if (cards instanceof Minion) {
                        practicePlayMinion((Minion) cards, cl);
                        spendManaOnMinion(cards.getManaCost() - player2ManaDecrease, true);
                    } else if (cards instanceof Spell) {
                        practicePlaySpell((Spell) cards);
                        spendManaOnSpell(cards.getManaCost() - player2ManaDecrease, true);
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
        if (player2PlayedCards.size() < 7) {
            if (minions.getAttributes() != null && (minions.getAttributes().contains(Attribute.Charge) || minions.getAttributes().contains(Attribute.Rush))) {
                minions.setSleep(false);
            }
            player2PlayedCards.add(minions);
            player2HandCards.remove(minions);
        }
    }

    private void practicePlayMinion(Minion minions, ClientHandler cl) {
        if (player2PlayedCards.size() < 7) {
            Admin.getInstance().playSound("minion");
            player2NotUsedMana -= minions.getManaCost() - player2ManaDecrease;
            minions.accept(new ActionVisitor(), null, player2DeckCards, player2HandCards, player2PlayedCards, player1DeckCards, player1HandCards, player1PlayedCards, player2Hero, player1Hero, );
            practiceSummonMinion(minions);
            checkDestroyMinion();
            hunterPowerAction(minions, true);
            faezeAction(minions, true, cl);
            shahryarAction(minions, true);
            updateGameLog(String.format("%s Played %s", player2.getUsername(), minions.getName().toLowerCase()));
        } else {
            Admin.getInstance().playSound("error");
            Admin.getInstance().frameRender();
        }
    }

    private void choosePracticeAttack(ClientHandler cl) {
        ArrayList<Card> list = new ArrayList<>();
        for (Card card : player2PlayedCards) {
            if (!((Minion) card).isSleep()) {
                list.add(card);
            }
        }
        if (list.size() > 0) {
            Random random = new Random();
            int index = random.nextInt(list.size());
            Minion minion = (Minion) list.get(index);
            int j = 0;
            for (Card card : player1PlayedCards) {
                if (card.getAttributes() != null && card.getAttributes().contains(Attribute.Taunt)) {
                    practiceAttack(minion, card, index, j, cl);
                    updateGameLog(String.format("%s Attacked %s", minion.getName(), card.getName()));
                    return;
                }
                j++;
            }
            j = 0;
            for (Card card : list) {
                if (((Minion) card).getDamage() >= player1Hero.getHealth()) {
                    practiceAttack((Minion) card, player1Hero, j, -1, cl);
                    updateGameLog(String.format("%s Attacked %s", minion.getName(), player1Hero.getName()));
                    return;
                }
                j++;
            }
            int chance = random.nextInt(20);
            if (player1PlayedCards.size() == 0 || chance % 3 == 0) {
                practiceAttack(minion, player1Hero, index, -1, cl);
                updateGameLog(String.format("%s Attacked %s", minion.getName(), player1Hero.getName()));
            } else {
                j = 0;
                for (Card card : player1PlayedCards) {
                    practiceAttack(minion, card, index, j, cl);
                    updateGameLog(String.format("%s Attacked %s", minion.getName(), card.getName()));
                    break;
                }
            }

        }
    }

    private void practiceAttack(Minion attacker, Character target, int i, int j, ClientHandler cl) {
        ActionHandler actionHandler = new ActionHandler();
        new Thread(() -> playSound("attack")).start();
        if (j >= 0) {
            Minion target1 = (Minion) target;
            actionHandler.Attack(attacker, target1, player1PlayedCards);
            setSleep(attacker, cl);
            Admin.getInstance().drawPracticeAttack(i, j, attacker.getDamage(), target.getAttack());
        } else {
            Hero target1 = player1Hero;
            actionHandler.Attack(attacker, target1, player1PlayedCards);
            setSleep(attacker, cl);
            Admin.getInstance().drawPracticeAttack(i, j, attacker.getDamage(), target.getAttack());
        }
    }

    private void practicePlaySpell(Spell spell) {
        Admin.getInstance().playSound("spell");
        player2NotUsedMana -= spell.getManaCost() - player2ManaDecrease;
        player2HandCards.remove(spell);
        spell.accept(new ActionVisitor(), null, player2DeckCards, player2HandCards, player2PlayedCards, player1DeckCards, player1HandCards, player1PlayedCards, player2Hero, player1Hero, );
        updateGameLog(String.format("%s Played %s", player2.getUsername(), spell.getName().toLowerCase()));
    }

    private void practicePlayWeapon(Weapon weapon) {
        Admin.getInstance().playSound("weapon");
        player2NotUsedMana -= weapon.getManaCost() - player2ManaDecrease;
        player2HandCards.remove(weapon);
        setEnemyWeapon(weapon);
        updateGameLog(String.format("%s Equiped %s", player2.getUsername(), weapon.getName()));
    }

    /*  Card Actions   */

    private void practiceHeroPower() {
        Random random = new Random();
        int chance = random.nextInt(1000);
        if (chance % 5 == 0) {
            Admin.getInstance().playSound("heropower");
            player2Hero.accept(new HeroPowerVisitor(), null, player2DeckCards, player2HandCards, player2PlayedCards, player1DeckCards, player1HandCards, player1PlayedCards, );
            updateGameLog(String.format("%s Use HeroPower .", player2.getUsername()));
            heroTakeDamage(player2Hero, 2);
        }

    }


}
