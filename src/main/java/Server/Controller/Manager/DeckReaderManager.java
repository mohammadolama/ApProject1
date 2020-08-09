package Server.Controller.Manager;

import Client.View.Configs.DeckReader;
import Server.Controller.Actions.SPVisitor.SpecialPowerVisitor;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.MainLogic.JsonReaders;
import Server.Model.InfoPassive;
import Server.Model.Player;

import java.util.ArrayList;

public class DeckReaderManager extends NormalManagers {

    public DeckReaderManager(InfoPassive infoPassive) {
        deckReaderMode = true;
        practiceMode = false;
        Player player = JsonReaders.deckReaderPlayer("player");
        this.player1 = player;
        this.player1Hero = player.getSelectedDeck().getHero();
        player1InfoInitilize(infoPassive);
        deckReaderEnemy();
        DeckReader deckReader = JsonReaders.deckReader();
//        ArrayList<Card> friend = DeckLogic.UpdateDeck(deckReader.getFriend());
//        ArrayList<Card> enemy = DeckLogic.UpdateDeck(deckReader.getEnemy());
//        ThreePrimitiveRandom(friend, "friendly");
//        ThreePrimitiveRandom(enemy, "enemy");
        player1Hero.accept(new SpecialPowerVisitor(), null, player1DeckCards, player1HandCards, player1PlayedCards, player2DeckCards, player2HandCards, player2PlayedCards, );
        player2Hero.accept(new SpecialPowerVisitor(), null, player1DeckCards, player1HandCards, player1PlayedCards, player2DeckCards, player2HandCards, player2PlayedCards, );

    }

    private void deckReaderEnemy() {
        Player player = JsonReaders.deckReaderPlayer("AI");
        this.player2 = player;
        player2InfoPassive = InfoPassive.sample();
        player2InfoInitilize(player2InfoPassive);
        this.player2PlayedCards = new ArrayList<>();
        this.player2Hero = player.getSelectedDeck().getHero();
    }


    @Override
    public void endTurn(ClientHandler cl) {
        benyaminAction(false);
        reversePlayers();
        PlayerTurn(cl);
    }
}
