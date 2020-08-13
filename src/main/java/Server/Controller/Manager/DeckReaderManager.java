package Server.Controller.Manager;

import Client.View.Configs.DeckReader;
import Server.Controller.Actions.SPVisitor.SpecialPowerVisitor;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.MainLogic.DeckLogic;
import Server.Controller.MainLogic.JsonReaders;
import Server.Model.Cards.Card;
import Server.Model.InfoPassive;
import Server.Model.Player;
import Server.Model.Requirements;

import java.util.ArrayList;

public class DeckReaderManager extends NormalManagers {


    public DeckReaderManager(Requirements rq1, Requirements rq2) {
        deckReaderMode = true;
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
        DeckReader deckReader = JsonReaders.deckReader();
        ArrayList<Card> p1 = DeckLogic.UpdateDeck(deckReader.getFriend());
        ArrayList<Card> p2 = DeckLogic.UpdateDeck(deckReader.getEnemy());
        this.player1CardsOfPlayer = p1;
        this.player2CardsOfPlayer = p2;
        ThreePrimitiveRandom(p1, "friendly");
        ThreePrimitiveRandom(p2, "enemy");
        player1Hero.accept(new SpecialPowerVisitor(), null, player1DeckCards, player1HandCards, player1PlayedCards, player2DeckCards, player2HandCards, player2PlayedCards, this);
        player2Hero.accept(new SpecialPowerVisitor(), null, player2DeckCards, player2HandCards, player2PlayedCards, player1DeckCards, player1HandCards, player1PlayedCards, this);
        timer = new MyTimer(false, this);
        timer.start();
    }

    @Override
    public void endTurn(ClientHandler cl) {
        updateGameLog(String.format("%s  EndTurn .", cl.getPlayer().getUsername()));
        p2Turn = !p2Turn;
        timer.reset1(p2Turn);
        if (cl.equals(cl1)) {
            benyaminAction(false);
            PlayerTurn(cl2);
        } else {
            benyaminAction(true);
            PlayerTurn(cl1);
        }
    }
}
