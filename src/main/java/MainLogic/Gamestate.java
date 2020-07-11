package MainLogic;

import Model.Player;

public class Gamestate {
    private static Player player;


    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        Gamestate.player = player;
    }
}
