package Model.SpecialPowers;

import Model.Cards.Card;
import Model.Cards.Spell;

import java.util.ArrayList;

public class MageSpecialPower extends SpecialPower {
    @Override
    void act(ArrayList<Card> deck) {
        for (Card card : deck) {
            if (card instanceof Spell) {
                card.setManaCost(card.getManaCost() - 2);
                if (card.getManaCost() < 0) {
                    card.setManaCost(0);
                }
            }
        }
    }
}
