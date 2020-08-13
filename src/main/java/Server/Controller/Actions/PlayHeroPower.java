package Server.Controller.Actions;

import Server.Controller.Actions.SPVisitor.HeroPowerVisitor;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Model.Heros.Hero;
import Server.Model.Heros.Hunter;
import Server.Model.Heros.Warlock;
import Server.Model.Interface.Character;

public class PlayHeroPower {
    private Managers m;

    public PlayHeroPower(Managers m) {
        this.m = m;
    }

    private int heroPowerTargetNeeded(ClientHandler cl) {
        Hero hero;
        if (cl.equals(m.getCl1())) {
            hero = m.getPlayer1Hero();
        } else {
            hero = m.getPlayer2Hero();
        }
        if (hero.isPowerNeedFriendlyTarget()) {
            return 1;
        } else if (hero.isPowerNeedEnemyTarget()) {
            return 2;
        }
        return 0;
    }

    public boolean heroPower(int target, ClientHandler cl) {
        TargetCraetor tc = new TargetCraetor(m);
        Character tar = tc.createTarget(target, cl);
        return playHeroPower(tar, cl);
    }


    private boolean playHeroPower(Character target, ClientHandler cl) {
        if (cl.equals(m.getCl1())) {
            return player1HeroPower(target);
        } else {
            return player2HeroPower(target);
        }
    }

    private boolean player1HeroPower(Character target) {
        if (m.getPlayer1Hero() instanceof Warlock) {
            m.getPlayer1Hero().accept(new HeroPowerVisitor(), target, m.getPlayer1DeckCards(), m.getPlayer1HandCards(),
                    m.getPlayer1PlayedCards(), m.getPlayer2DeckCards(), m.getPlayer2HandCards(),
                    m.getPlayer2PlayedCards(), m);
            m.heroTakeDamage(m.getPlayer1Hero(), 2);
            m.setPlayer1HeroPowerUsageTime(m.getPlayer1HeroPowerUsageTime() - 1);
            m.updateGameLog(m.getPlayer1().getUsername() + " Use HeroPower");
            m.checkForWinner();
            return true;
        } else {
            if (m.getPlayer1NotUsedMana() >= (m.getPlayer1Hero().getHeroPowerManaCost() - m.getPlayer1PowerManaDecrease())) {
                m.getPlayer1Hero().accept(new HeroPowerVisitor(), target, m.getPlayer1DeckCards(), m.getPlayer1HandCards(),
                        m.getPlayer1PlayedCards(), m.getPlayer2DeckCards(), m.getPlayer2HandCards(),
                        m.getPlayer2PlayedCards(), m);
                m.setPlayer1NotUsedMana(m.getPlayer1NotUsedMana() - (m.getPlayer1Hero().getHeroPowerManaCost() - m.getPlayer1PowerManaDecrease()));
                m.setPlayer1HeroPowerUsageTime(m.getPlayer1HeroPowerUsageTime() - 1);
                m.updateGameLog(m.getPlayer1().getUsername() + " Use HeroPower");
                m.checkForWinner();
                return true;
            }
            return false;
        }
    }

    private boolean player2HeroPower(Character target) {
        if (m.getPlayer2Hero() instanceof Warlock) {
            m.getPlayer2Hero().accept(new HeroPowerVisitor(), target, m.getPlayer2DeckCards(), m.getPlayer2HandCards(),
                    m.getPlayer2PlayedCards(), m.getPlayer1DeckCards(), m.getPlayer1HandCards(),
                    m.getPlayer1PlayedCards(), m);
            m.heroTakeDamage(m.getPlayer2Hero(), 2);
            m.setPlayer2HeroPowerUsageTime(m.getPlayer2HeroPowerUsageTime() - 1);
            m.updateGameLog(m.getPlayer2().getUsername() + " Use HeroPower");
            m.checkForWinner();
            return true;
        } else {
            if (m.getPlayer2NotUsedMana() >= (m.getPlayer2Hero().getHeroPowerManaCost() - m.getPlayer2PowerManaDecrease())) {
                m.getPlayer2Hero().accept(new HeroPowerVisitor(), target, m.getPlayer2DeckCards(), m.getPlayer2HandCards(),
                        m.getPlayer2PlayedCards(), m.getPlayer1DeckCards(), m.getPlayer1HandCards(),
                        m.getPlayer1PlayedCards(), m);
                m.setPlayer2NotUsedMana(m.getPlayer2NotUsedMana() - (m.getPlayer2Hero().getHeroPowerManaCost() - m.getPlayer2PowerManaDecrease()));
                m.setPlayer2HeroPowerUsageTime(m.getPlayer2HeroPowerUsageTime() - 1);
                m.updateGameLog(m.getPlayer2().getUsername() + " Use HeroPower");
                m.checkForWinner();
                return true;
            }
            return false;
        }
    }

    public int heroPowerCanBePlayed(ClientHandler cl) {
        Hero hero;
        int i;
        if (cl.equals(m.getCl1())) {
            hero = m.getPlayer1Hero();
            i = m.getPlayer1HeroPowerUsageTime();
        } else {
            hero = m.getPlayer2Hero();
            i = m.getPlayer2HeroPowerUsageTime();
        }
        if (!(hero instanceof Hunter)) {
            if (i > 0) {
                if (heroPowerTargetNeeded(cl) > 0) {
                    if (heroPowerTargetNeeded(cl) == 1) {
                        return 1;
                    } else if (heroPowerTargetNeeded(cl) == 2) {
                        return 2;
                    }
                    return 0;
                } else {
                    return 3;
                }
            } else {
                return 0;
            }
        }
        return 0;
    }
}
