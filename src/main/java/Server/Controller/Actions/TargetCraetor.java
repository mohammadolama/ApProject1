package Server.Controller.Actions;

import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Model.Interface.Character;

public class TargetCraetor {

    private final Managers m;

    public TargetCraetor(Managers m) {
        this.m = m;
    }

    Character createTarget(int target, ClientHandler cl) {
        if (cl.equals(m.getCl1())) {
            if (target >= 10 && target < 20) {
                return m.getPlayer1PlayedCards().get(target - 10);
            } else if (target >= 20 && target < 30) {
                return m.getPlayer2PlayedCards().get(target - 20);
            } else if (target == 1) {
                return m.getPlayer1Hero();
            } else if (target == 2) {
                return m.getPlayer2Hero();
            } else {
                return null;
            }
        } else {
            if (target >= 10 && target < 20) {
                return m.getPlayer2PlayedCards().get(target - 10);
            } else if (target >= 20 && target < 30) {
                return m.getPlayer1PlayedCards().get(target - 20);
            } else if (target == 1) {
                return m.getPlayer2Hero();
            } else if (target == 2) {
                return m.getPlayer1Hero();
            } else {
                return null;
            }
        }
    }

}
