package Server.Controller.Manager;

import Server.Controller.Actions.SPVisitor.HeroPowerVisitor;

import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Model.Cards.*;
import Server.Model.Enums.ContiniousActionCarts;
import Server.Model.Heros.*;
import Server.Model.*;

import java.util.ArrayList;
import java.util.ListIterator;

public abstract class NormalManagers extends Managers {

    @Override
    void player1InfoInitilize(InfoPassive infoPassive) {
        player1StartingMana = 1;
        player1TotalMana = 1;
        player1NotUsedMana = 1;
        player1DrawCardNum = 1;
        player1HeroPowerUsageTime = 1;
        p1HPMAXUT = 1;
        player1PowerManaDecrease = 0;
        player1ManaDecrease = 0;
        player1DefenceAdd = 0;
        String st = infoPassive.getName();
        if (st.equalsIgnoreCase("twiceDraw")) {
            player1DrawCardNum = 2;
        } else if (st.equalsIgnoreCase("offCards")) {
            player1ManaDecrease = 1;
        } else if (st.equalsIgnoreCase("warriors")) {
            player1DefenceAdd = 2;
        } else if (st.equalsIgnoreCase("manaJump")) {
            player1StartingMana = 2;
            player1TotalMana = 2;
            player1NotUsedMana = 2;
        } else if (st.equalsIgnoreCase("freePower")) {
            player1HeroPowerUsageTime = 2;
            p1HPMAXUT = 2;
            player1PowerManaDecrease = 1;
        }
    }

    @Override
    void player2InfoInitilize(InfoPassive infoPassive) {
        player2StartingMana = 0;
        player2TotalMana = 0;
        player2NotUsedMana = 0;
        player2DrawCardNum = 1;
        player2HeroPowerUsageTime = 1;
        p2HPMAXUT = 1;
        player2PowerManaDecrease = 0;
        player2ManaDecrease = 0;
        player2DefenceAdd = 0;
        String st = infoPassive.getName();
        if (st.equalsIgnoreCase("twiceDraw")) {
            player2DrawCardNum = 2;
        } else if (st.equalsIgnoreCase("offCards")) {
            player2ManaDecrease = 1;
        } else if (st.equalsIgnoreCase("warriors")) {
            player2DefenceAdd = 2;
        } else if (st.equalsIgnoreCase("manaJump")) {
            player2StartingMana = 2;
            player2TotalMana = 2;
            player2NotUsedMana = 2;
        } else if (st.equalsIgnoreCase("freePower")) {
            player2HeroPowerUsageTime = 2;
            p2HPMAXUT = 2;
            player2PowerManaDecrease = 1;
        }
    }

    @Override
    void ThreePrimitiveRandom(ArrayList<Card> arrayList, String value) {
        ListIterator<Card> iterator = arrayList.listIterator();
        ArrayList<Card> ar = new ArrayList<>();
        while (iterator.hasNext()) {
            Card cards = iterator.next();
            ar.add(cards);
            iterator.remove();
            if (ar.size() == 3)
                break;
        }
        while (ar.size() < 3) {
            ar.add(arrayList.get(0));
            arrayList.remove(0);
        }
        if (value.equalsIgnoreCase("friendly")) {
            player1HandCards = ar;
            player1DeckCards = arrayList;
        } else {
            player2HandCards = ar;
            player2DeckCards = arrayList;
        }
    }

    @Override
    void refillMana(boolean p1Turn, ClientHandler cl) {
        if (p1Turn) {
            if (player2TotalMana < 10) {
                player2TotalMana++;
            }
            player2NotUsedMana = player2TotalMana;
            player2HeroPowerUsageTime = p2HPMAXUT;
        } else {
            if (player1TotalMana < 10) {
                player1TotalMana++;
            }
            player1NotUsedMana = player1TotalMana;
            player1HeroPowerUsageTime = p1HPMAXUT;
        }
    }

    @Override
    public boolean drawCard(int j, String mode, ArrayList<Card> deck, ArrayList<Card> hand, ClientHandler cl) {
        if (deck.size() == 0) {
            return false;
        } else {
            if (deck.size() < j) {
                j = deck.size();
            }
            for (int i = 0; i < j; i++) {
                Card cards = randomCardDraw(deck);
                if (hand.size() < 12) {
                    if (mode == null || (mode.equalsIgnoreCase("extra") && !(cards instanceof Spell))) {
                        addCard(hand, cards);
                        matinAction(false);
                    }
                }
                removeCard(deck, cards);
            }
            return true;
        }
    }

    void reversePlayers() {
        Player playerTemp = this.player1;
        player1 = player2;
        player2 = playerTemp;

        InfoPassive tempInfo = this.player1InfoPassive;
        player1InfoPassive = player2InfoPassive;
        player2InfoPassive = tempInfo;

        int startingManaTemp = this.player1StartingMana;
        player1StartingMana = player2StartingMana;
        player2StartingMana = startingManaTemp;

        int totalManaTemp = player1TotalMana;
        player1TotalMana = player2TotalMana;
        player2TotalMana = totalManaTemp;

        int notUsedTemp = player1NotUsedMana;
        player1NotUsedMana = player2NotUsedMana;
        player2NotUsedMana = notUsedTemp;

        int drawNumTemp = player1DrawCardNum;
        player1DrawCardNum = player2DrawCardNum;
        player2DrawCardNum = drawNumTemp;

        int heropowerUsageTemp = player1HeroPowerUsageTime;
        player1HeroPowerUsageTime = player2HeroPowerUsageTime;
        player2HeroPowerUsageTime = heropowerUsageTemp;

        int HPMaxTemp = p1HPMAXUT;
        p1HPMAXUT = p2HPMAXUT;
        p2HPMAXUT = HPMaxTemp;

        int manaDecTemp = player1ManaDecrease;
        player1ManaDecrease = player2ManaDecrease;
        player2ManaDecrease = manaDecTemp;

        int powerManaTemp = player1PowerManaDecrease;
        player1PowerManaDecrease = player2PowerManaDecrease;
        player2PowerManaDecrease = powerManaTemp;

        int defenceTemp = player1DefenceAdd;
        player1DefenceAdd = player2DefenceAdd;
        player2DefenceAdd = defenceTemp;

        ArrayList<Card> handsTemp = this.player1HandCards;
        player1HandCards = player2HandCards;
        player2HandCards = handsTemp;

        ArrayList<Card> deckTemp = this.player1DeckCards;
        player1DeckCards = player2DeckCards;
        player2DeckCards = deckTemp;

        ArrayList<Card> playedTemp = this.player1PlayedCards;
        player1PlayedCards = player2PlayedCards;
        player2PlayedCards = playedTemp;

        ArrayList<Card> continiousTemp = this.player1ContiniousActionCard;
        player1ContiniousActionCard = player2ContiniousActionCard;
        player2ContiniousActionCard = continiousTemp;

        Weapon temp4 = this.player1Weapon;
        player1Weapon = player2Weapon;
        player2Weapon = temp4;

        Hero heroTemp = player1Hero;
        player1Hero = player2Hero;
        player2Hero = heroTemp;
    }

    void PlayerTurn(ClientHandler cl) {
        checkDestroyMinion();
        canBeAttackedUpdater(cl);
        wakeUp(false, cl);
        refillMana(false, cl);
        chargeWeapon(false, cl);
        if (!drawCard(player1DrawCardNum, null, player1DeckCards, player1HandCards, cl))
            heroTakeDamage(player1Hero, 1);
    }

    void checkContiniousAction(Card cards, boolean AI) {
        for (ContiniousActionCarts value : ContiniousActionCarts.values()) {
            if (cards.getName().equalsIgnoreCase(value.toString())) {
                addContiniousActionCard(cards, AI);
                break;
            }
        }
    }

    void setEnemyWeapon(Weapon enemyWeapon) {
        this.player2Weapon = enemyWeapon;
        this.player2Hero.setWeapon(enemyWeapon);
    }

}
