package Server.Controller.Manager;

import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Model.CardModelView;
import Server.Model.Cards.Card;
import Server.Model.InfoPassive;
import Server.Model.State;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OnlineManager extends Managers {


    @Override
    void player1InfoInitilize(InfoPassive infoPassive) {

    }

    @Override
    void player2InfoInitilize(InfoPassive infoPassive) {

    }

    @Override
    void ThreePrimitiveRandom(ArrayList<Card> arrayList, String value) {

    }

    @Override
    void refillMana(boolean p1Turn, ClientHandler cl) {

    }

    @Override
    void endTurn(ClientHandler cl) {

    }

    @Override
    boolean drawCard(int j, String mode, ArrayList<Card> deck, ArrayList<Card> hand, ClientHandler cl) {
        return false;
    }


    public State getState(ClientHandler cl) {
        if (cl.equals(cl1)) {
            ArrayList<CardModelView> hand = Admin.getInstance().modelList(player1HandCards);
            ArrayList<CardModelView> p1Played = Admin.getInstance().modelList(player1PlayedCards);
            ArrayList<CardModelView> p2Played = Admin.getInstance().modelList(player2PlayedCards);
            CardModelView p1w = Admin.getInstance().getWeaponViewModel(player1Weapon);
            CardModelView p2w = Admin.getInstance().getWeaponViewModel(player2Weapon);
            int dpm = player1Hero.getHeroPower().getManaCost() - player1PowerManaDecrease;
            int upm = player2Hero.getHeroPower().getManaCost() - player2PowerManaDecrease;
            return new State(player1.getUsername(), player2.getUsername(), player1Hero.getName(),
                    player2Hero.getName(), time, player1HeroPowerUsageTime, player2HeroPowerUsageTime,
                    dpm, upm, player1NotUsedMana, player1TotalMana, player1Hero.getHealth(),
                    player2Hero.getHealth(), player1Hero.getDefence(), player2Hero.getDefence(),
                    player1HandCards.size(), player2HandCards.size(), player1PlayedCards.size(),
                    player2PlayedCards.size(), player1DeckCards.size(), player2DeckCards.size(),
                    player1Weapon != null, player2Weapon != null, player1Hero.getCanAttack(),
                    p1w, p2w, null, null, hand, p1Played, p2Played, gameLog);
        } else {
            ArrayList<CardModelView> hand = Admin.getInstance().modelList(player2HandCards);
            ArrayList<CardModelView> p1Played = Admin.getInstance().modelList(player2PlayedCards);
            ArrayList<CardModelView> p2Played = Admin.getInstance().modelList(player1PlayedCards);
            CardModelView p1w = Admin.getInstance().getWeaponViewModel(player2Weapon);
            CardModelView p2w = Admin.getInstance().getWeaponViewModel(player1Weapon);
            int upm = player1Hero.getHeroPower().getManaCost() - player1PowerManaDecrease;
            int dpm = player2Hero.getHeroPower().getManaCost() - player2PowerManaDecrease;
            return new State(player2.getUsername(), player1.getUsername(), player2Hero.getName(),
                    player1Hero.getName(), time, player2HeroPowerUsageTime, player1HeroPowerUsageTime,
                    dpm, upm, player2NotUsedMana, player2TotalMana, player2Hero.getHealth(),
                    player1Hero.getHealth(), player2Hero.getDefence(), player1Hero.getDefence(),
                    player2HandCards.size(), player1HandCards.size(), player2PlayedCards.size(),
                    player1PlayedCards.size(), player2DeckCards.size(), player1DeckCards.size(),
                    player2Weapon != null, player1Weapon != null, player2Hero.getCanAttack(),
                    p1w, p2w, null, null, hand, p1Played, p2Played, gameLog);
        }
    }

}


