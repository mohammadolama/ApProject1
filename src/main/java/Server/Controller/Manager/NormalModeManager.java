package Server.Controller.Manager;

import Server.Controller.Actions.SPVisitor.SpecialPowerVisitor;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.MainLogic.DeckLogic;
import Server.Controller.MainLogic.JsonReaders;
import Server.Model.Cards.Card;
import Server.Model.Heros.Hero;
import Server.Model.InfoPassive;
import Server.Model.Player;

import java.util.ArrayList;
import java.util.Collections;

public class NormalModeManager extends NormalManagers {

    public NormalModeManager(Player player, InfoPassive infoPassive, ArrayList<Card> arrayList,
                             String card1, String card2, String card3, ClientHandler cl) {
        try {
            player1CardsOfPlayer = arrayList;
            ThreeCard(player1CardsOfPlayer, card1, card2, card3);
            this.player1 = player;
            this.player1Hero = (Hero) player.getSelectedDeck().getHero().clone();
            this.currentHero = player1Hero;
            this.player1InfoPassive = infoPassive;
            player1InfoInitilize(infoPassive);
            enemyInit();
            player1Hero.accept(new SpecialPowerVisitor(), null, player1DeckCards, player1HandCards, player1PlayedCards, player2DeckCards, player2HandCards, player2PlayedCards, this);
            player2Hero.accept(new SpecialPowerVisitor(), null, player2DeckCards, player2HandCards, player2PlayedCards, player1DeckCards, player1HandCards, player1PlayedCards, this);
            cl1 = cl;
            MyTimer myTimer = new MyTimer();
            myTimer.start();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    private void ThreeCard(ArrayList<Card> arrayList, String card1, String card2, String card3) {
        ArrayList<Card> ar = new ArrayList<>();
        for (Card card : arrayList) {
            if (card.getName().equalsIgnoreCase(card1)) {
                ar.add(card);
                arrayList.remove(card);
                break;
            }
        }
        for (Card card : arrayList) {
            if (card.getName().equalsIgnoreCase(card2)) {
                ar.add(card);
                arrayList.remove(card);
                break;
            }
        }
        for (Card card : arrayList) {
            if (card.getName().equalsIgnoreCase(card3)) {
                ar.add(card);
                arrayList.remove(card);
                break;
            }
        }
        player1HandCards = ar;
        player1DeckCards = arrayList;
    }

    private void enemyInit() {
        Player player = JsonReaders.deckReaderPlayer("enemy");
        this.player2 = player;
        player2InfoPassive = InfoPassive.sample();
        player2InfoInitilize(player2InfoPassive);
        ArrayList<Card> ar1 = DeckLogic.UpdateDeck(player.getSelectedDeck().getDeck());
        Collections.shuffle(ar1);
        ThreePrimitiveRandom(ar1, "enemy");
        this.player2PlayedCards = new ArrayList<>();
        this.player2Hero = player.getSelectedDeck().getHero();
    }


    @Override
    public void endTurn(ClientHandler cl) {
        benyaminAction(false);
        reversePlayers();
        PlayerTurn(cl);
        flag1 = false;
    }


}
