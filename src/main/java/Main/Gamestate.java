package Main;

import java.io.IOException;

public class Gamestate {
    private static Player player;


    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
//        if (Gamestate.player == null){
//            try {
//                player=new Player();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        Gamestate.player = player;
    }
}
