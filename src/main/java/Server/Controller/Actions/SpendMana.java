package Server.Controller.Actions;

import Server.Controller.Actions.CardVisitors.ActionVisitor;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Model.Cards.Card;
import Server.Model.Cards.Spell;

public class SpendMana {
    private Managers m;

    public SpendMana(Managers m) {
        this.m = m;
    }

    public void spendManaOnMinion(int i, boolean p2Turn) {
        if (p2Turn) {
            for (Card card : m.getPlayer2ContiniousActionCard()) {
                if (card.getName().equalsIgnoreCase("strengthinnumbers") || card.getName().equalsIgnoreCase("strengthinnumbersdr")) {
                    ((Spell) card).setManaSpendOnSth(((Spell) card).getManaSpendOnSth() + i);
                    card.accept(new ActionVisitor(), null, m.getPlayer2DeckCards(), m.getPlayer2HandCards(),
                            m.getPlayer2PlayedCards(), m.getPlayer1DeckCards(), m.getPlayer1HandCards(),
                            m.getPlayer1PlayedCards(), m.getPlayer2Hero(), m.getPlayer1Hero(), m);
                    break;
                }
            }
        } else {
            for (Card card : m.getPlayer1ContiniousActionCard()) {
                if (card.getName().equalsIgnoreCase("strengthinnumbers") || card.getName().equalsIgnoreCase("strengthinnumbersdr")) {
                    ((Spell) card).setManaSpendOnSth(((Spell) card).getManaSpendOnSth() + i);
                    card.accept(new ActionVisitor(), null, m.getPlayer1DeckCards(), m.getPlayer1HandCards(),
                            m.getPlayer1PlayedCards(), m.getPlayer2DeckCards(), m.getPlayer2HandCards(),
                            m.getPlayer2PlayedCards(), m.getPlayer1Hero(), m.getPlayer2Hero(), m);
                    break;
                }
            }
        }
    }

    public void spendManaOnSpell(int i, ClientHandler cl) {
        if (cl.equals(m.getCl2())) {
            for (Card card : m.getPlayer2ContiniousActionCard()) {
                if (card.getName().equalsIgnoreCase("learnjavadonic")) {
                    ((Spell) card).setManaSpendOnSth(((Spell) card).getManaSpendOnSth() + i);
                    card.accept(new ActionVisitor(), null, m.getPlayer2DeckCards(), m.getPlayer2HandCards(),
                            m.getPlayer2PlayedCards(), m.getPlayer1DeckCards(), m.getPlayer1HandCards(),
                            m.getPlayer1PlayedCards(), m.getPlayer2Hero(), m.getPlayer1Hero(), m);
                    break;
                }
            }
        } else {
            for (Card card : m.getPlayer1ContiniousActionCard()) {
                if (card.getName().equalsIgnoreCase("learnjavadonic")) {
                    ((Spell) card).setManaSpendOnSth(((Spell) card).getManaSpendOnSth() + i);
                    card.accept(new ActionVisitor(), null, m.getPlayer1DeckCards(), m.getPlayer1HandCards(),
                            m.getPlayer1PlayedCards(), m.getPlayer2DeckCards(), m.getPlayer2HandCards(),
                            m.getPlayer2PlayedCards(), m.getPlayer1Hero(), m.getPlayer2Hero(), m);
                    break;
                }
            }
        }
    }
}
