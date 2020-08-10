package Server.Controller.Manager;

import Server.Controller.Actions.SPVisitor.SpecialPowerVisitor;
import Server.Controller.MainLogic.ClientHandler;
import Server.Model.Cards.Card;
import Server.Model.Heros.Hero;
import Server.Model.InfoPassive;
import Server.Model.Player;

import java.util.ArrayList;

public class OnlineManager extends Managers {

    public OnlineManager(Player player1, Player player2, ClientHandler cl1,
                         ClientHandler cl2, InfoPassive ip1, InfoPassive ip2,
                         ArrayList<Card> list1, ArrayList<Card> list2,
                         ArrayList<String> cards1, ArrayList<String> cards2) {

        try {
            this.player1 = player1;
            this.player2 = player2;
            this.player1InfoPassive = ip1;
            this.player2InfoPassive = ip2;
            this.cl1 = cl1;
            this.cl2 = cl2;
            this.player1CardsOfPlayer = list1;
            this.player2CardsOfPlayer = list2;
            this.player1Hero = (Hero) player1.getSelectedDeck().getHero().clone();
            this.player2Hero = (Hero) player2.getSelectedDeck().getHero().clone();
            this.currentHero = player1Hero;
            player1Hero.accept(new SpecialPowerVisitor(), null, player1DeckCards, player1HandCards,
                    player1PlayedCards, player2DeckCards, player2HandCards, player2PlayedCards, this);

            player2Hero.accept(new SpecialPowerVisitor(), null, player1DeckCards, player1HandCards,
                    player1PlayedCards, player2DeckCards, player2HandCards, player2PlayedCards, this);

            player1InfoInitilize(ip1);
            player2InfoInitilize(ip2);
            initLists(list1, cards1, 1);
            initLists(list2, cards2, 2);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }


    private void initLists(ArrayList<Card> list, ArrayList<String> cards,
                           int i) {
        ArrayList<Card> ar = new ArrayList<>();
        for (Card card : list) {
            if (card.getName().equalsIgnoreCase(cards.get(0))) {
                ar.add(card);
                list.remove(card);
                break;
            }
        }
        for (Card card : list) {
            if (card.getName().equalsIgnoreCase(cards.get(1))) {
                ar.add(card);
                list.remove(card);
                break;
            }
        }
        for (Card card : list) {
            if (card.getName().equalsIgnoreCase(cards.get(2))) {
                ar.add(card);
                list.remove(card);
                break;
            }
        }
        if (i == 1) {
            player1HandCards = ar;
            player1DeckCards = list;
        } else {
            player2HandCards = ar;
            player2DeckCards = list;
        }


    }

    @Override
    public void endTurn(ClientHandler cl) {
        p2Turn = !p2Turn;
        if (cl.equals(cl1)) {
            benyaminAction(false);
            PlayerTurn(cl2);
        } else {
            benyaminAction(true);
            PlayerTurn(cl1);
        }
    }


}


