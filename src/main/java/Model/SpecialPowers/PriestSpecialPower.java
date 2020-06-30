package Model.SpecialPowers;

import Model.Cards.Card;
import Model.Cards.Spell;

import java.util.ArrayList;

public class PriestSpecialPower extends SpecialPower {
    @Override
    void act(ArrayList<Card> deck) {
        for (Card card : deck) {
            if (card.getName().equalsIgnoreCase("cookie") && card.getName().equalsIgnoreCase("holylight")) {
                ((Spell) card).setHealthRestore(((Spell) card).getHealthRestore() * 2);
            }
        }
    }
}
