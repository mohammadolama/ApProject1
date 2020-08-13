package Server.Controller.Actions;

import Server.Controller.Manager.Managers;
import Server.Model.Cards.Card;
import Server.Model.Cards.Spell;

import java.util.ArrayList;
import java.util.Collections;

public class HandleCards {

    private Managers m;

    public HandleCards(Managers m) {
        this.m = m;
    }

    public boolean drawCard(int j, String mode, ArrayList<Card> deck, ArrayList<Card> hand) {
        if (deck.size() == 0) {
            return false;
        } else {
            if (deck.size() < j) {
                j = deck.size();
            }
            for (int i = 0; i < j; i++) {
                Card cards = randomCardDraw(deck);
                if (hand.size() < 12) {
                    if (mode == null || (mode.equalsIgnoreCase("extra") && !(cards instanceof Spell))) {
                        addCard(hand, cards);
                        m.matinAction(false);
                    }
                }
                removeCard(deck, cards);
            }
            return true;
        }
    }

    Card randomCardDraw(ArrayList<Card> cards) {
        if (cards.size() == 0)
            return null;
        if (m.isDeckReaderMode()) {
            return cards.get(0);
        }
        Collections.shuffle(cards);
        return cards.get(0);
    }

    void addCard(ArrayList<Card> arrayList, Card cards) {
        arrayList.add(cards);
    }

    void removeCard(ArrayList<Card> arrayList, Card cards) {
        arrayList.remove(cards);
    }

}
