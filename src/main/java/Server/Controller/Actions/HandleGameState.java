package Server.Controller.Actions;

import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Model.CardModelView;
import Server.Model.GameState;

import java.util.ArrayList;

public class HandleGameState {

    private final Managers m;

    public HandleGameState(Managers m) {
        this.m = m;
    }

    public GameState getState(ClientHandler cl) {
        if (cl.equals(m.getCl1())) {
            ArrayList<CardModelView> hand = Admin.getInstance().modelList(m.getPlayer1HandCards());
            ArrayList<CardModelView> p1Played = Admin.getInstance().modelList(m.getPlayer1PlayedCards());
            ArrayList<CardModelView> p2Played = Admin.getInstance().modelList(m.getPlayer2PlayedCards());
            CardModelView p1w = Admin.getInstance().getWeaponViewModel(m.getPlayer1Weapon());
            CardModelView p2w = Admin.getInstance().getWeaponViewModel(m.getPlayer2Weapon());
            int dpm = m.getPlayer1Hero().getHeroPower().getManaCost() - m.getPlayer1PowerManaDecrease();
            int upm = m.getPlayer2Hero().getHeroPower().getManaCost() - m.getPlayer2PowerManaDecrease();
            return new GameState(m.getPlayer1().getUsername(), m.getPlayer2().getUsername(), m.getPlayer1Hero().getName(),
                    m.getPlayer2Hero().getName(), m.getTime(), m.getPlayer1HeroPowerUsageTime(), m.getPlayer2HeroPowerUsageTime(),
                    dpm, upm, m.getPlayer1NotUsedMana(), m.getPlayer1TotalMana(), m.getPlayer1Hero().getHealth(),
                    m.getPlayer2Hero().getHealth(), m.getPlayer1Hero().getDefence(), m.getPlayer2Hero().getDefence(),
                    m.getPlayer1HandCards().size(), m.getPlayer2HandCards().size(), m.getPlayer1CardsOfPlayer().size(),
                    m.getPlayer2PlayedCards().size(), m.getPlayer1DeckCards().size(), m.getPlayer2DeckCards().size(),
                    m.getPlayer1Weapon() != null, m.getPlayer2Weapon() != null, m.getPlayer1Hero().isCanAttack(),
                    p1w, p2w, null, null, hand, p1Played, p2Played, m.getGameLog(), m.isP2Turn());
        } else {
            ArrayList<CardModelView> hand = Admin.getInstance().modelList(m.getPlayer2HandCards());
            ArrayList<CardModelView> p1Played = Admin.getInstance().modelList(m.getPlayer2PlayedCards());
            ArrayList<CardModelView> p2Played = Admin.getInstance().modelList(m.getPlayer1PlayedCards());
            CardModelView p1w = Admin.getInstance().getWeaponViewModel(m.getPlayer2Weapon());
            CardModelView p2w = Admin.getInstance().getWeaponViewModel(m.getPlayer1Weapon());
            int upm = m.getPlayer1Hero().getHeroPower().getManaCost() - m.getPlayer1PowerManaDecrease();
            int dpm = m.getPlayer2Hero().getHeroPower().getManaCost() - m.getPlayer2PowerManaDecrease();
            return new GameState(m.getPlayer2().getUsername(), m.getPlayer1().getUsername(), m.getPlayer2Hero().getName(),
                    m.getPlayer1Hero().getName(), m.getTime(), m.getPlayer2HeroPowerUsageTime(), m.getPlayer1HeroPowerUsageTime(),
                    dpm, upm, m.getPlayer2NotUsedMana(), m.getPlayer2TotalMana(), m.getPlayer2Hero().getHealth(),
                    m.getPlayer1Hero().getHealth(), m.getPlayer2Hero().getDefence(), m.getPlayer1Hero().getDefence(),
                    m.getPlayer2HandCards().size(), m.getPlayer1HandCards().size(), m.getPlayer2PlayedCards().size(),
                    m.getPlayer1CardsOfPlayer().size(), m.getPlayer2DeckCards().size(), m.getPlayer1DeckCards().size(),
                    m.getPlayer2Weapon() != null, m.getPlayer1Weapon() != null, m.getPlayer2Hero().isCanAttack(),
                    p1w, p2w, null, null, hand, p1Played, p2Played, m.getGameLog(), !m.isP2Turn());
        }
    }

}
