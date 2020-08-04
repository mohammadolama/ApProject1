package Server.Controller.Manager;

import Client.View.Configs.DeckReader;
import Server.Controller.Actions.SPVisitor.SpecialPowerVisitor;
import Server.Controller.MainLogic.DeckLogic;
import Server.Controller.MainLogic.JsonReaders;
import Server.Model.Cards.Card;
import Server.Model.InfoPassive;
import Server.Model.Player;

import java.util.ArrayList;

public class DeckReaderManager extends Managers {

    public DeckReaderManager(InfoPassive infoPassive) {
        deckReaderMode = true;
        practiceMode = false;
        Player player = JsonReaders.deckReaderPlayer("player");
        this.friendlyPlayer = player;
        this.friendlyPlayerHero = player.getSelectedDeck().getHero();
        friendlyInfoInitilize(infoPassive);
        deckReaderEnemy();
        DeckReader deckReader = JsonReaders.deckReader();
//        ArrayList<Card> friend = DeckLogic.UpdateDeck(deckReader.getFriend());
//        ArrayList<Card> enemy = DeckLogic.UpdateDeck(deckReader.getEnemy());
//        ThreePrimitiveRandom(friend, "friendly");
//        ThreePrimitiveRandom(enemy, "enemy");
        friendlyPlayerHero.accept(new SpecialPowerVisitor(), null, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyDeckCards, enemyHandCards, enemyPlayedCards);
        enemyPlayerHero.accept(new SpecialPowerVisitor(), null, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyDeckCards, enemyHandCards, enemyPlayedCards);

    }

    private void deckReaderEnemy() {
        Player player = JsonReaders.deckReaderPlayer("AI");
        this.enemyPlayer = player;
        enemyInfoPassive = InfoPassive.sample();
        enemyInfoInitilize(enemyInfoPassive);
        this.enemyPlayedCards = new ArrayList<>();
        this.enemyPlayerHero = player.getSelectedDeck().getHero();
    }


    @Override
    public void endTurn() {
        benyaminAction(false);
        reversePlayers();

        PlayerTurn();
    }
}
