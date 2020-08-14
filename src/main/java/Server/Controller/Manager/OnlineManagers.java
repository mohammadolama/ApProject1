package Server.Controller.Manager;

import Server.Controller.MainLogic.ClientHandler;

public class OnlineManagers extends Managers {


    @Override
    public void endTurn(ClientHandler cl) {
        updateGameLog(String.format("%s  EndTurn .", cl.getPlayer().getUsername()));
        p2Turn = !p2Turn;
        timer.reset1(p2Turn);
        if (cl.equals(cl1)) {
            benyaminAction(false);
            PlayerTurn(cl2);
        } else {
            benyaminAction(true);
            PlayerTurn(cl1);
        }
    }
}
