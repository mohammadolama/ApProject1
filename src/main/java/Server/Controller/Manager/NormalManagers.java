package Server.Controller.Manager;

import Server.Model.Cards.Card;
import Server.Model.Cards.Weapon;
import Server.Model.Enums.ContiniousActionCarts;
import Server.Model.Heros.Hero;
import Server.Model.InfoPassive;
import Server.Model.Player;

import java.util.ArrayList;

public abstract class NormalManagers extends Managers {


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
