package Model.SpecialPowers;

import Model.Cards.Card;
import Model.Cards.Minion;
import Model.Enums.Attribute;

import java.util.ArrayList;

public class HunterSpecialPower extends SpecialPower {
    @Override
    void act(ArrayList<Card> deck) {
        for (Card card : deck) {
            if (card instanceof Minion) {
                if (!((Minion) card).getAttributes().contains(Attribute.Rush)) {
                    ((Minion) card).getAttributes().add(Attribute.Rush);
                }
            }
        }
    }
}
