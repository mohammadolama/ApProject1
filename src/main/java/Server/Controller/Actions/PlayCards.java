package Server.Controller.Actions;

import Server.Controller.Actions.CardVisitors.ActionVisitor;
import Server.Controller.Actions.CardVisitors.BattlecryVisitor;
import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Model.Cards.Card;
import Server.Model.Cards.Minion;
import Server.Model.Cards.Spell;
import Server.Model.Cards.Weapon;
import Server.Model.Enums.Attribute;
import Server.Model.Heros.Hero;
import Server.Model.Interface.Character;

import java.util.ArrayList;

import static Server.Model.Cards.Card.getCardOf;

public class PlayCards {

    private Managers m;

    public PlayCards(Managers m) {
        this.m = m;
    }

    public void playCard(String string, int i, int target, ClientHandler cl) {
        if (cl.equals(m.getCl1())) {
            playCard(string, i, target, m, m.getPlayer1DeckCards(), m.getPlayer1HandCards(), m.getPlayer1PlayedCards(),
                    m.getPlayer2DeckCards(), m.getPlayer2HandCards(), m.getPlayer2PlayedCards(), m.getPlayer1Hero(), m.getPlayer2Hero(),
                    false, m.getCl1(), m.getCl2());
        } else {
            playCard(string, i, target, m, m.getPlayer2DeckCards(), m.getPlayer2HandCards(), m.getPlayer2PlayedCards(),
                    m.getPlayer1DeckCards(), m.getPlayer1HandCards(), m.getPlayer1PlayedCards(), m.getPlayer2Hero(), m.getPlayer1Hero(),
                    true, m.getCl2(), m.getCl1());
        }
    }

    void playCard(String string, int i, int target, Managers gameManager,
                  ArrayList<Card> p1deck, ArrayList<Card> p1hand, ArrayList<Card> p1played,
                  ArrayList<Card> p2deck, ArrayList<Card> p2hand, ArrayList<Card> p2played,
                  Hero p1hero, Hero p2hero, boolean p2Turn, ClientHandler cl1, ClientHandler cl2) {

        TargetCraetor tc = new TargetCraetor(m);
        Minion targeted = (Minion) tc.createTarget(target, cl1);

        for (Card cards : p1hand) {
            if (cards.getName().equalsIgnoreCase(string)) {
                m.checkContiniousAction(cards, p2Turn, gameManager);
                cards.accept(new BattlecryVisitor(), targeted, p1deck, p1hand,
                        p1played, p2deck, p2hand,
                        p2played, p1hero, p2hero, gameManager);
                if (cards instanceof Minion) {
                    playMinion((Minion) cards, i, targeted, gameManager, cl1);
                } else if (cards instanceof Spell) {
                    playSpell((Spell) cards, targeted, gameManager, cl1);

                } else if (cards instanceof Weapon) {
                    playWeapon((Weapon) cards, gameManager, cl1);
                }
                Admin.getInstance().updateCardUsageTime(cards.getName().toLowerCase(), cl1.getPlayer());
                break;
            }
        }
        gameManager.checkDestroyMinion();
        gameManager.checkForWinner();
    }

    private void playMinion(Minion minions, int i, Character target, Managers gameManager, ClientHandler cl) {
        if (cl.equals(m.getCl1())) {
            m.spendManaOnMinion(minions.getManaCost() - m.getPlayer1ManaDecrease(), false);
            m.setPlayer1NotUsedMana(m.getPlayer1NotUsedMana() - (minions.getManaCost() - m.getPlayer1ManaDecrease()));
            minions.accept(new ActionVisitor(), target, m.getPlayer1DeckCards(), m.getPlayer1HandCards(),
                    m.getPlayer1PlayedCards(), m.getPlayer2DeckCards(), m.getPlayer2HandCards(),
                    m.getPlayer2PlayedCards(), m.getPlayer1Hero(), m.getPlayer2Hero(), gameManager);
            summonMinion(minions, i, gameManager, cl);
            m.checkDestroyMinion();
            m.hunterPowerAction(minions, false);
            m.faezeAction(minions, false, cl);
            m.updateGameLog(String.format("%s Played %s", m.getPlayer1().getUsername(), minions.getName()));
        } else {
            m.spendManaOnMinion(minions.getManaCost() - m.getPlayer2ManaDecrease(), true);
            m.setPlayer1NotUsedMana(m.getPlayer2NotUsedMana() - (minions.getManaCost() - m.getPlayer2ManaDecrease()));
            minions.accept(new ActionVisitor(), target, m.getPlayer2DeckCards(), m.getPlayer2HandCards(),
                    m.getPlayer2PlayedCards(), m.getPlayer1DeckCards(), m.getPlayer1HandCards(),
                    m.getPlayer1PlayedCards(), m.getPlayer2Hero(), m.getPlayer1Hero(), gameManager);
            summonMinion(minions, i, gameManager, cl);
            m.checkDestroyMinion();
            m.hunterPowerAction(minions, true);
            m.faezeAction(minions, true, cl);
            m.updateGameLog(String.format("%s Played %s", m.getPlayer2().getUsername(), minions.getName()));
        }
    }

    public void summonMinion(Minion minions, int i, Managers gameManager, ClientHandler cl) {
        if (cl.equals(m.getCl2())) {
            if (m.getPlayer2PlayedCards().size() < 7) {
                gameManager.shahryarAction(minions, false);
                if (minions.getAttributes() != null && (minions.getAttributes().contains(Attribute.Charge) || minions.getAttributes().contains(Attribute.Rush))) {
                    minions.setSleep(false);
                }
                if (i < 0) {
                    m.getPlayer2PlayedCards().add(minions);
                } else {
                    m.getPlayer2PlayedCards().add(i, minions);
                }
                m.getPlayer2HandCards().remove(minions);
            }
        } else {
            if (m.getPlayer1PlayedCards().size() < 7) {
                gameManager.shahryarAction(minions, false);
                if (minions.getAttributes() != null && (minions.getAttributes().contains(Attribute.Charge) || minions.getAttributes().contains(Attribute.Rush))) {
                    minions.setSleep(false);
                }
                if (i < 0) {
                    m.getPlayer1PlayedCards().add(minions);
                } else {
                    m.getPlayer1PlayedCards().add(i, minions);
                }
                m.getPlayer1HandCards().remove(minions);
            }
        }
    }

    private void playSpell(Spell spell, Character target, Managers gameManager, ClientHandler cl) {
        m.spendManaOnSpell(spell.getManaCost() - m.getPlayer1ManaDecrease(), cl);
        if (cl.equals(m.getCl1())) {
            m.setPlayer1NotUsedMana(m.getPlayer1NotUsedMana() - (spell.getManaCost() - m.getPlayer1ManaDecrease()));
            m.getPlayer1HandCards().remove(spell);
            spell.accept(new ActionVisitor(), target, m.getPlayer1DeckCards(), m.getPlayer1HandCards(),
                    m.getPlayer1PlayedCards(), m.getPlayer2DeckCards(), m.getPlayer2HandCards(),
                    m.getPlayer2PlayedCards(), m.getPlayer1Hero(), m.getPlayer2Hero(), gameManager);
            m.updateGameLog(String.format("%s Cast %s", m.getPlayer1().getUsername(), spell.getName()));
        } else {
            m.setPlayer2NotUsedMana(m.getPlayer2NotUsedMana() - (spell.getManaCost() - m.getPlayer2ManaDecrease()));
            m.getPlayer2HandCards().remove(spell);
            spell.accept(new ActionVisitor(), target, m.getPlayer2DeckCards(), m.getPlayer2HandCards(),
                    m.getPlayer2PlayedCards(), m.getPlayer1DeckCards(), m.getPlayer1HandCards(),
                    m.getPlayer1PlayedCards(), m.getPlayer2Hero(), m.getPlayer1Hero(), gameManager);
            m.updateGameLog(String.format("%s Cast %s", m.getPlayer2().getUsername(), spell.getName()));
        }
    }

    private void playWeapon(Weapon weapon, Managers gameManager, ClientHandler cl) {
        if (cl.equals(m.getCl1())) {
            m.setPlayer1NotUsedMana(m.getPlayer1NotUsedMana() - (weapon.getManaCost() - m.getPlayer1ManaDecrease()));
            m.getPlayer1HandCards().remove(weapon);
            m.updateGameLog(String.format("%s Equiped %s", m.getPlayer1().getUsername(), weapon.getName()));
        } else {
            m.setPlayer2NotUsedMana(m.getPlayer2NotUsedMana() - (weapon.getManaCost() - m.getPlayer2ManaDecrease()));
            m.getPlayer2HandCards().remove(weapon);
            m.updateGameLog(String.format("%s Equiped %s", m.getPlayer2().getUsername(), weapon.getName()));
        }
        m.setPlayerWeapon(weapon, cl);
    }

    public String canBePlayed(String string, ArrayList<Card> hand, ArrayList<Card> played,
                              int notUsed, int manaDecrease) {
        for (Card cards : hand) {
            if (cards.getName().equalsIgnoreCase(string)) {
                if (notUsed >= cards.getManaCost() - manaDecrease) {
                    if (getCardOf(string) instanceof Minion) {
                        if (played.size() < 7) {
                            return "okminion";
                        } else {
                            return "full";
                        }
                    } else if (getCardOf(string) instanceof Spell) {
                        return "okspell";
                    } else if (getCardOf(string) instanceof Weapon) {
                        return "okweapon";
                    }
                    break;
                } else {
                    return "mana";
                }
            }
        }
        throw new RuntimeException();
    }

}
