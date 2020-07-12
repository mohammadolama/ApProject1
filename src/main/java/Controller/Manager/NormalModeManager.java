package Controller.Manager;

import Controller.Actions.SPVisitor.SpecialPowerVisitor;
import MainLogic.DeckLogic;
import MainLogic.JsonReaders;
import Model.Cards.Card;
import Model.Heros.Hero;
import Model.InfoPassive;
import Model.Player;

import java.util.ArrayList;
import java.util.Collections;

public class NormalModeManager extends Managers {

    public NormalModeManager(Player player, InfoPassive infoPassive, ArrayList<Card> arrayList,
                             String card1, String card2, String card3) {
        try {
            friendlyCardsOfPlayer = arrayList;
            ThreeCard(friendlyCardsOfPlayer, card1, card2, card3);
            this.friendlyPlayer = player;
            this.friendlyPlayerHero = (Hero) player.getSelectedDeck().getHero().clone();
            this.playerHero = friendlyPlayerHero;
            this.friendlyInfoPassive = infoPassive;
            friendlyInfoInitilize(infoPassive);
            enemyInit();
            friendlyPlayerHero.accept(new SpecialPowerVisitor(), null, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyDeckCards, enemyHandCards, enemyPlayedCards);
            enemyPlayerHero.accept(new SpecialPowerVisitor(), null, friendlyDeckCards, friendLyHandCards, friendlyPlayedCards, enemyDeckCards, enemyHandCards, enemyPlayedCards);
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
        friendLyHandCards = ar;
        friendlyDeckCards = arrayList;
    }

    private void enemyInit() {
        Player player = JsonReaders.deckReaderPlayer("enemy");
        this.enemyPlayer = player;
        enemyInfoPassive = InfoPassive.sample();
        enemyInfoInitilize(enemyInfoPassive);
        ArrayList<Card> ar1 = DeckLogic.UpdateDeck(player.getSelectedDeck().getDeck());
        Collections.shuffle(ar1);
        ThreePrimitiveRandom(ar1, "enemy");
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
