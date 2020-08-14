package Server.Controller.Manager;

import Server.Controller.Actions.SPVisitor.SpecialPowerVisitor;
import Server.Model.Cards.Card;
import Server.Model.Requirements;

import java.util.ArrayList;
import java.util.Arrays;

public class Multiplayer extends OnlineManagers {


    public Multiplayer(Requirements rq1, Requirements rq2,
                       ArrayList<Card> list1, ArrayList<Card> list2) {
        multyplayer = true;
        this.cl1 = rq1.getClientHandler();
        this.cl2 = rq2.getClientHandler();
        this.player1 = cl1.getPlayer();
        this.player2 = cl2.getPlayer();
        this.player1InfoPassive = rq1.getInfoPassive();
        this.player2InfoPassive = rq2.getInfoPassive();
        this.player1Hero = player1.getSelectedDeck().getHero();
        this.player2Hero = player2.getSelectedDeck().getHero();
        this.currentHero = player1Hero;
        player1InfoInitilize(player1InfoPassive);
        player2InfoInitilize(player2InfoPassive);
        player1Hero.accept(new SpecialPowerVisitor(), null, player1DeckCards, player1HandCards,
                player1PlayedCards, player2DeckCards, player2HandCards, player2PlayedCards, this);
        player2Hero.accept(new SpecialPowerVisitor(), null, player2DeckCards, player2HandCards,
                player2PlayedCards, player1DeckCards, player1HandCards, player1PlayedCards, this);
        player1CardsOfPlayer = list1;
        player2CardsOfPlayer = list2;
        initLists(player1CardsOfPlayer, new ArrayList<String>(Arrays.asList(rq1.getCard1(), rq1.getCard2(), rq1.getCard3())), 1);
        initLists(player2CardsOfPlayer, new ArrayList<String>(Arrays.asList(rq2.getCard1(), rq2.getCard2(), rq2.getCard3())), 2);
        timer = new MyTimer(false, this);
        timer.start();
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

}


