package Server.Controller.Actions;

import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.MainLogic.DataBaseManagment;
import Server.Controller.Manager.Managers;
import Server.Model.Cards.Card;
import Server.Model.Cards.Minion;
import Server.Model.Cards.Weapon;
import Server.Model.Player;

import java.util.ListIterator;

public class GameChecker {

    private Managers m;

    public GameChecker(Managers m) {
        this.m = m;
    }

    public void checkDestroyMinion() {
        ListIterator<Card> iterator = m.getPlayer2PlayedCards().listIterator();
        while (iterator.hasNext()) {
            Minion minion = (Minion) iterator.next();
            if (minion.getHealth() <= 0) {
                m.getPlayer2Hero().setDefence(m.getPlayer2Hero().getDefence() + m.getPlayer2DefenceAdd());
                m.updateGameLog(String.format("Minion %s defeated .", minion.getName()));
                iterator.remove();
            }
        }
        ListIterator<Card> iterator1 = m.getPlayer1PlayedCards().listIterator();
        while (iterator1.hasNext()) {
            Minion minion = (Minion) iterator1.next();
            if (minion.getHealth() <= 0) {
                m.getPlayer1Hero().setDefence(m.getPlayer1Hero().getDefence() + m.getPlayer1DefenceAdd());
                m.updateGameLog(String.format("Minion %s defeated .", minion.getName()));
                iterator1.remove();
            }
        }
    }

    public void checkDestroyedWeapon(ClientHandler cl) {
        if (m.getPlayer1Weapon() != null) {
            if (m.getPlayer1Weapon().getDurability() <= 0) {
                m.setPlayerWeapon(null, cl);
            }
        }
        if (m.getPlayer2Weapon() != null) {
            if (m.getPlayer2Weapon().getDurability() <= 0) {
                m.setPlayerWeapon(null, cl);
            }
        }
    }

    public void updateWeapon(ClientHandler cl) {
        if (cl.equals(m.getCl1())) {
            Weapon weapon = m.getPlayer1Weapon();
            weapon.setDurability(weapon.getDurability() - 1);
            m.updateGameLog(String.format("%s use Weapon.", m.getPlayer1().getUsername()));
            m.setPlayerWeapon(weapon, cl);
            m.getPlayer1Hero().setCanAttack(false);
        } else {
            Weapon weapon = m.getPlayer2Weapon();
            weapon.setDurability(weapon.getDurability() - 1);
            m.updateGameLog(String.format("%s use Weapon.", m.getPlayer2().getUsername()));
            m.setPlayerWeapon(weapon, cl);
            m.getPlayer2Hero().setCanAttack(false);
        }
        checkDestroyedWeapon(cl);
    }

    int WinnerChecker() {
        if (!m.getPlayer2Hero().equals(m.getCurrentHero()) && m.getPlayer2Hero().getHealth() <= 0) {
            return 1;
        } else if (!m.getPlayer1Hero().equals(m.getCurrentHero()) && m.getPlayer1Hero().getHealth() <= 0) {
            return 2;
        } else if (m.getPlayer2Hero().equals(m.getCurrentHero()) && m.getPlayer2Hero().getHealth() <= 0) {
            return 3;
        } else if (m.getPlayer1Hero().equals(m.getCurrentHero()) && m.getPlayer1Hero().getHealth() <= 0) {
            return 4;
        } else {
            return 0;
        }
    }

    public void checkForWinner() {
        int i = WinnerChecker();
        switch (i) {
            case 1:
            case 3:
                winGame(m.getPlayer1Hero().getName().toLowerCase());
                if (m.onlineMode()) {
                    Admin.getInstance().updateDeckStates(1, m.getPlayer1());
                    Admin.getInstance().updateDeckStates(0, m.getPlayer2());
                    expManager(m.getPlayer1(), 1);
                    expManager(m.getPlayer2(), -1);
                }
                break;
            case 2:
            case 4:
                winGame(m.getPlayer2Hero().getName().toLowerCase());
                if (m.onlineMode()) {
                    Admin.getInstance().updateDeckStates(1, m.getPlayer2());
                    Admin.getInstance().updateDeckStates(0, m.getPlayer1());
                    expManager(m.getPlayer1(), -1);
                    expManager(m.getPlayer2(), 1);
                }
                break;
            case 0:
                break;
        }
    }

    public void expManager(Player player, int i) {
        if (player.getExp() == 0 && i < 0) {

        } else {
            player.setExp(player.getExp() + i);
            DataBaseManagment.savePlayer(player);
        }
    }

    public void winGame(String name) {
        m.getCl1().notifyWinner(name);
        if (m.getCl2() != null)
            m.getCl2().notifyWinner(name);
    }
}
