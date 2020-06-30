package Model.SpecialPowers;

import Model.Cards.Card;

import java.util.ArrayList;

public class RogueSpecialPower extends SpecialPower {
    @Override
    void act(ArrayList<Card> deck) {
        for (Card card : deck) {
            if (!card.getHeroClass().equalsIgnoreCase("Neutral") && !card.getHeroClass().equalsIgnoreCase("Rogue")) {
                card.setManaCost(card.getManaCost() - 2);
                if (card.getManaCost() < 7) {
                    card.setManaCost(0);
                }
            }
        }
    }
}
