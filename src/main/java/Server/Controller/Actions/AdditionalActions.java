package Server.Controller.Actions;

import Server.Controller.Actions.CardVisitors.ActionVisitor;
import Server.Controller.Actions.SPVisitor.HeroPowerVisitor;
import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Model.Cards.Benyamin;
import Server.Model.Cards.Card;
import Server.Model.Cards.Minion;
import Server.Model.Heros.Hunter;

public class AdditionalActions {
    private Managers m;

    public AdditionalActions(Managers m) {
        this.m = m;
    }

    public void hunterPowerAction(Minion minion, boolean p2Turn) {
        if (p2Turn) {
            if (m.getPlayer1Hero() instanceof Hunter) {
                m.getPlayer1Hero().accept(new HeroPowerVisitor(), minion, m.getPlayer2DeckCards(), m.getPlayer2HandCards(),
                        m.getPlayer2PlayedCards(), m.getPlayer1DeckCards(), m.getPlayer1HandCards(),
                        m.getPlayer1PlayedCards(), m);

                m.summonedMinion(minion, 1, minion.getDamage(), minion.getHealth());
            }
        } else {
            if (m.getPlayer2Hero() instanceof Hunter) {
                m.getPlayer2Hero().accept(new HeroPowerVisitor(), minion, m.getPlayer1DeckCards(), m.getPlayer1HandCards(),
                        m.getPlayer1PlayedCards(), m.getPlayer2DeckCards(), m.getPlayer2HandCards(),
                        m.getPlayer2PlayedCards(), m);
                m.summonedMinion(minion, 1, minion.getDamage(), minion.getHealth());
            }
        }
    }

    public void matinAction(boolean p2Turn) {
        if (p2Turn) {
            for (Card card : m.getPlayer2PlayedCards()) {
                if (card.getName().equalsIgnoreCase("matin")) {
                    card.accept(new ActionVisitor(), null, m.getPlayer2DeckCards(), m.getPlayer2HandCards(),
                            m.getPlayer2PlayedCards(), m.getPlayer1DeckCards(), m.getPlayer1HandCards(),
                            m.getPlayer1PlayedCards(), m.getPlayer2Hero(), m.getPlayer1Hero(), m);
                }
            }

        } else {
            for (Card card : m.getPlayer1PlayedCards()) {
                if (card.getName().equalsIgnoreCase("matin")) {
                    card.accept(new ActionVisitor(), null, m.getPlayer1DeckCards(), m.getPlayer1HandCards(),
                            m.getPlayer1PlayedCards(), m.getPlayer2DeckCards(), m.getPlayer2HandCards(),
                            m.getPlayer2PlayedCards(), m.getPlayer1Hero(), m.getPlayer2Hero(), m);
                }
            }
        }
    }

    public void benyaminAction(boolean p2Turn) {
        if (p2Turn) {
            for (Card card : m.getPlayer2PlayedCards()) {
                if (card.getName().equalsIgnoreCase("benyamin")) {
                    card.accept(new ActionVisitor(), new Benyamin(), m.getPlayer2DeckCards(), m.getPlayer2HandCards(),
                            m.getPlayer2PlayedCards(), m.getPlayer1DeckCards(), m.getPlayer1HandCards(),
                            m.getPlayer1PlayedCards(), m.getPlayer2Hero(), m.getPlayer1Hero(), m);
                }
            }
        } else {
            for (Card card : m.getPlayer1PlayedCards()) {
                if (card.getName().equalsIgnoreCase("benyamin")) {
                    card.accept(new ActionVisitor(), new Benyamin(), m.getPlayer1DeckCards(), m.getPlayer1HandCards(),
                            m.getPlayer1PlayedCards(), m.getPlayer2DeckCards(), m.getPlayer2HandCards(),
                            m.getPlayer2PlayedCards(), m.getPlayer1Hero(), m.getPlayer2Hero(), m);
                }
            }
        }
    }

    public void shahryarAction(Minion minion, boolean p2Turn) {
        if (p2Turn) {
            for (Card card : m.getPlayer2PlayedCards()) {
                if (card.getName().equalsIgnoreCase("shahryar")) {
                    card.accept(new ActionVisitor(), minion, m.getPlayer2DeckCards(), m.getPlayer2HandCards(),
                            m.getPlayer2PlayedCards(), m.getPlayer1DeckCards(), m.getPlayer1HandCards(),
                            m.getPlayer1PlayedCards(), m.getPlayer2Hero(), m.getPlayer1Hero(), m);
                }
            }
        } else {
            for (Card card : m.getPlayer1PlayedCards()) {
                if (card.getName().equalsIgnoreCase("shahryar")) {
                    card.accept(new ActionVisitor(), minion, m.getPlayer1DeckCards(), m.getPlayer1HandCards(),
                            m.getPlayer1PlayedCards(), m.getPlayer2DeckCards(), m.getPlayer2HandCards(),
                            m.getPlayer2PlayedCards(), m.getPlayer1Hero(), m.getPlayer2Hero(), m);
                }
            }
        }
    }

    public void faezeAction(Minion target, boolean p2Turn, ClientHandler cl) {
        if (p2Turn) {
            for (Card card : m.getPlayer1PlayedCards()) {
                if (card.getName().equalsIgnoreCase("faeze")) {
                    m.Attack2((Minion) card, target, m.getPlayer2PlayedCards());
                }
            }
        } else {
            for (Card card : m.getPlayer2PlayedCards()) {
                if (card.getName().equalsIgnoreCase("faeze")) {
                    m.Attack2((Minion) card, target, m.getPlayer1PlayedCards());
                }
            }
        }
    }

}
